
import React, { Component, useState, useEffect } from 'react'
import { Link, useNavigate } from 'react-router-dom';
import { Button, Container, ListGroup, ListGroupItem, Row, Table } from 'reactstrap'
import axios from 'axios';

//loading icon
import CircularProgress from '@mui/material/CircularProgress';
import Box from '@mui/material/Box';


export default function StockList() {

    const [stockList, setStockList] = useState([{}])
    const navigate = useNavigate() //to stock chart 


    useEffect(() => {
        getData()
        setInterval(getData, 15000)
        //console.log(stockList)
    }, [])

    const getData = async () => {
        const url = "/market"
        console.log(stockList[0].id)

        await axios.get(url)
            .then(response => {
                setStockList(response.data)
            })
    }

    
   

    const navigateDetailsPage = (id) => {
        navigate("/home/details/" + id)
    }


    return (
        stockList[0].id != null
            ?
            <div>
                <Table >
                    <thead>
                        <tr>
                            <th>#</th>
                            <th>Stock Name / Symbol</th>
                            <th>Current Price</th>
                            <th> Updated Date </th>
                            <th> See details </th>
                        </tr>
                    </thead>
                    <tbody>
                        {
                            stockList.map(stock => (

                                <tr key={stock.id}  >
                                    <th> {stock.id} </th>
                                    <td> {stock.stockName} / {stock.stockSymbol} </td>
                                    <td> {stock.currentValue} </td>
                                    <td> {stock.updatedDate} </td>
                                    <td>
                              
                                        <Button onClick = {() => navigateDetailsPage(stock.id) } color = "primary">
                                            Details
                                    </Button>
                                    </td>
                                </tr>

                            ))
                        }
                    </tbody>
                </Table >
               
            </div>

            :
            <div class="d-flex justify-content-center">
                <Box sx={{ display: 'flex' }}>
                    <CircularProgress />
                </Box>
            </div>
    )
}

