import axios from 'axios';
import React, { Component, useState, useEffect } from 'react'
import { Link } from 'react-router-dom';
import { CarouselControl,  Col, Container, Form, Row } from 'reactstrap'
import Home from '../Home/Home';
import { Navigate, Route, Routes } from 'react-router';
import SignUp from '../Sign Up/SignUp';


export default function Login() {


    const[user, setUser] = useState({
        tcNo: "",
        hashPassword: ""
    })

    const onChangeHandler = (event) => {
        let tarName = event.target.name;
        let value = event.target.value;

        setUser({...user, [tarName] : value})


        console.log(user);
        
    }

    const onSubmitHandler = (event) => {
        event.preventDefault();
        var url = "/auth/login"
        

        //tc kimlik, long tipine dönüştürülmeli!!
        user.tcNo = +user.tcNo;
        

        axios.post(url,user)
        .then(response => {
          
            if(response.data != "") {
                //kayıt bulundu, home page e yönlendir
                console.log(response.data)
               // alert("kullanıcı var")
                return <SignUp/>
                //window.location.replace("/home/{response.data.id}")
            }
            else {
                alert("hatalı tc no veya şifre.");
            }
            

           

        })
    }


    return (
        <div>
             <Container>
                    <Row  >
                        <Col xs = "2" ></Col>
                          <Col xs = "2" ><h2> Giriş Paneli</h2>
                        <Form onSubmit = {onSubmitHandler}>
                          
                        <h4>Tc Kimlik No</h4>
                            <input type = "text" onChange = {onChangeHandler} name = "tcNo"/>
                        <h4>Parola</h4>
                            <input type = "password" onChange = {onChangeHandler}  name= "hashPassword"/>
                        <Col>
                            <input type = "submit" color = "primary" value="Giriş Yap"/> 
                        </Col>
                        </Form>
                        <Link to = "/signup">Üye Değil Misiniz?</Link>
                        </Col>
                       
                    </Row>
        
            </Container>
        </div>
    )

}




