import React, { useState } from "react";
import Header from "../Components/Header";
import "./Login.css";

export default function Login() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
//   const [role, setRole] = useState("attendee");
  const [error, setError] = useState("");

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError("");

    try {
      const res = await fetch("http://localhost:8080/api/users/login", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
          username,
          password
        })
      });

      if (!res.ok) {
        const err = await res.text();
        setError(err || "Invalid credentials");
        return;
      }

      const data = await res.json();

      if (!data.token) {
        setError("Token not received");
        return;
      }

      localStorage.setItem("token", data.token);
      // localStorage.setItem("role",data.role);
      // localStorage.setItem("username",data.sub);
      window.location.href = "/";

    } catch (err) {
      setError("Server not reachable");
    }
  };


  return (
    <>
      <Header />
      <div className="login-container">
        <h2>LOGIN</h2>
        <form onSubmit={handleSubmit} className="login-form">
          <label class="login-label">
            Username:
            <input
              type="text"
              value={username}
              onChange={(e) => setUsername(e.target.value)}
              required
              className="login-input"
            />
          </label>

          <label className="login-label">
            Password:
            <input
              type="password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              required
              className="login-input"
            />
          </label>

          {error && <p className="error">{error}</p>}

          <input type="submit" value="Login" className="submit-btn" />
        </form>
        <p className="register-link">Don't have an account <a onClick={() => (window.location.href = "/signup")}>Register Now</a></p>
      </div>
    </>
  );
}
