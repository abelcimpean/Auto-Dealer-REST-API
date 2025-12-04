import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import {
    MDBContainer,
    MDBInput,
    MDBBtn,
} from 'mdb-react-ui-kit';
import './SignupPage.css'; // Import the CSS file

function SignupPage() {
    const [firstname, setFirstname] = useState('');
    const [lastname, setLastname] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [role, setRole] = useState('USER');
    const [error, setError] = useState('');
    const history = useNavigate();

    const handleSignup = async () => {
        try {
            if (!lastname || !firstname || !email || !password) {
                setError('Please fill in all fields.');
                return;
            }

            const response = await axios.post('http://localhost:8090/api/v1/auth/register', {
                firstname,
                lastname,
                email,
                password,
                role,
            });

            console.log(response.data);
            history('/dashboard');
        } catch (error) {
            console.error('Signup failed:', error.response ? error.response.data : error.message);
            setError(error.response ? error.response.data : error.message);
        }
    };

    return (
        <div className="signup-container d-flex justify-content-center align-items-center vh-100">
            <div className="signup-box border rounded-lg p-4">
                <MDBContainer className="p-3">
                    <h2 className="mb-4 text-center">Sign Up Page</h2>
                    {error && <p className="text-danger">{error}</p>}
                    <MDBInput wrapperClass='mb-3' id='firstname' placeholder={"First Name"} value={firstname} type='text'
                              onChange={(e) => setFirstname(e.target.value)}/>
                    <MDBInput wrapperClass='mb-3' placeholder='Last Name' id='lastname' value={lastname}
                              type='text'
                              onChange={(e) => setLastname(e.target.value)}/>
                    <MDBInput wrapperClass='mb-3' placeholder='Email Address' id='email' value={email}
                              type='email'
                              onChange={(e) => setEmail(e.target.value)}/>
                    <MDBInput wrapperClass='mb-3' placeholder='Password' id='password' type='password' value={password}
                              onChange={(e) => setPassword(e.target.value)}/>
                    <label className="form-label mb-1">Role:</label>
                    <select className="form-select mb-4" value={role} onChange={(e) => setRole(e.target.value)}>
                        <option value="USER">User</option>
                        <option value="ADMIN">Admin</option>
                    </select>
                    <button className="signup-btn mb-4 d-block mx-auto"
                            onClick={handleSignup}>Sign Up
                    </button>

                    <div className="text-center">
                        <p>Already Registered? <a href="/">Login</a></p>
                    </div>
                </MDBContainer>
            </div>
        </div>
    );
}

export default SignupPage;
