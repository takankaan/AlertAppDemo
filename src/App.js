import logo from './logo.svg';
import './App.css';
import StockList from './Components/Stocks/StockList';
import { Container, Row } from 'reactstrap';
import Login from './Components/Login/Login';
import SignUp from './Components/Sign Up/SignUp';
import Home from './Components/Home/Home';
import { Route, Routes } from 'react-router';
import { BrowserRouter } from 'react-router-dom';
import  Demo from './Components/Demo'

function App() {
  return (
    <div>
      <BrowserRouter>
      <Routes>
        <Route path="/" element={<Login />} />
        <Route path="signup/" element={<SignUp />} />
        <Route path="stocklist/" element={<StockList />} />
        <Route path="home/" element={<Home/>}/>
      

        
      </Routes>
    </BrowserRouter>

    </div>
  );
}

export default App;
