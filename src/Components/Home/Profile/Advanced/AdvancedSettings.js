import { blue, green, red } from '@mui/material/colors'
import { style } from '@mui/system'
import axios from 'axios'
import React, { useEffect, useState } from 'react'
import { Link, useNavigate } from 'react-router-dom';
import { Button, Col, Container, Form, Input, Label, Row } from 'reactstrap'

export default function AdvancedSettings(props) {
    const [passwordData, setPasswordData] = useState({
        oldPassword: "",
        newPassword: "",
        newPasswordConfirm: ""
    })
    let navigate = useNavigate()


    const [deleteConfirm, setDeleteConfirm] = useState(false)

    const [tcNo, setTcNo] = useState()


    function handleChange(event) {
        let targetName = event.target.name;
        let value = event.target.value

        setPasswordData({ ...passwordData, [targetName]: value })

    }

    function onSubmitHandler(event) {
        var url = "/user/" + props.id + "/changePassword";
        event.preventDefault()
        
            if (passwordData.newPassword === passwordData.newPasswordConfirm) {
                //şifre değiştirilecek.
                delete passwordData.newPasswordConfirm //aktarılacak veri oldPassword ve newPassword den oluşacak.
                //axios put
                
                axios.put(url,passwordData)
                .then(response => {
                    console.log(response.data)
                    if(response.data.id == props.id) {
                        alert("Başarılı")
                    }
                    else {
                        alert("eski şifre yanlış.")
                    }
                })

            }
            else {
                alert("Girilen şifreler aynı değil.")
            }
        

      
    }

    function confirmDelete() {
        setDeleteConfirm(!deleteConfirm)
    }

    function deleteUser() {
        //hesap silinecek
        const url = "/user/" + props.id + "/delete";
        var removeUser = {deleted : true};
        axios.put(url,removeUser)
        .then(response => {
            if(response.data.id == props.id) {
                alert("hesabınız başarıyla silindi.")
                navigate("/")
            }
            else {
                alert("Hata")
            }
        })

        
       
    }

    function tcNoHandler(event) {
        event.preventDefault()
        var value = event.target.value;
        setTcNo(value)
    }


    return (
        <span className="block-example border border-danger">
            <div >
                <Container>
                    <Row>
                        <Form>
                            <Container>
                                <Col >
                                    <Row >
                                        <Label> Eski Parola </Label>
                                        <Input type="text" name="oldPassword" onChange={handleChange} />
                                    </Row>
                                    <Row className="mt-2" >
                                        <Label> Yeni Parola </Label>
                                        <Input type="text" name="newPassword" onChange={handleChange} />
                                    </Row>
                                    <Row className="mt-2" >
                                        <Label> Yeni Parola (tekrar) </Label>
                                        <Input type="text" name="newPasswordConfirm" onChange={handleChange} />
                                    </Row>
                                </Col>

                                <Col  >
                                <Row className="mt-3" >
                                        <Input type="submit" value="Parolayı Değiştir" onClick={onSubmitHandler}  />
                                    </Row>

                                </Col>
                            </Container>
                        </Form>
                    </Row>

                    <Row className = "mt-5">
                        <Button color="danger" onClick={confirmDelete}>Hesabı Sil</Button>
                    </Row>
                    {deleteConfirm ?
                        <Form className = "mt-3">
                            <Row>
                                <Input type="text" placeholder="Tc kimlik numaranızı yazın" name="tcNo" onChange={tcNoHandler} />
                            </Row>
                            <Row className = "mt-1">
                                <Input type="button" onClick={deleteUser} value="Hesabı Silmeyi Onayla" disabled={!(+tcNo === props.tcNo)}  />
                            </Row>
                        </Form>
                        : null}
                </Container>

            </div>
        </span>
    )
}
