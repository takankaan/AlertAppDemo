import React, { Component, useState, setState } from 'react'
import { Form, Row, FormGroup, Label, Input, Table, Alert } from 'reactstrap'
import axios from 'axios';


function SignUp() {
    const url = "/auth/register";
    const [user, setValues] = useState({
        name: "",
        surname: "",
        fatherName: "",
        motherName: "",
        hashPassword: "",
        tcNo: "",
        phone: "",
        birthPlace: "",
        birthDate: ""
    })
    const [userId, setUserId] = useState();

    function handleChange(event) {
        let tarName = event.target.name;
        let value = event.target.value;

        setValues({ ...user, [tarName]: value }); // ilgilli attribute u değiştir.
       // console.log(user)
    }

    ////////////////////////////////////////////////////
    const [passwordConfirm, setConfirmPassword] = useState()
    function handlePasswordConfirm(event) {
        //let tarName = event.target.name;
        let value = event.target.value;
        setConfirmPassword(value)
        console.log("Password Confirm : " + passwordConfirm)

    }
    /*
    const handleNameChange = (event) => {
        setValues({ ...user, name: event.target.value })
        //console.log(event.target.value)
    }

    const handleSurNameChange = (event) => {
        setValues({ ...user, surname: event.target.value })
    }
    const handleTcNoChange = (event) => {
        setValues({ ...user, tcNo: event.target.value })
    }
    const handlePasswordChange = (event) => {
        setValues({ ...user, password: event.target.value })
    }
    const handleFatherNameChange = (event) => {
        setValues({ ...user, fatherName: event.target.value })
    }
    const handleMotherNameChange = (event) => {
        setValues({ ...user, motherName: event.target.value })
    }
    const handlePhoneChange = (event) => {
        setValues({ ...user, phone: event.target.value })
    }
    const handleBirthDateChange = (event) => {
        setValues({ ...user, birthDate: event.target.value })
    }
    const handleBirthPlaceChange = (event) => {
        setValues({ ...user, birthPlace: event.target.value })
    }
*/
 
    const handleSubmit = (event) => {
        event.preventDefault();
        //

        if ((passwordConfirm === user.hashPassword)) {//parolalar aynı girilmiş mi
            if ((passwordConfirm !== "")) { //boş değil
                //console.log("doğru bölge")
                //console.log(user)
                
                //Tc kimlik bilgisini long değerine dönüştürme
                setValues({...user, "tcNo" : +user.tcNo})

                try {
                axios.post(url, user)
                .then(resp => {setUserId(resp.data.id)
                console.log(resp.data.id)
                })
                
                }
                
                catch(e) {
                    alert(e);
                }
                console.log("current user id : " + userId);

                }


            else {
                alert("parola boş bırakılamaz.");
                return;
            }
        }

        else {

            alert("parola hatası");
            return;
        }

    }
    //uyarılar için kullanılacak


    return (
        <div>

            <Form onSubmit={handleSubmit} >
                <Table bordered>
                    <thead>
                        <tr>
                            <th> <FormGroup>
                                <Label for="name">
                                    İsim
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
                                    Soy İsim
                                </Label>
                                <Input
                                    id="surname"
                                    name="surname"
                                    placeholder="Soyisim"
                                    value={user.surname}
                                    onChange={handleChange}
                                />
                            </FormGroup></th>
                        </tr>

                        <tr>
                            <th> <FormGroup>
                                <Label for="TcNo">
                                    Tc Kimlik No
                                </Label>
                                <Input
                                    id="tcNo"
                                    name="tcNo"
                                    placeholder="Tc"
                                    value={user.tcNo}
                                    onChange={handleChange}
                                /></FormGroup></th>

                            <th>  <FormGroup>
                                <Label for="examplePassword">
                                    Parola
                                </Label>
                                <Input
                                    id="hashPassword"
                                    name="hashPassword"
                                    placeholder="parola"
                                    type="password"
                                    value={user.password}
                                    onChange={handleChange}

                                />
                            </FormGroup> </th>
                        </tr>
                        <tr>
                            <th>
                                <FormGroup>
                                    <Label for="examplePassword">
                                        Parola Tekrar
                                    </Label>
                                    <Input
                                        id="passwordConfirm"
                                        name="password"
                                        placeholder="parola"
                                        type="password"
                                        onChange={handlePasswordConfirm}
                                    />    </FormGroup>  </th>
                            <th> <FormGroup>
                                <Label for="surname">
                                    Baba Adı
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
                                    Anne Adı
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
                                    Telefon
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
                                    Doğum Günü
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
                                    Doğum Yeri
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
                <Input type="submit" value="Kaydol" />
            </Form>
        </div>
    )

}

export default SignUp;
