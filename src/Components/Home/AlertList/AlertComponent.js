import axios from 'axios';
import React, { useState, useEffect } from 'react'
import { Button, Col, Row, Table } from 'reactstrap';
import DeleteIcon from '@mui/icons-material/Delete';
import { Grid, svgIconClasses } from '@mui/material';

//icon imports
import DeleteForeverSharpIcon from '@mui/icons-material/DeleteForeverSharp';
import EditIcon from '@mui/icons-material/Edit';
import ShowChartIcon from '@mui/icons-material/ShowChart';

//dialog imports
import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogContent from '@mui/material/DialogContent';
import DialogContentText from '@mui/material/DialogContentText';
import DialogTitle from '@mui/material/DialogTitle';



export default function AlertComponent() {


    const [alertList, setAlertList] = useState([{}])
    const [open, setOpen] = React.useState(false); //alert state


    useEffect(() => {
        getData()
    }, [])



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


    const editItem = (item) => {
        alert(item.id);
        //edit alert
    }

    const removeItem = (item) => {
        console.log(item)
        //delete alert
        setOpen(false)

    }
    const navigateCharPage = (item) => {

    }

    const handleClose = () => {
        setOpen(false);
    };

    //open dialog
    const handleClickOpen = (item) => {
        setOpen(true);
        console.log(item)
    };


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
                        <th> İşlem </th>
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
                                <td> {singleAlert.alertDirection ? "yukarı" : "aşağı"} </td>
                                <td>
                                    <Col >
                                        <Grid item xs={8}>
                                            <div onClick={() => editItem(alert)}>
                                                <EditIcon color="warning" />
                                            </div>
                                        </Grid>
                                    </Col>
                                    <Col>
                                        <Grid item xs={8} onClick ={() => handleClickOpen(singleAlert)}>
                                                <DeleteForeverSharpIcon color="error" />
                                        </Grid>
                                        <Dialog
                                            open={open}
                                            onClose={() => handleClose(singleAlert.id)}
                                            aria-labelledby="alert-dialog-title"
                                            aria-describedby="alert-dialog-description">

                                            <DialogTitle id="alert-dialog-title">
                                                { singleAlert.id + " numaralı alarmı silmek istediğinize emin misiniz?"}
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
                                            <div onClick={() => navigateCharPage(alert)}>
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
        </div>
    )
}
