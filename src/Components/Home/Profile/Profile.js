import { blue } from '@mui/material/colors'
import React, { useState, useEffect } from 'react'
import { Link } from 'react-router-dom'
import { Button, Col, Container, Form, FormGroup, Input, Label, Row, Table } from 'reactstrap'
import ChangePassword from './ChangePassword/ChangePassword'

export default function Profile(props) {



    const [user, setCurrentUser] = useState({
        name: " ",
        surname: " ",
        fatherName: " ",
        motherName: " ",
        hashPassword: " ",
        tcNo: " ",
        phone: " ",
        birthPlace: " ",
        birthDate: " "
    })

    const [changePasswordState, setChangePasswordState] = useState(false)

    useEffect(() => {
        getData()
    }, []) //sayfa açılırken veriler alınsın



    const getData = () => {
        const u = localStorage.getItem("currentUser")
        const userData = JSON.parse(u)
        setCurrentUser(userData);
        console.log(userData)
    }

    function handleChange() {

    }

    function handleSubmit() {

    }

    function openPasswordWindow() {
        setChangePasswordState(!changePasswordState);
        console.log(changePasswordState)
    }

    return (
        <Container fluid="md" style={{ backgroundColor: blue }}>
            <Row>
                <Col >
                    <Row >
                        <Form onSubmit={handleSubmit} >
                            <Table bordered>
                                <thead>
                                    <tr>
                                        <th> <FormGroup>
                                            <Label for="name">
                                                İsim*
                                            </Label>
                                            <Input
                                                id="name"
                                                name="name"
                                                placeholder="İsim"
                                                value={user.name}
                                                onChange={handleChange}

                                            />
                                        </FormGroup> </th>

                                        <th> <FormGroup>
                                            <Label for="surname">
                                                Soy İsim*
                                            </Label>
                                            <Input
                                                id="surname"
                                                name="surname"
                                                placeholder="Soyisim"
                                                value={user.surname}
                                                onChange={handleChange}
                                            />
                                            <span>{ }</span>
                                        </FormGroup></th>
                                    </tr>

                                    <tr>
                                        <th> <FormGroup>
                                            <Label for="TcNo">
                                                Tc Kimlik No*
                                            </Label>
                                            <Input
                                                id="tcNo"
                                                name="tcNo"
                                                placeholder="Tc"
                                                value={user.tcNo}
                                                onChange={handleChange}
                                            /></FormGroup>

                                        </th>


                                        <th> <FormGroup>
                                            <Label for="surname">
                                                Baba Adı*
                                            </Label>
                                            <Input
                                                id="fatherName"
                                                name="fatherName"
                                                placeholder="Baba adı"
                                                value={user.fatherName}
                                                onChange={handleChange}

                                            />
                                        </FormGroup> </th>
                                    </tr>
                                    <tr>
                                        <th> <FormGroup>
                                            <Label for="surname">
                                                Anne Adı*
                                            </Label>
                                            <Input
                                                id="motherName"
                                                name="motherName"
                                                placeholder="Ana adı"
                                                value={user.motherName}
                                                onChange={handleChange}
                                            />
                                        </FormGroup></th>
                                        <th> <FormGroup>
                                            <Label for="surname">
                                                Telefon*
                                            </Label>
                                            <Input
                                                id="phone"
                                                name="phone"
                                                placeholder="Telefon Numarası"
                                                value={user.phone}
                                                onChange={handleChange}
                                            />
                                        </FormGroup></th>
                                    </tr>

                                    <tr>
                                        <th> <FormGroup>
                                            <Label for="exampleDate">
                                                Doğum Günü*
                                            </Label>
                                            <Input
                                                id="birthDate"
                                                name="birthDate"
                                                placeholder="Doğum Günü"
                                                type="date"
                                                value={user.birthDate}
                                                onChange={handleChange}
                                            />
                                        </FormGroup> </th>
                                        <th><FormGroup>
                                            <Label for="exampleCity">
                                                Doğum Yeri*
                                            </Label>
                                            <Input
                                                id="birthPlace"
                                                name="birthPlace"
                                                placeholder="Doğum Yeri"
                                                value={user.birthPlace}
                                                onChange={handleChange}

                                            />
                                        </FormGroup> </th>
                                    </tr>

                                </thead>
                            </Table>
                            <Input type="submit" value="Değişiklikleri kaydet" />
                        </Form>
                    </Row>
                    <Row>
                        <Button color="warning" onClick={openPasswordWindow}>Parola değiştirmek için tıklayın</Button>{' '}
                    </Row>
                </Col>
                <Col>
                    <Row>
                        {changePasswordState ? <ChangePassword /> : null}
                    </Row>
                </Col>
            </Row>
        </Container>
    )
}
