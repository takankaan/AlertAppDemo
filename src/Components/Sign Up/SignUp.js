import React, { Component, useState, setState } from 'react'
import { Form, Row, FormGroup, Label, Input, Table, Alert } from 'reactstrap'
import axios from 'axios';


function SignUp() {
    const url = "https://619140e841928b001768ffc3.mockapi.io/createUser";

    const [user, setValues] = useState({
        name: "",
        surname: "",
        fatherName: "",
        motherName: "",
        password: "",
        tcNo: "",
        phone: "",
        birthPlace: "",
        birthDate: ""
    })

    function handleChange(event) {
        let tarName = event.target.name;
        let value = event.target.value;

        setValues({ ...user, [tarName]: value }); // ilgilli attribute u değiştir.


    }
    ////////////////////////////////////////////////////
    const [passwordConfirm, setConfirmPassword] = useState()
    function handlePasswordConfirm(event) {
        //let tarName = event.target.name;
        let value = event.target.value;
        setConfirmPassword(value)

    }

    const [formState, setFormState] = useState(2);

    /*
    0- sayfa açılışı
    1- parola doğrulama yanlış
    2- kayıtlı kullanıcı var
    */

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


    const handlePasswordChange = (event) => {
        setValues({ ...user, password: event.target.value })
    }

    const handleSubmit = (event) => {
        event.preventDefault();
        //
   
        if ((passwordConfirm === user.password)) {//parolalar aynı girilmiş mi
            if ((passwordConfirm !== "")) { //boş değil
                /*
                TC KİMLİK BİLGİLERİ KARŞILAŞTIR
                */
                fetch(url)
                    .then(users => users.json())
                    .then(userList =>  {
                        for (var i = 0; i < userList.length; i++) {
                            if (userList[i].tcNo === user.tcNo) {
                                alert("Bu tc no ile kayıt oluşturulmuş.");
                                return;
                            }
                            else {
                                if (i === userList.length - 1) {
                                    //parola boş değil,
                                    //doğrulama başarılı,
                                    //tc no ya ait başka kayıt yok
                                    axios.post(url, user);
                                    alert("kayıt oluşturuldu");
                                    console.log(user)
                                    //tokenlar local storage ta tutulur
                                }
                            }
                        }

                    })



            }
            else {
                alert("parola boş bırakılamaz.");
                return;
            }
        }

        else {
            setFormState(1);
            alert("parola hatası");
            return;
        }

    }
    //uyarılar için kullanılacak
    function ManageAlert() {
        switch (formState) {
            case 0://açılış ekranı (alert metni yok)
                return (
                    null
                )
            case 1://tc kimlik eşleşti.
                console.log("tc kimlik ha tası")
                return (<Alert color="warning"> İlgili Tc Kimliğe ait kullanıcı bulundu
                    <a
                        className="alert-link"
                        href="#"
                    >
                        an example link
                    </a>
                    . Give it a click if you like.
                </Alert>
                )
            case 2:
                return (
                    <Alert color="danger"> This is a danger alert with{' '} <a className="alert-link " href="#">an example link</a> Give it a click if you like.</Alert>
                )
        }
    }

    return (
        <div>
            {ManageAlert}
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
                                    id="password"
                                    name="password"
                                    placeholder="parola"
                                    type="password"
                                    value={user.password}
                                    onChange={handlePasswordChange}

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
