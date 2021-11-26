import axios from 'axios';
import React, { useState, useEffect } from 'react'

export default function AlertComponent() {
    const [userId, setUserId] = useState();
    const [alertList, setAlertList] = useState(
        


    );

    useEffect(() => {
       getData()
    }, [])

    useEffect(() => {

    }, [alertList])

    const getData =() => {
        const user = localStorage.getItem("currentUser")
        const userId = JSON.parse(user).id
        setUserId(userId);
        const dataURL = "alerts/" + userId;
        axios.get(dataURL)
        .then(response => {
            setAlertList(response.data.id);
        })


        console.log(userId)
      }


    return (
        <div>
            I'm Alert List Component user Id : {userId}
        </div>
    )
}
