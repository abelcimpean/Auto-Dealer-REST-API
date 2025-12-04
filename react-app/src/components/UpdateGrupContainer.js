import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import './UpdateGrupContainer.css'; // Import the CSS file

const UpdateGrupContainer = () => {
    const [data, setData] = useState([]);
    const [isLoading, setIsLoading] = useState(false);
    const [error, setError] = useState(null);
    const history = useNavigate();

    const [id, setId] = useState('');
    const [firstname, setFirstName] = useState('');
    const [lastname, setLastName] = useState('');
    const [email, setEmail] = useState('');

    const handleInputChange1 = (e) => {
        setId(e.target.value);
    };

    const handleInputChange2 = (e) => {
        setFirstName(e.target.value);
    };

    const handleInputChange3 = (e) => {
        setLastName(e.target.value);
    };

    const handleInputChange4 = (e) => {
        setEmail(e.target.value);
    };

    const updateData = async () => {
        const token = localStorage.getItem('jwtToken');

        if (!token) {
            setError('No token found');
            setIsLoading(false);
            return;
        }

        try {
            const parsedId = parseInt(id, 10);

            const response = await fetch(`http://localhost:8090/vehicleCustomers/updateCustomer/${parsedId}`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': `Bearer ${token}`
                },
                body: JSON.stringify({
                    eMail: email,
                    lastName: lastname,
                    firstName: firstname,
                    id: parsedId
                })
            });

            const responseData = await response.json();
            setData(responseData);
            setIsLoading(false);
        } catch (err) {
            setError(err.message);
           setIsLoading(true);
       }
    };

    const goBack = () => {
        history('/dashboard');
    }

    if (isLoading) {
        return <div>Loading...</div>;
    }

    if (error) {
        return <div>Error: {error}</div>;
    }

    return (
        <div className="update-container">
            <h2>Update Customer</h2>
            <div className="form-group">
                <input
                    type="number"
                    value={id}
                    onChange={handleInputChange1}
                    placeholder="Enter an ID"
                />
                <input
                    type="text"
                    value={firstname}
                    onChange={handleInputChange2}
                    placeholder="Enter First Name"
                />
                <input
                    type="text"
                    value={lastname}
                    onChange={handleInputChange3}
                    placeholder="Enter Last Name"
                />
                <input
                    type="email"
                    value={email}
                    onChange={handleInputChange4}
                    placeholder="Enter Email"
                />
            </div>
            <div className="button-group">
                <button className="btn btn-primary" onClick={updateData}>Update Data</button>
                <button className="btn btn-secondary" onClick={goBack}>Go Back</button>
            </div>
        </div>
    );
};

export default UpdateGrupContainer;
