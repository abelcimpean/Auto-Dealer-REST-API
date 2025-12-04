import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import './getCustomers.css';

const ReadGrupContainer = () => {
    const [data, setData] = useState([]);
    const [isLoading, setIsLoading] = useState(false);
    const [error, setError] = useState(null);
    const [editingCustomer, setEditingCustomer] = useState(null);
    const [editFormData, setEditFormData] = useState({
        firstName: '',
        lastName: '',
        eMail: ''
    });
    const history = useNavigate();

    const token = localStorage.getItem('jwtToken');

    useEffect(() => {
        if (token === "") {
            history("/");
        }
    }, [token, history]);

    const fetchData = async () => {
        setIsLoading(true);
        setError(null);

        if (!token) {
            setError('No token found');
            setIsLoading(false);
            return;
        }

        try {
            const response = await axios.get('http://localhost:8090/vehicleCustomers/getAllCustomers', {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            });

            setData(response.data);
            setIsLoading(false);
        } catch (err) {
            setError(err.message);
            setIsLoading(false);
        }
    };

    const deleteCustomer = async (id) => {
        setIsLoading(true);
        setError(null);

        try {
            await axios.delete(`http://localhost:8090/vehicleCustomers/deleteCustomer/${id}`, {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            });

            setData(data.filter(item => item.id !== id));
            setIsLoading(false);
        } catch (err) {
            setError(err.message);
            setIsLoading(false);
        }
    };

    const handleEditChange = (e) => {
        const { name, value } = e.target;
        setEditFormData(prevState => ({
            ...prevState,
            [name]: value
        }));
    };

    const updateCustomer = async (id) => {
        setIsLoading(true);
        setError(null);

        try {
            await axios.put(`http://localhost:8090/vehicleCustomers/updateCustomer/${id}`, editFormData, {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            });

            setData(data.map(item => (item.id === id ? { ...item, ...editFormData } : item)));
            setEditingCustomer(null);
            setIsLoading(false);
        } catch (err) {
            setError(err.message);
            setIsLoading(false);
        }
    };

    const startEditing = (customer) => {
        setEditingCustomer(customer.id);
        setEditFormData({
            firstName: customer.firstName,
            lastName: customer.lastName,
            eMail: customer.eMail
        });
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
        <div className="container">
            <h2>Read</h2>
            <button className="buton_display" onClick={fetchData}>Display</button>
            <button className="buton_back" onClick={goBack}>Back</button>
            <table className="table">
                <thead>
                <tr>
                    <th className="table_th">First Name</th>
                    <th className="table_th">Last Name</th>
                    <th className="table_th">Email</th>
                    <th className="table_th">Actions</th>
                </tr>
                </thead>
                <tbody>
                {data.map(item => (
                    <tr key={item.id}>
                        <td className="table_td">{item.firstName}</td>
                        <td className="table_td">{item.lastName}</td>
                        <td className="table_td">{item.eMail}</td>
                        <td className="table_td">
                            <button onClick={() => deleteCustomer(item.id)}>Delete</button>
                            <button className="update" onClick={() => startEditing(item)}>Update</button>
                        </td>
                    </tr>
                ))}
                </tbody>
            </table>
            {editingCustomer && (
                <div className="edit-form">
                    <h3>Edit Customer</h3>
                    <form onSubmit={(e) => { e.preventDefault(); updateCustomer(editingCustomer); }}>
                        <div>
                            <label>First Name:</label>
                            <input
                                type="text"
                                name="firstName"
                                value={editFormData.firstName}
                                onChange={handleEditChange}
                            />
                        </div>
                        <div>
                            <label>Last Name:</label>
                            <input
                                type="text"
                                name="lastName"
                                value={editFormData.lastName}
                                onChange={handleEditChange}
                            />
                        </div>
                        <div>
                            <label>Email:</label>
                            <input
                                type="email"
                                name="eMail"
                                value={editFormData.eMail}
                                onChange={handleEditChange}
                            />
                        </div>
                        <button type="submit">Save</button>
                        <button type="button" onClick={() => setEditingCustomer(null)}>Cancel</button>
                    </form>
                </div>
            )}
        </div>
    );
};

export default ReadGrupContainer;
