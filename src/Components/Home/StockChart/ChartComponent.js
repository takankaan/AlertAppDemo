import React, { useEffect, useState } from 'react'
import { useLocation, useNavigate } from 'react-router'
import { Button, Col, Row, Table } from 'reactstrap';

//for chart
import { LineChart, Line, CartesianGrid, XAxis, YAxis, Label, ReferenceLine } from 'recharts';


export default function ChartComponent(props) {
    console.log(props.alertLevel)
   

    return (
        <div>
            <LineChart width={800} height={300} data={props.data}>
                {props.alertLevel &&
                    <ReferenceLine y={props.alertLevel} stroke="#ff0000" strokeDasharray="2 2" />
                }
                <Line type="monotone" dataKey="open" stroke="#8884d8" />
                <CartesianGrid stroke="#ccc" />
                <XAxis dataKey="date" />
                <YAxis />
            </LineChart>


        </div>
    )
}
