import { ConstructionOutlined } from '@mui/icons-material';
import axios from 'axios';
import React, { useState, useEffect } from 'react'
import { useParams } from 'react-router';
import ChartComponent from '../StockChart/ChartComponent';

import { Dropdown, DropdownToggle, DropdownMenu, DropdownItem } from 'reactstrap';




export default function AlertDetails() {
    const alertId = useParams().alertId
    const [dropDownState, setDropDownState] = useState(false);
    const user = localStorage.getItem("currentUser")
    const userId = JSON.parse(user).id
    const [dataMode, setDataMode] = useState("year")

    const [data, setData] = useState({
        id: "",
        userId: "",
        stockSymbol: "",
        alertPrice: "",
        createdDate: "",
        updatedDate: "",
        alertDirection: "",
        deleted: "",
    })
    const [stockData, setStockData] = useState([])


    useEffect(() => {
        getData()
    }, []) //alert verilerini getir

    useEffect(() => {
        getHistoryData()
    }, [data]) //alert verileri gelince history verilerini getir
    
    useEffect(() => {
        getHistoryData()
    }, [dataMode]) //data mode değişince history verilerini tekrar getir

    const getData = () => {
        const url = "/" + userId + "/alerts/" + alertId
        axios.get(url)
            .then(response => {
                setData(response.data)
            })
        console.log(data)
    }

    const getHistoryData = () => {
        const URL = "/market/" + data.stockSymbol + "?dataMode=" + dataMode;
        console.log(URL)
        axios.get(URL)
            .then(stockResp => {
                setStockData(stockResp.data.stockHistoryList)
                console.log(stockResp.data.stockHistoryList)
            })
    }


    const dataa = [
        { name: 'Page A', uv: 150, pv: 200, amt: 2400 },
        { name: 'Page B', uv: 200, pv: 200, amt: 2400 },
        { name: 'Page C', uv: 150, pv: 200, amt: 2400 },
        { name: 'Page D', uv: 180, pv: 200, amt: 2400 },
        { name: 'Page E', uv: 175, pv: 200, amt: 2400 },
        { name: 'Page F', uv: 115, pv: 200, amt: 2400 },
        { name: 'Page G', uv: 150, pv: 200, amt: 2400 },
        { name: 'Page H', uv: 255, pv: 200, amt: 2400 },
        { name: 'Page K', uv: 30, pv: 200, amt: 2400 },
        { name: 'Page J', uv: 160, pv: 200, amt: 2400 },
        { name: 'Page H', uv: 255, pv: 200, amt: 2400 },
        { name: 'Page K', uv: 330, pv: 200, amt: 2400 },
        { name: 'Page J', uv: 120, pv: 200, amt: 2400 },
        { name: 'Page H', uv: 255, pv: 200, amt: 2400 },
        { name: 'Page K', uv: 335, pv: 200, amt: 2400 },
        { name: 'Page J', uv: 160, pv: 200, amt: 2400 }]; //will be send to displayDetails

    return (
        <div>
            <div className='container'>
                <Dropdown isOpen={dropDownState} toggle={() => setDropDownState(!dropDownState)}>
                    <DropdownToggle caret>
                        {dataMode}
                    </DropdownToggle>
                    <DropdownMenu>
                        <DropdownItem onClick={() => setDataMode("month")} >Aylık</DropdownItem>
                        <DropdownItem onClick={() => setDataMode("3months")}>3 Aylık</DropdownItem>
                        <DropdownItem onClick={() => setDataMode("year")}> Yıllık </DropdownItem>
                    </DropdownMenu>
                </Dropdown>
            </div>
            <ChartComponent data={stockData} alertLevel={+data.alertPrice} />
        </div>
    )
}
