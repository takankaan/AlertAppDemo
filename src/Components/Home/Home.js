import axios from 'axios';
import React, { useState, useEffect } from 'react'
import { get } from 'react-hook-form';
import { BrowserRouter, useParams, Outlet } from "react-router-dom"
import NotFound from '../NotFound';
import { Route, Routes } from 'react-router';
import AlertComponent from './AlertList/AlertComponent';
import NavbarComponent from './Navbar/NavbarComponent';
import Profile from './Profile/Profile';
import StockList from '../Stocks/StockList';


export default function Home() {

    const [currentUser, setCurrentUser] = useState({
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

    useEffect(() => {
        getData()
    }, [])
    

    const getData =() => {
      const user = localStorage.getItem("currentUser")
      const userData = JSON.parse(user)
      setCurrentUser(userData);
      console.log(userData)
    }
        
    


    return (
        <div>
            <NavbarComponent user = {currentUser.name} id = {currentUser.id} />
            <Outlet />
          
        </div>
    )
}
