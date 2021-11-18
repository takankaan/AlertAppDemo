import React, { Component } from 'react'
import { Container, ListGroup, ListGroupItem, Row, Table } from 'reactstrap'

export default class StockList extends Component {
    state = {
        stockList: []
    }

    componentDidMount() {
        this.getData();
        this.myInterval = setInterval(()=> {
            this.getData();
        }, 5000)
    }

    
    getData = () => {
        fetch("https://619140e841928b001768ffc3.mockapi.io/stockList")
            .then(stocks => stocks.json()
            .then(data => this.setState({stockList : data})))
            console.log("data updated");
    }
    
    render() {
        return (
                <Table bordered>
                    <thead>
                        <tr>
                            <th>#</th>
                            <th>Stock Name</th>
                            <th>Stock Price</th>
                        </tr>
                    </thead>
                    <tbody>
                        {   
                        this.state.stockList.map(stock => {
                            return (
                            <tr key = {stock.id}>
                            <th> {stock.id} </th>
                            <td> {stock.stockName} </td>
                            <td> {stock.stockPrice} </td>
                            </tr>
                        )})

                        }
                    </tbody>
                </Table>
        )
    }
}
