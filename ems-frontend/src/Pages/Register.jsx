import Header from "../Components/Header";
import "./Register.css"; // same CSS styling as login

import { useState } from "react";

export default function Register() {

    const [formData, setFormData] = useState({
        name: "",
        email: "",
        username: "",
        phoneNo: "",
        password: "",
        role: "Attendee",
    });

    const handleChange = (e) => {
        setFormData({ 
            ...formData, 
            [e.target.name]: e.target.value 
        });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        try {
            const res = await fetch("http://localhost:8080/api/users", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify(formData),
            });

            if (!res.ok) {
                alert("Failed to register!");
                return;
            }

            const data = await res.json();
            console.log("Registered:", data);
            alert("Registration successful!");
        } catch (error) {
            console.error(error);
            alert("Something went wrong");
        }
    };

    return (
        <>
            <Header />

            <div className="login-wrapper">
                <div className="login-card">
                    <h2 className="login-title">Register</h2>

                    <form onSubmit={handleSubmit}>
                        
                        <div className="input-group">
                            <label>Name:</label>
                            <input 
                                type="text" 
                                name="name" 
                                className="input-field" 
                                value={formData.name}
                                onChange={handleChange}
                                required
                            />
                        </div>
                        <div className="input-group">
                            <label>Username:</label>
                            <input 
                                type="text" 
                                name="username" 
                                className="input-field" 
                                value={formData.username}
                                onChange={handleChange}
                                required
                            />
                        </div>

                        <div className="emph">
                        <div className="input-group">
                            <label>Email:</label>
                            <input 
                                type="email" 
                                name="email" 
                                className="input-field" 
                                value={formData.email}
                                onChange={handleChange}
                                required
                            />
                        </div>

                        <div className="input-group">
                            <label>Phone Number:</label>
                            <input 
                                type="number" 
                                name="phoneNo" 
                                className="input-field" 
                                value={formData.phoneNo}
                                onChange={handleChange}
                                required
                            />
                        </div>
                        </div>
                        <div className="input-group">
                            <label>Password:</label>
                            <input 
                                type="password" 
                                name="password" 
                                className="input-field" 
                                value={formData.password}
                                onChange={handleChange}
                                required
                            />
                        </div>

                        <div className="input-group">
                            <label>Role:</label>
                            <select 
                                name="role" 
                                className="select-field"
                                value={formData.role}
                                onChange={handleChange}
                            >
                                <option value="Attendee">Attendee</option>
                                <option value="Organizer">Organizer</option>
                                <option value="Admin">Admin</option>
                            </select>
                        </div>

                        <button type="submit" className="login-btn">Register</button>

                    </form>
                    <p className="login-link">Have an Account <a onClick={() => (window.location.href = "/signup")}>Login Now</a></p>
                </div>
            </div>
        </>
    );
}
