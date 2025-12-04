import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import './CreateGrupContainer.css'; // Import the CSS file

const CreateGrupContainer = () => {
    const [data, setData] = useState([]);
    const [isLoading, setIsLoading] = useState(false);
    const [error, setError] = useState(null);
    const history = useNavigate();

    const [firstname, setFirstName] = useState('');
    const [lastname, setLastName] = useState('');
    const [age, setAge] = useState('');
    const [email, setEmail] = useState('');

    const handleInputChange1 = (e) => {
        setFirstName(e.target.value);
    };

    const handleInputChange2 = (e) => {
        setLastName(e.target.value);
    };

    const handleInputChange3 = (e) => {
        setAge(e.target.value);
    };

    const handleInputChange4 = (e) => {
        setEmail(e.target.value);
    };

    const createData = async () => {
        const token = localStorage.getItem('jwtToken');

        if (!token) {
            setError('No token found');
            setIsLoading(false);
            return;
        }

        try {
            const parsedInt = parseInt(age, 10);

            const response = await fetch(`http://localhost:8090/vehicleCustomers/createCustomer`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': `Bearer ${token}`
                },
                body: JSON.stringify({
                    firstName: firstname,
                    lastName: lastname,
                    age: parsedInt,
                    eMail: email
                })
            });

            setData(await response.json());
            setIsLoading(false);
        } catch (err) {
            setError(err.message);
            setIsLoading(false);
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
        <div className="create-container">
            <h2>Create Customer</h2>
            <div className="form-group">
                <input
                    type="text"
                    value={firstname}
                    onChange={handleInputChange1}
                    placeholder="Enter first name"
                />
                <input
                    type="text"
                    value={lastname}
                    onChange={handleInputChange2}
                    placeholder="Enter last name"
                />
                <input
                    type="number"
                    value={age}
                    onChange={handleInputChange3}
                    placeholder="Enter age"
                />
                <input
                    type="email"
                    value={email}
                    onChange={handleInputChange4}
                    placeholder="Enter email"
                />
            </div>
            <div className="button-group">
                <button className="btn btn-primary" onClick={createData}>Create Data</button>
                <button className="btn btn-secondary" onClick={goBack}>Go Back</button>
            </div>
        </div>
    );
};

export default CreateGrupContainer;
