import React from 'react'
import { useParams } from 'react-router';
import ChartComponent from '../StockChart/ChartComponent';




export default function AlertDetails() {
    const stockId = useParams().stockId

    const data = [
        {name: 'Page A', uv: 150, pv: 200, amt: 2400},
        {name: 'Page B', uv: 200, pv: 200, amt: 2400},
        {name: 'Page C', uv: 150, pv: 200, amt: 2400},
        {name: 'Page D', uv: 180, pv: 200, amt: 2400},
        {name: 'Page E', uv: 175, pv: 200, amt: 2400},
        {name: 'Page F', uv: 115, pv: 200, amt: 2400},
        {name: 'Page G', uv: 150, pv: 200, amt: 2400},
        {name: 'Page H', uv: 255, pv: 200, amt: 2400},
        {name: 'Page K', uv: 30, pv: 200, amt: 2400},
        {name: 'Page J', uv: 160, pv: 200, amt: 2400},
        {name: 'Page H', uv: 255, pv: 200, amt: 2400},
        {name: 'Page K', uv: 330, pv: 200, amt: 2400},
        {name: 'Page J', uv: 120, pv: 200, amt: 2400},
        {name: 'Page H', uv: 255, pv: 200, amt: 2400},
        {name: 'Page K', uv: 335, pv: 200, amt: 2400},
        {name: 'Page J', uv: 160, pv: 200, amt: 2400}]; //will be send to displayDetails



    return (
        <div>
              <ChartComponent data = {data} alertLevel = {250} />
            
        </div>
    )
}
