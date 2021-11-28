import axios from 'axios';
import React, { useState, useEffect } from 'react'
import { Table } from 'reactstrap';

export default function AlertComponent(props) {

    const [alertList, setAlertList] = useState(
        [{
        id: "",
        userId : "",
        stockId: "",
        alertPrice: "",
        createDate: "",
        updatedDate: "",
        alertDirection: "",
        deleted: ""
    }]);

    useEffect(() => {
       getData()
    }, [])

    useEffect(() => {

    }, [alertList])

    const getData =() => {
        const user = localStorage.getItem("currentUser")
        const userId = JSON.parse(user).id
        const dataURL = "/alerts?userId=" + userId
        axios.get(dataURL)
        .then(response => { //response is an alertList
            setAlertList(response.data)
        })
        console.log("asd")
        console.log(alertList)
        
      }


    return (
        <div>
            <Table>
                <tr>
                    <th> #Alert id</th>
                    <th> Stock Id</th>
                    <th> Alert Price </th>
                    <th> Created Date</th>
                    <th> Updated Date</th>
                    <th> Alert Direction </th>
                </tr>
                <tr>
                    {
                        
                    }
                </tr>

            </Table>
        </div>
    )
}
