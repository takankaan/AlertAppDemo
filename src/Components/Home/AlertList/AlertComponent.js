import axios from 'axios';
import React, { useState, useEffect } from 'react'
import { Link, useNavigate } from 'react-router-dom';
import { Button, Col, Container, Row, Table } from 'reactstrap';
import { Grid } from '@mui/material';

//icon imports
import DeleteForeverSharpIcon from '@mui/icons-material/DeleteForeverSharp';
import EditIcon from '@mui/icons-material/Edit';
import ShowChartIcon from '@mui/icons-material/ShowChart';
import ArrowCircleUpIcon from '@mui/icons-material/ArrowCircleUp';
import ArrowCircleDownIcon from '@mui/icons-material/ArrowCircleDown';

//dialog imports
import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogContent from '@mui/material/DialogContent';
import DialogContentText from '@mui/material/DialogContentText';
import DialogTitle from '@mui/material/DialogTitle';



export default function AlertComponent() {


    const [alertList, setAlertList] = useState([{}])
    const [openForm, setOpenForm] = useState({
        openState: false,
        id: -1
    }); //alert state


    useEffect(() => {
        getData()
    }, [])

    const navigate = useNavigate() //to stock chart 



    const getData = () => {
        const user = localStorage.getItem("currentUser")
        const userId = JSON.parse(user).id
        const dataURL = "/alerts?userId=" + userId
        axios.get(dataURL)
            .then(response => {
                //response is an alertList
                setAlertList(response.data)
                console.log(response.data)
            })
    }



    const removeItem = () => {
        //delete alert
        //alert(openForm.id + " numaralı eleman silinecek.")
        const url = "/alerts/" + openForm.id;
        var removeAlert = { deleted: true }

        axios.put(url, removeAlert)
            .then(response => {
                if (response.data != "") {
                    alert(openForm.id + " numaralı alarm silindi.")
                }
            })
        getData() //listeyi yeniden güncelle
        setOpenForm({ openState: false, id: openForm.id })

    }
    const navigateCharPage = (itemId) => {
        // alert(id)
        navigate("/home/chartPage", { state: { id: itemId } })

    }

    const handleClose = () => {
        setOpenForm({ openState: false, id: openForm.id })
    };

    //open dialog
    const handleClickOpen = (item) => {
        setOpenForm({ openState: true, id: item.id });
        console.log(openForm)
    };

    const newAlertPage = () => {
        navigate("/home/createAlert")
    }


    return (
        <div>
            <Table>
                <thead>
                    <tr>
                        <th> #Alert id</th>
                        <th> Stock Id</th>
                        <th> Alert Price </th>
                        <th> Created Date</th>
                        <th> Updated Date</th>
                        <th> Alert Direction </th>
                        <th> Process </th>
                    </tr>
                </thead>
                <tbody>
                    {
                        alertList.map((singleAlert) => (

                            <tr key={singleAlert.id}>
                                <td > {singleAlert.id} </td>
                                <td> {singleAlert.stockId} </td>
                                <td> {singleAlert.alertPrice} </td>
                                <td> {singleAlert.createdDate} </td>
                                <td> {singleAlert.updatedDate} </td>
                                <td> {singleAlert.alertDirection ? <ArrowCircleUpIcon fontSize="large" color="success" /> : <ArrowCircleDownIcon fontSize="large" color="error" />} </td>
                                <td>
                                    <Col>
                                        <Grid item xs={8} onClick={() => handleClickOpen(singleAlert)}>
                                            <DeleteForeverSharpIcon color="error" />
                                        </Grid>
                                        <Dialog
                                            open={openForm.openState}
                                            onClose={() => handleClose(singleAlert.id)}
                                            aria-labelledby="alert-dialog-title"
                                            aria-describedby="alert-dialog-description">

                                            <DialogTitle id="alert-dialog-title">
                                                {openForm.id + " numaralı alarmı silmek istediğinize emin misiniz?"}
                                            </DialogTitle>
                                            <DialogContent>
                                                <DialogContentText id="alert-dialog-description">
                                                    Bu işlem, hissenin belirlenen seviyeye gelmesi durumunda mesaj alamayacağınız anlamına gelir.
                                                </DialogContentText>
                                            </DialogContent>
                                            <DialogActions>
                                                <Button onClick={handleClose}>Vazgeç</Button>
                                                <Button onClick={() => removeItem(singleAlert.id)} autoFocus>
                                                    Onayla
                                                </Button>
                                            </DialogActions>
                                        </Dialog>
                                    </Col>
                                    <Col>
                                        <Grid item xs={8}>
                                            <div onClick={() => navigateCharPage(singleAlert.id)}>
                                                <ShowChartIcon color="success" />
                                            </div>
                                        </Grid>
                                    </Col>

                                </td>
                            </tr>
                        ))

                    }
                </tbody>
            </Table>
            <div>
                <Button onClick={newAlertPage} color="success" style={{ marginTop: 50, width: "50%", marginLeft:"25%" }}> Create New Alert</Button>
            </div>
        </div>
    )
}
