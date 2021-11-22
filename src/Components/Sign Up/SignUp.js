import React, { Component, useState,useEffect, setState } from 'react'
import { Form, Row, FormGroup, Label, Input, Table } from 'reactstrap'
import axios from 'axios';
import { Alert, Snackbar } from '@mui/material';


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
    const [userId, setUserId] = useState(); //for current user
    const [passwordConfirm, setConfirmPassword] = useState() //for confirm password and passwordconfirm area are same
    const [message, setMessage] = useState({
        status:null,
        text:null
    }) //for managing alerts

 
    

    /*-----------FORM ÜZERİNDEKİ İNPUTLARA HER GİRDİ VERİLDİĞİNDE BU ALAN ÇALIŞIR. (passwordConfirm hariç) --------*/
    function handleChange(event) {
        let tarName = event.target.name;
        let value = event.target.value;

        setValues({ ...user, [tarName]: value }); // change the attribute that named tarName
       // console.log(user)
    }

    /*------PasswordConfirm alanına input verildiğinde bu alan çalışır. ----------------------*/
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
 

    /* ------- Kayıt ol butonuna basıldığında bu alan çalışır. ------------*/
    const handleSubmit = (event) => {
        event.preventDefault();
        

        if ((passwordConfirm === user.hashPassword)) {//parolalar aynı girilmiş mi
            if ((passwordConfirm !== "")) { //boş değil
                //password ve passwordConfirm değerleri aynı ve boş değil. Kayıt işlemi için post gönderilecek.
                
                //Tc kimlik bilgisini long değerine dönüştürme
                setValues({...user, "tcNo" : +user.tcNo})

                axios.post(url, user)
                .then(resp => { 
                    if(resp.data !== null) { //gelen değer null değil ise, kayıt oluşturulmuştur. formstatus u 1 yap. 
                        setUserId(resp.data.id);
                        setMessage({text:"Kayıt Oluşturuldu", status:"success"}) // kayıt oluşturuldu.
                        //console.log(userId);
                    }
                    else {
                        //alert("Bu tc no kullanılmış.") //gelen değer null ise, aynı tcno ya ait kullanıcı var demektir. formstatus u 2 yap.
                        setMessage({text : "bu tc kullanıldı", status : "error"})
                    }
                })
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


    
    //alert kapanma
    const handleClose = (event, reason) => {
        if (reason === 'clickaway') {
          return;
        }
        setMessage({
            status:null,
            text:null
        });
      };


    //alert için kullanılacak
    /*
    const AlertManager = () => {
        console.log("alert manager çalıştı.")
        switch(formStatus) {
            case 0://sayfa yeni açıldı, alert mesajı yok
            return null 
            case 1://kayıt başarıyla oluşturuldu
            return  <Snackbar open={true} autoHideDuration={12000} onClose={handleClose}>
                        <Alert onClose={handleClose} severity="success" sx={{ width: '100%' }}>
                            This is a success message!
                        </Alert>
                    </Snackbar>
            case 2://aynı tc ye ait kullanıcı vardır (error alert)


            default:
            return null
        }
    }
    */ 


    return (
        <div>
           {message.text && <Snackbar open={true} autoHideDuration={1200} onClose={handleClose}>
                        <Alert onClose={handleClose} severity={message.status} sx={{ width: '100%' }}>
                            {message.text}
                        </Alert>
                    </Snackbar> }
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
