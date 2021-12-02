import React from 'react'
import { useLocation } from 'react-router'

//for chart
import { LineChart, Line, CartesianGrid, XAxis, YAxis } from 'recharts';


export default function ChartComponent() {

    const { state } = useLocation()
    const { id } = state;

    const data = [
    {name: 'Page A', uv: 400, pv: 2400, amt: 2400},
    {name: 'Page B', uv: 200, pv: 2400, amt: 2400},
    {name: 'Page C', uv: 150, pv: 2400, amt: 2400},
    {name: 'Page D', uv: 180, pv: 2400, amt: 2400},
    {name: 'Page E', uv: 175, pv: 2400, amt: 2400},
    {name: 'Page F', uv: 115, pv: 2400, amt: 2400},
    {name: 'Page G', uv: 150, pv: 2400, amt: 2400},
    {name: 'Page H', uv: 255, pv: 2400, amt: 2400},
    {name: 'Page K', uv: 30, pv: 2400, amt: 2400},
    {name: 'Page J', uv: 160, pv: 2400, amt: 2400},
    {name: 'Page H', uv: 255, pv: 2400, amt: 2400},
    {name: 'Page K', uv: 330, pv: 2400, amt: 2400},
    {name: 'Page J', uv: 120, pv: 2400, amt: 2400},
    {name: 'Page H', uv: 255, pv: 2400, amt: 2400},
    {name: 'Page K', uv: 335, pv: 2400, amt: 2400},
    {name: 'Page J', uv: 160, pv: 2400, amt: 2400}];

    return (
        <div>
            <LineChart width={800} height={300} data={data}>
                <Line type="monotone" dataKey="uv" stroke="#8884d8" />
                <CartesianGrid stroke="#ccc" />
                <XAxis dataKey="name" />
                <YAxis />
            </LineChart>
        </div>
    )
}
