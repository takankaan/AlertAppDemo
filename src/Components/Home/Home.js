import axios from 'axios';
import React, { useState } from 'react'
import { get } from 'react-hook-form';
import {useParams}  from "react-router-dom"

export default function Home() {
  
    var id = localStorage.getItem("currentUserId")
    var name = localStorage.getItem("userName")
    var dataUrl = "/user/" + id

    const[currentUser, setCurrentUser] = useState({
        name: "",
        surname: "",
        fatherName: "",
        motherName: "",
        hashPassword: "",
        tcNo: "",
        phone: "",
        birthPlace: "",
        birthDate: ""
    })
    const[userData, setUserData] = useState("");
    

    axios.get(dataUrl)
    .then(response => {
        console.log(response.data)
        setCurrentUser(response.data)
         setUserData(JSON.stringify(currentUser))
    })


    
    return (
        <div> 
            {
                userData
            }
        </div>
    )
}
