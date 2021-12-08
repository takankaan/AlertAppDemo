import axios from 'axios'
import React, { useState, useEffect } from 'react'
import { Button, Col, Form, Input, Label, Row, Table, UncontrolledDropdown, DropdownToggle, DropdownMenu, DropdownItem  } from 'reactstrap'

import ArrowCircleUpIcon from '@mui/icons-material/ArrowCircleUp';
import ArrowCircleDownIcon from '@mui/icons-material/ArrowCircleDown';
import { useParams } from 'react-router';

export default function CreateAlert(props) {
    const [stockList, setStockList] = useState([{}])
    const [postAlert, setPostAlert] = useState({
        userId: props.userId,
        stockId : +props.stockId,
        alertPrice : "",
        alertDirection : ""
    })

    const getStockData = () => {

    }
    


    const upHandler = () => {
        setPostAlert({...postAlert, ["alertDirection"] : true})
        console.log(postAlert)
    }
    const downHandler = () => {
        setPostAlert({...postAlert, ["alertDirection"] : false})
        console.log(postAlert)
    }
    const onPriceChangeHandler = (event) => {
        setPostAlert({...postAlert, ["alertPrice"] : +event.target.value})
    }

    //onFormSubmit
    const submitHandler = (event) => {
        event.preventDefault()
        console.log(postAlert)
        //console.log(postAlert)
        const url = "/alerts"
        axios.post(url,postAlert)
        .then(response => {
            if(response.data != null) {
                alert("Alarm başarıyla eklendi.")
            }
        })

     
    }


    return (
        <div>
            <Form>
                <Table>
                    <Col>
                        <Row><Label>Alarm Seviyesi : </Label> </Row>
                        <Row> <Input type="text" name="alertPrice" onChange={onPriceChangeHandler} /> </Row>
                    </Col>
                    <Col>
                        <Row><Label> Alarm Yönü : </Label> </Row>
                        <Row>
                            <UncontrolledDropdown >
                                <DropdownToggle caret color={postAlert.alertDirection ? "success" : "danger"}>
                                    {postAlert.alertDirection ? "Yukarı" : "Aşağı"}
                                </DropdownToggle>
                                <DropdownMenu>
                                    <DropdownItem onClick={upHandler}> <ArrowCircleUpIcon fontSize="large" color="success" /> </DropdownItem>
                                    <DropdownItem onClick={downHandler}> <ArrowCircleDownIcon fontSize="large" color="error" /></DropdownItem>
                                </DropdownMenu>
                            </UncontrolledDropdown>

                        </Row>
                    </Col>

                </Table>

                <Button color="success" onClick={submitHandler}>Alarm Oluştur </Button>
            </Form>
        </div>
    )
}
