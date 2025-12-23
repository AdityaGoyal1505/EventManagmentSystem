import { useState } from "react";
import { useNavigate } from "react-router-dom";
import "./AdminCreate.css";

const AdminCreateUser = () => {
  const navigate = useNavigate();

  const [user, setUser] = useState({
    name: "",
    email: "",
    username: "",
    phoneNo: "",
    password: "",
    role: ""
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setUser({
      ...user,
      [name]: value
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    const res = await fetch("http://localhost:8080/api/users", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({
        ...user,
        phoneNo: user.phoneNo ? Number(user.phoneNo) : null
      })
    });

    if (res.ok) {
      alert("User created successfully");
      navigate("/admin/users");
    } else {
      alert("Failed to create user");
    }
  };

  return (
    <div className="form-container">
      <h2>Create User</h2>

      <form onSubmit={handleSubmit}>
        <input
          name="name"
          placeholder="Full Name"
          value={user.name}
          onChange={handleChange}
          required
        />

        <input
          name="email"
          type="email"
          placeholder="Email"
          value={user.email}
          onChange={handleChange}
          required
        />

        <input
          name="username"
          placeholder="Username"
          value={user.username}
          onChange={handleChange}
          required
        />

        <input
          name="phoneNo"
          type="number"
          placeholder="Phone Number"
          value={user.phoneNo}
          onChange={handleChange}
        />

        <input
          name="password"
          type="password"
          placeholder="Password"
          value={user.password}
          onChange={handleChange}
          required
        />

        <select
          name="role"
          value={user.role}
          onChange={handleChange}
          required
        >
          <option value="">Select Role</option>
          <option value="Admin">Admin</option>
          <option value="Organizer">Organizer</option>
          <option value="User">User</option>
        </select>

        <button type="submit">Create User</button>
      </form>
    </div>
  );
};

export default AdminCreateUser;
