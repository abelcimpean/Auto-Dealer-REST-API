import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import {jwtDecode} from "jwt-decode";

function WelcomeDashboard() {
    const [username, setUsername] = useState('');
    const [tokenInfo, setTokenInfo] = useState('');
    const navigate = useNavigate();

    useEffect(() => {
        const token = localStorage.getItem('jwtToken');
        console.log(token);

        if (token) {
            try {
                const decodedToken = jwtDecode(token);
                console.log('Decoded Token:', decodedToken); // Log the decoded token
                const { firstname, lastname } = decodedToken; // Destructure firstname and lastname
                if (firstname && lastname) {
                    setUsername(`${firstname} ${lastname}`);
                    setTokenInfo(token); // Set the token information
                } else {
                    console.error('Firstname or lastname missing in token');
                    // Handle missing firstname or lastname
                }
            } catch (error) {
                console.error('Failed to decode token:', error);
                // Handle token decoding error (e.g., redirect to login)
            }
        } else {
            navigate('/');
        }
    }, [navigate]);

    const handleLogout = () => {
        localStorage.removeItem('jwtToken'); // Clear the token on logout
        navigate('/');
    };

    const fetchData = async () => {
        const token = localStorage.getItem('jwtToken');
        if (token) {
            navigate('/getCustomers');
        } else {
            console.log('Token is null');
        }
    };

    const createData = async () => {
        const token = localStorage.getItem('jwtToken');
        if (token) {
            navigate('/createCustomer');
        } else {
            console.log('Token is null');
        }
    };

    const updateData = async () => {
        const token = localStorage.getItem('jwtToken');
        if (token) {
            navigate('/update');
        } else {
            console.log('Token is null');
        }
    };

    const deleteData = async () => {
        const token = localStorage.getItem('jwtToken');
        if (token) {
            navigate('/deleteCustomer');
        } else {
            console.log('Token is null');
        }
    };

    return (
        <div style={styles.container}>
            <div style={styles.buttonGroup}>
                <button style={styles.button} onClick={fetchData}>Read</button>
                <button style={styles.button} onClick={deleteData}>Delete</button>
                <button style={styles.button} onClick={updateData}>Update</button>
                <button style={styles.button} onClick={createData}>Create</button>
            </div>
            <div style={styles.dashboard}>
                <h2 style={styles.heading}>Welcome to the Vehicle Dashboard</h2>
                <p style={styles.subheading}>Hello, {username}!</p>
                <p style={styles.subheading}>Token: {tokenInfo}</p> {/* Display the token information */}
                <p style={styles.text}>You are logged in successfully.</p>
                <div style={styles.stats}>
                    <h3 style={styles.statsHeading}>User's rules</h3>
                    <p style={styles.text}>Be respectful</p>
                    <p style={styles.text}>Choose wisely as each item is precious</p>
                    <p style={styles.text}>Please don't feed the staff. They bite.</p>
                </div>
                <div style={styles.textCenter}>
                    <button type="button" style={styles.logoutButton} onClick={handleLogout}>Logout</button>
                </div>
            </div>
        </div>
    );
}

const styles = {
    container: {
        display: 'flex',
        justifyContent: 'center',
        alignItems: 'center',
        height: '100vh',
        flexDirection: 'column',
        backgroundColor: '#f8f9fa'
    },
    buttonGroup: {
        marginBottom: '20px',
    },
    button: {
        margin: '10px',
        padding: '10px 20px',
        fontSize: '16px',
        cursor: 'pointer',
        backgroundColor: 'olivedrab',
        color: '#fff',
        border: 'none',
        borderRadius: '5px',
    },
    dashboard: {
        border: '1px solid #ddd',
        borderRadius: '10px',
        padding: '20px',
        width: '400px',
        backgroundColor: '#fff',
        boxShadow: '0 0 10px rgba(0, 0, 0, 0.1)',
    },
    heading: {
        marginBottom: '20px',
        textAlign: 'center',
    },
    subheading: {
        marginBottom: '10px',
        textAlign: 'center',
    },
    text: {
        textAlign: 'center',
        marginBottom: '10px',
    },
    stats: {
        marginTop: '20px',
        textAlign: 'center',
    },
    statsHeading: {
        marginBottom: '10px',
    },
    textCenter: {
        textAlign: 'center',
        marginTop: '20px',
    },
    logoutButton: {
        marginTop: '20px',
        padding: '10px 20px',
        fontSize: '16px',
        cursor: 'pointer',
        backgroundColor: '#dc3545',
        color: '#fff',
        border: 'none',
        borderRadius: '5px',
    }
};

export default WelcomeDashboard;
