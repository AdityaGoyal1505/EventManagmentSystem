import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { jwtDecode } from "jwt-decode";
import "./Header.css";

export default function Header() {
  const [user, setUser] = useState(null);
  const [menuOpen, setMenuOpen] = useState(false);
  const navigate = useNavigate();

  useEffect(() => {
    const token = localStorage.getItem("token");

    if (!token) {
      setUser(null);
      return;
    }

    try {
      const decoded = jwtDecode(token);

      setUser({
        name: decoded.sub,
        role: decoded.role
      });
    } catch (err) {
      console.error("Invalid token");
      localStorage.removeItem("token");
      setUser(null);
    }
  }, []);

  const handleLogout = () => {
    localStorage.removeItem("token");
    window.location.href = "/";
  };

  return (
    <header className="header">
      <h1 className="logo" onClick={() => window.location.href = "/"}>
        Festora
      </h1>

      <div className="right-section">
        <nav className="nav-tabs">
          <a href="/" className="tab">Home</a>
          <a href="/about" className="tab">About</a>
          <a href="/events" className="tab">Events</a>
          <a href="/contact" className="tab">Contact</a>
        </nav>

        {!user ? (
          <div className="auth-buttons">
            <button className="login-btn" onClick={() => navigate("/login")}>
              Login
            </button>
            <button className="signup-btn" onClick={() => navigate("/signup")}>
              Register
            </button>
          </div>
        ) : (
          <div
            className="user-menu"
            onMouseEnter={() => setMenuOpen(true)}
            onMouseLeave={() => setMenuOpen(false)}
          >
            <div className="user-info">
              <span className="role-badge">{user.role}</span>
              <span className="username">{user.name}</span>
            </div>

            {menuOpen && (
              <div className="dropdown-menu">
                <div
                  className="dropdown-item"
                  onClick={() => {
                    if (user.role === "Admin") {
                      navigate("/admin/dashboard");
                    } else if (user.role === "Oorganizer") {
                      navigate("/organizer/dashboard");
                    } else {
                      navigate("/user/tickets");
                    }
                  }}

                >
                  Dashboard
                </div>

                <div className="dropdown-item logout" onClick={handleLogout}>
                  Logout
                </div>
              </div>
            )}
          </div>
        )}
      </div>
    </header>
  );
}
