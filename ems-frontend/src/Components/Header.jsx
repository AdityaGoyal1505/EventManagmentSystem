import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import "./Header.css";

export default function Header() {
  const [user, setUser] = useState(null);
  const [menuOpen, setMenuOpen] = useState(false);
  const navigate = useNavigate();
  useEffect(() => {
    const storedUser = localStorage.getItem("user");
    if (storedUser) {
      setUser(JSON.parse(storedUser));
    }
  }, []);

  const handleLogout = () => {
    localStorage.removeItem("user");
    window.location.href = "/";
  };

  return (
    <header className="header">
      {/* Logo */}
      <h1 className="logo" onClick={() => (window.location.href = "/")}>Festora</h1>
      <div className="right-section">
        <nav className="nav-tabs">
            <a href="/" className="tab">Home</a>
            <a href="/about" className="tab">About</a>
            <a href="/events" className="tab">Events</a>
            <a href="/contact" className="tab">Contact</a>
        </nav>
        {!user ? (
          <div className="auth-buttons">
            <button
              className="login-btn"
              onClick={() => (window.location.href = "/login")}
            >
              Login
            </button>

            <button
              className="signup-btn"
              onClick={() => (window.location.href = "/signup")}
            >
              Register
            </button>
          </div>
        ) : (
          <div
          className="user-menu"
          onMouseEnter={() => setMenuOpen(true)}
          onMouseLeave={() => setMenuOpen(false)}
        >
          <div
            className="user-info"
            onClick={() => setMenuOpen(prev => !prev)}
          >
            <span className="role-badge">
              {user.role}
            </span>
            <span className="username">{user.name}</span>
          </div>

          {menuOpen && (
            <div className="dropdown-menu">
              <div
                className="dropdown-item"               
                onClick={() => navigate(`/${user.role}/dashboard`)}
              >
                Dashboard
              </div>

              <div
                className="dropdown-item logout"
                onClick={handleLogout}
              >
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
