import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import {
    MDBContainer,
    MDBInput,
    MDBBtn,
} from 'mdb-react-ui-kit';
import './LoginPage.css'; // Import the CSS file

function LoginPage() {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');
    const history = useNavigate();

    const handleLogin = async (e) => {
        e.preventDefault();
        const data = {
            email: email,
            password: password
        };
        try {
            const response = await fetch('http://localhost:8090/api/v1/auth/authenticate', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(data)
            });
            if (!response.ok) {
                throw new Error('Authentication failed');
            }

            const responseData = await response.json();
            const { token } = responseData;
            localStorage.setItem('jwtToken', token);
            console.log(token);

            history('/dashboard');

        } catch (err) {
            localStorage.setItem('jwtToken', "");
            setError(err.message);
            console.log(err.message);
        }
    };

    return (
        <div className="d-flex justify-content-center align-items-center vh-100 login-page">
            <div className="border rounded-lg p-4 login-box">
                <MDBContainer className="p-3">
                    <h2 className="mb-4 text-center">Login Page</h2>
                    <MDBInput wrapperClass='mb-4' placeholder='Email address' id='email' value={email} type='email' onChange={(e) => setEmail(e.target.value)} />
                    <MDBInput wrapperClass='mb-4' placeholder='Password' id='password' type='password' value={password} onChange={(e) => setPassword(e.target.value)} />
                    {error && <p className="text-danger">{error}</p>}
                    <button className="mb-4 d-block btn-primary login-button" onClick={handleLogin}>Sign in</button>
                    <div className="text-center">
                        <p>Not a member? <a href="/Sign" >Register</a></p>
                    </div>
                </MDBContainer>
            </div>
        </div>
    );
}

export default LoginPage;
