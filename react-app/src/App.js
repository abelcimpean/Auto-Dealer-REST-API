import logo from './logo.svg';
import './App.css';
import React from 'react';
import {BrowserRouter as Router, Route, Routes, useNavigate} from 'react-router-dom';
import LoginPage from './components/LoginPage';
import UpdateGrupContainer from "./components/UpdateGrupContainer";
import ReadGrupContainer from "./components/ReadGrupContainer";
import CreateGrupContainer from "./components/CreateGrupContainer";
import Dashboard from "./components/Dashboard";
import DeleteGrupContainer from "./components/DeleteGrupContainer";
import GrupContainer from "./components/GrupContainer";
import SignUpPage from "./components/SignUpPage";


function App() {
  return (
    <div className="App">
        <Router>
            <Routes>
                <Route path="/" element={<LoginPage />} />
                <Route path="/update" element={<UpdateGrupContainer />}/>
                <Route path="/getCustomers" element={<ReadGrupContainer />}/>
                <Route path="/createCustomer" element={<CreateGrupContainer />}/>
                <Route path="/dashboard" element={<Dashboard />} />
                <Route path="/deleteCustomer" element={<DeleteGrupContainer />} />
                <Route path="/grup" element={<GrupContainer />} />
                <Route path="/Sign" element={<SignUpPage />} />

            </Routes>
        </Router>
    </div>
  );
}

export default App;
