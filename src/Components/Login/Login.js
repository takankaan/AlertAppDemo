import React, { Component } from 'react'
import { Link } from 'react-router-dom';
import { CarouselControl, Col, Container, Form, Row } from 'reactstrap'

export default class Login extends Component {
    state = {user : {
        tcNo: "",
        password: ""
        }
    }

    onChangeHandler = (event) => {
        let tarName = event.target.name;
        let value = event.target.value;
        this.state.user[tarName] = value;
        console.log(this.state.user);
        
    }

    onSubmitHandler = (event) => {
        event.preventDefault();
        var url = "https://619140e841928b001768ffc3.mockapi.io/createUser"
        var loginUser = fetch(url)
        .then(users => users.json)
        .then(userList => {userList.find( userList.tcNo === this.state.user.tcNo && userList.password === this.state.user.password)
        

        })
    }
    
    render() { 
        return (
                <Container>
                    <Row  >
                        <Col xs = "2" ></Col>
                          <Col xs = "2" ><h2> Giriş Paneli</h2>
                        <Form onSubmit = {this.onSubmitHandler}>
                          
                        <h4>Tc Kimlik No</h4>
                            <input type = "text" onChange = {this.onChangeHandler} name = "tcNo"/>
                        <h4>Parola</h4>
                            <input type = "password" onChange = {this.onChangeHandler}  name= "password"/>
                        <Col>
                            <input type = "submit" color = "primary" value="Giriş Yap"/> 
                        </Col>
                        </Form>
                        <Link to = "/signup">Üye Değil Misiniz?</Link>
                        </Col>
                       
                    </Row>
        
            </Container>

        )
    }
}
