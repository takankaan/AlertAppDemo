import { blue, green, red } from '@mui/material/colors'
import { style } from '@mui/system'
import axios from 'axios'
import React, { useEffect, useState } from 'react'
import { Button, Col, Container, Form, Input, Label, Row } from 'reactstrap'

export default function AdvancedSettings(props) {


    const [passwordData, setPasswordData] = useState({
        oldPassword: "",
        newPassword: "",
        newPasswordConfirm: ""
    })



    function handleChange(event) {
        let targetName = event.target.name;
        let value = event.target.value

        setPasswordData({ ...passwordData, [targetName]: value })

    }

    function onSubmitHandler(event) {
        event.preventDefault()
        if (props.password === passwordData.oldPassword) {
            if (passwordData.newPassword === passwordData.newPasswordConfirm) {
                //şifre değiştirilecek.


            }
            else {
                alert("Girilen şifreler aynı değil.")
            }
        }
        else {
            alert("hatalı parola")
        }

    }

    function deleteUser() {

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
                                    <Row >
                                        <Label> Yeni Parola </Label>
                                        <Input type="text" name="newPassword" onChange={handleChange} />
                                    </Row>
                                    <Row>
                                        <Label> Yeni Parola (tekrar) </Label>
                                        <Input type="text" name="newPasswordConfirm" onChange={handleChange} />
                                    </Row>
                                </Col>

                                <Col  >
                                    <Row>
                                        <Input type="submit" value="Parolayı Değiştir" onClick={onSubmitHandler} color="#1E6738" />
                                    </Row>
                                </Col>
                            </Container>
                        </Form>
                    </Row>

                    <Row >
                        <Button color="danger" onClick={deleteUser}>Hesabı Sil</Button>
                    </Row>
                </Container>

            </div>
        </span>
    )
}
