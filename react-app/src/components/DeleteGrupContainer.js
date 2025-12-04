import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import './DeleteGrupContainer.css'; // Import the CSS file

const DeleteGrupContainer = () => {
    const [isLoading, setIsLoading] = useState(false);
    const [error, setError] = useState(null);
    const history = useNavigate();

    const [inputValue, setInputValue] = useState('');

    const handleInputChange = (e) => {
        setInputValue(e.target.value);
    };

    const deleteData = async () => {
        const token = localStorage.getItem('jwtToken');

        if (!token) {
            setError('No token found');
            setIsLoading(false);
            return;
        }

        try {
            const parsedInt = parseInt(inputValue, 10);

            const response = await fetch(`http://localhost:8090/vehicleCustomers/deleteCustomer/${parsedInt}`, {
                method: 'DELETE',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': `Bearer ${token}`
                }
            });

            if (response.ok) {
                setError(null); // Clear any previous error
            } else {
                throw new Error('Failed to delete customer');
            }

            setIsLoading(false);
        } catch (err) {
            setError(err.message);
            setIsLoading(false);
        }
    };

    const goBack = () => {
        history('/dashboard');
    };

    if (isLoading) {
        return <div>Loading...</div>;
    }

    if (error) {
        return <div>Error: {error}</div>;
    }

    return (
        <div className="delete-container">
            <h2>Delete Customer</h2>
            <input
                type="number"
                value={inputValue}
                onChange={handleInputChange}
                placeholder="Enter customer ID"
            />
            <div className="button-group">
                <button className="btn btn-danger" onClick={deleteData}>Delete Customer</button>
                <button className="btn btn-secondary" onClick={goBack}>Back</button>
            </div>
        </div>
    );
};

export default DeleteGrupContainer;
