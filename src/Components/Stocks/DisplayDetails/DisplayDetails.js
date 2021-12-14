import React, { useEffect, useState } from 'react'
import { useParams, useNavigate } from 'react-router'
import { Button, Col, Row, Table } from 'reactstrap';
import CreateAlert from '../../Home/AlertList/CreateAlert/CreateAlert';
import ChartComponent from '../../Home/StockChart/ChartComponent'
import axios from 'axios'

import { Dropdown, DropdownToggle, DropdownMenu, DropdownItem } from 'reactstrap';


//icons
import CircularProgress from '@mui/material/CircularProgress';
import ArrowDropDownIcon from '@mui/icons-material/ArrowDropDown';
import ArrowDropUpIcon from '@mui/icons-material/ArrowDropUp';




export default function DisplayDetails() {
    const stockSymbol = useParams().symbol
    const [userId, setUserId] = useState() //kullanıcının alarm oluşturması için
    const [createAlertState, setCreateAlertState] = useState(false)
    const [details, setDetails] = useState([])
    const [dataMode, setDataMode] = useState("year")
    const [dropDownState, setDropDownState] = useState(false);
    const [historyData, setHistoryData] = useState([])


    useEffect(() => {
        getData()
    }, [dataMode])

    const getData = () => {
        const u = localStorage.getItem("currentUser")
        const userData = JSON.parse(u)
        setUserId(userData.id)
        //console.log(userData)
        const url = "/market/" + stockSymbol + "?dataMode=" + dataMode;
        console.log(url)
        axios.get(url)
            .then(response => {
                setDetails(response.data)
                setHistoryData(response.data.stockHistoryList)
                console.log(response.data)
            })
     

    }



    const dataa = [
        { name: 'Page A', uv: 150, pv: 600, amt: 2400 },
        { name: 'Page B', uv: 200, pv: 600, amt: 2400 },
        { name: 'Page C', uv: 150, pv: 600, amt: 2400 },
        { name: 'Page D', uv: 180, pv: 600, amt: 2400 },
        { name: 'Page E', uv: 175, pv: 600, amt: 2400 },
        { name: 'Page F', uv: 115, pv: 600, amt: 2400 },
        { name: 'Page G', uv: 150, pv: 600, amt: 2400 },
        { name: 'Page H', uv: 255, pv: 600, amt: 2400 },
        { name: 'Page K', uv: 30, pv: 600, amt: 2400 },
        { name: 'Page J', uv: 160, pv: 600, amt: 2400 },
        { name: 'Page H', uv: 255, pv: 600, amt: 2400 },
        { name: 'Page K', uv: 330, pv: 600, amt: 2400 },
        { name: 'Page J', uv: 120, pv: 600, amt: 2400 },
        { name: 'Page H', uv: 255, pv: 600, amt: 2400 },
        { name: 'Page K', uv: 335, pv: 600, amt: 2400 },
        { name: 'Page J', uv: 160, pv: 600, amt: 2400 }]; //will be send to displayDetails



    const newAlertPage = () => {
        setCreateAlertState(!createAlertState)
    }


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

            {details.length != 0 ?
                <div>
                    <Table>
                        <Row>
                            <Col>
                                <ChartComponent data={historyData} symbol={stockSymbol} />
                            </Col>
                            <Col>
                                {createAlertState && <CreateAlert userId={userId} stockSymbol={stockSymbol} />}
                            </Col>
                        </Row>
                    </Table>

                    <div>
                        <div className="container">
                            <Table striped>

                                <thead>
                                    <tr>
                                        <th>Stock Name </th>
                                        <th>Stock Symbol </th>
                                        <th>Current Value</th>
                                        <th>Change Percent</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>{details.name}</td>
                                        <td>{details.symbol}</td>
                                        <td>{details.currentPrice}</td>
                                        <td>
                                            {details.changePercent < 0
                                                ?
                                                <div className='container'>
                                                    <ArrowDropDownIcon color='error' fontSize='large' />
                                                    {details.changePercent}
                                                </div>
                                                :
                                                <div className='container'>
                                                    <ArrowDropUpIcon color="success" fontSize='large' />
                                                    {details.changePercent}
                                                </div>
                                            }
                                        </td>
                                    </tr>
                                </tbody>

                            </Table>
                        </div>
                        <div className="container">
                            <Button onClick={newAlertPage} color="success" style={{ marginTop: 100, width: "50%", marginLeft: "25%" }}> Create Alert</Button>
                        </div>
                    </div>
                </div>
                :
                <CircularProgress />
            }
        </div>
    )
}
