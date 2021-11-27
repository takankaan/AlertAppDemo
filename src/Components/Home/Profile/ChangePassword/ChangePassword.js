import { blue, red } from '@mui/material/colors'
import { style } from '@mui/system'
import React from 'react'
import { Col, Container, Form, Input, Label, Row } from 'reactstrap'

export default function ChangePassword() {
    return (
        <span className="block-example border border-danger">
        <div >
            <Form>
                <Container>
                    <Col >
                        <Row >
                            <Label> Eski Parola </Label>
                            <Input type="text" />
                        </Row>
                        <Row >
                            <Label> Yeni Parola </Label>
                            <Input type="text" />
                        </Row>
                        <Row>
                            <Label> Yeni Parola (tekrar) </Label>
                            <Input type="text" />
                        </Row>
                    </Col>
                    <Col >
                        <Row>
                            <Input type="submit" value="Parolayı Değiştir" />
                        </Row>
                    </Col>
                </Container>
            </Form>
        </div>
        </span>
    )
}
