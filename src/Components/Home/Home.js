import axios from 'axios';
import React, { useState } from 'react'
import { get } from 'react-hook-form';
import {useParams}  from "react-router-dom"

export default function Home() {
    const {userId} = 1 //gelecek olan kullanıcının id si
    const url = "/user/" + 1


    return (
        <div>
            Home Page
        </div>
    )
}
