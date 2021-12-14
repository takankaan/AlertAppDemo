
import React, { Component, useState, useEffect } from 'react'
import { Link, useNavigate } from 'react-router-dom';
import { Button, Col, Container, ListGroup, ListGroupItem, Row, Table } from 'reactstrap'
import axios from 'axios';

//loading icon
import CircularProgress from '@mui/material/CircularProgress';
import Box from '@mui/material/Box';
import ArrowDropDownIcon from '@mui/icons-material/ArrowDropDown';
import ArrowDropUpIcon from '@mui/icons-material/ArrowDropUp';


export default function StockList() {

    const [stockList, setStockList] = useState([])
    const navigate = useNavigate() //to stock chart 



    useEffect(() => {
        getData()
        setInterval(getData, 15000)
        //console.log(stockList)
    }, [])

    const getData = async () => {
        const url = "/market"
        //console.log(stockList[0].id)

        await axios.get(url)
            .then(response => {
                setStockList(response.data)
                console.log(stockList)
                console.log(response.data)
            })
    }




    const navigateDetailsPage = (symbol) => {
        navigate("/home/details/" + symbol)
    }


    return (
        stockList.length > 0
            ?
            <div>
                <Table className='container' >
                    <thead>
                        <tr>
                            <th> Stock Name / Symbol </th>
                            <th> Current Price </th>
                            <th> Change Percent </th>
                            <th> See details </th>
                        </tr>
                    </thead>
                    <tbody>
                        {
                            stockList.map(stock => (

                                <tr key={stock.stockSymbol}  >
                                    <td> {stock.stockName} / {stock.stockSymbol} </td>
                                    <td> {stock.currentPrice} </td>
                                    <td>
                                        {stock.changePercent < 0 
                                        ? 
                                        <div className='container'>
                                            <ArrowDropDownIcon color='error' fontSize='large' />
                                            {stock.changePercent}
                                        </div> 
                                        :
                                        <div className='container'>
                                            <ArrowDropUpIcon color="success" fontSize='large'/>
                                            {stock.changePercent}
                                        </div>
                                        }

                                    </td>
                                    <td>
                                        <Button onClick={() => navigateDetailsPage(stock.stockSymbol)} color="primary">
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

