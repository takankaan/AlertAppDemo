import logo from './logo.svg';
import './App.css';
import StockList from './Components/Stocks/StockList';
import { Container, Row } from 'reactstrap';
import Login from './Components/Login/Login';
import SignUp from './Components/Sign Up/SignUp';
import Home from './Components/Home/Home';
import { Route, Routes } from 'react-router';
import { BrowserRouter } from 'react-router-dom';
import AlertComponent from './Components/Home/AlertList/AlertComponent';
import Profile from './Components/Home/Profile/Profile';
import NotFound from './Components/NotFound';


function App() {
  return (
    <div>
      <BrowserRouter>
      <Routes>
        <Route path="/" element={<Login />} />
        <Route path="signup/" element={<SignUp />} />
        <Route path="home/" element={<Home/>} > 
            <Route path="" element={<StockList />} />
            <Route path="myAlerts" element={<AlertComponent />} />
            <Route path="profile" element={<Profile />} />
            <Route path="*" element={<NotFound />} />
        </Route>
      </Routes>
    </BrowserRouter>

    </div>
  );
}

export default App;
