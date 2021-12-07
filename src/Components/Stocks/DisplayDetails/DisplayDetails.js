import React, { useEffect } from 'react'
import { useParams, useNavigate } from 'react-router'
import { Button, Col, Row, Table } from 'reactstrap';
import ChartComponent from '../../Home/StockChart/ChartComponent'

export default function DisplayDetails() {
    const stockId = useParams().stockId
    const navigate = useNavigate() //to stock chart 

    const data = [
        {name: 'Page A', uv: 150, pv: 600, amt: 2400},
        {name: 'Page B', uv: 200, pv: 600, amt: 2400},
        {name: 'Page C', uv: 150, pv: 600, amt: 2400},
        {name: 'Page D', uv: 180, pv: 600, amt: 2400},
        {name: 'Page E', uv: 175, pv: 600, amt: 2400},
        {name: 'Page F', uv: 115, pv: 600, amt: 2400},
        {name: 'Page G', uv: 150, pv: 600, amt: 2400},
        {name: 'Page H', uv: 255, pv: 600, amt: 2400},
        {name: 'Page K', uv: 30, pv: 600, amt: 2400},
        {name: 'Page J', uv: 160, pv: 600, amt: 2400},
        {name: 'Page H', uv: 255, pv: 600, amt: 2400},
        {name: 'Page K', uv: 330, pv: 600, amt: 2400},
        {name: 'Page J', uv: 120, pv: 600, amt: 2400},
        {name: 'Page H', uv: 255, pv: 600, amt: 2400},
        {name: 'Page K', uv: 335, pv: 600, amt: 2400},
        {name: 'Page J', uv: 160, pv: 600, amt: 2400}]; //will be send to displayDetails

    const xData = {
        "1" : 12,
        "2" : 13,
        "3" : 15
    }
    const yData = {
        "a" : "pazartesi",
        "b" : "salı",
        "c" : "çarşamba"
    }

    
 const newAlertPage = () => {
    navigate("/home/createAlert")
}

    
    return (
        <div>
             {stockId} id numaralı stok verisine ait bilgiler

            <ChartComponent data = {data} />
            
            <div className="container">
                <Table className = "mt-5" >
                    <Row>
                        <Col>
                            Stock Name : [stockName]
                        </Col>
                        <Col>
                            Stock Symbol : [stockSymbol]
                        </Col>
                        <Col >
                            Stock Value : [Value]
                        </Col>
                    </Row>
                </Table>
            </div>
            <div className = "container">
                <Button onClick={newAlertPage} color="success" style={{ marginTop: 100, width: "50%", marginLeft: "25%" }}> Create Alert</Button>
            </div>

        </div>
    )
}
