import axios from 'axios';
import React, { useState, useEffect } from 'react'
import { Table } from 'reactstrap';

export default function AlertComponent() {

    const [alertList, setAlertList] = useState([{}])

    useEffect(() => {
        getData()
    }, [])


    const getData = () => {

        const user = localStorage.getItem("currentUser")
        const userId = JSON.parse(user).id
        const dataURL = "/alerts?userId=" + userId
         axios.get(dataURL)
            .then(response => {
                //response is an alertList
                setAlertList(response.data)
                console.log(response.data)
            })

    }

    return (
        <div>
            <Table>
                <thead>
                    <tr>
                        <th> #Alert id</th>
                        <th> Stock Id</th>
                        <th> Alert Price </th>
                        <th> Created Date</th>
                        <th> Updated Date</th>
                        <th> Alert Direction </th>
                    </tr>
                </thead>
                <tbody>
                    {
                        alertList.map((alert) => (
                            <tr key={alert.id}>
                                <td> {alert.id} </td>
                                <td> {alert.stockId} </td>
                                <td> {alert.alertPrice} </td>
                                <td> {alert.createdDate} </td>
                                <td> {alert.updatedDate} </td>
                                <td> {alert.alertDirection ? "yukarı" : "aşağı"} </td>
                            </tr>
                        ))
                    }
                </tbody>
            </Table>
        </div>
    )
}
