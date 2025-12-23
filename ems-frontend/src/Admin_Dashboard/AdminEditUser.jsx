import "./AdminEditUser.css";
import { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";

const AdminEditUser = () => {
  const { id } = useParams();   // ✅ FIX
  const navigate = useNavigate();

  const [formData, setFormData] = useState({
    name: "",
    email: "",
    username: "",
    phoneNo: "",
    role: "Organizer",
  });

  const [loading, setLoading] = useState(true);

  // Fetch user by ID
  useEffect(() => {
    fetch(`http://localhost:8080/api/users/${id}`)
      .then((res) => {
        if (!res.ok) throw new Error("Failed to fetch user");
        return res.json();
      })
      .then((data) => {
        setFormData({
          name: data.name,
          email: data.email,
          username: data.username,
          phoneNo: data.phoneNo || "",
          role: data.role,
        });
        setLoading(false);
      })
      .catch((err) => {
        console.error(err);
        alert("User not found");
        navigate("/admin/users");
      });
  }, [id, navigate]);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({ ...prev, [name]: value }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    const payload = {
      name: formData.name,
      email: formData.email,
      phoneNo: formData.phoneNo,
      role: { name: formData.role }, // matches backend
    };

    try {
      const res = await fetch(
        `http://localhost:8080/api/users/${id}`,
        {
          method: "PUT",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify(payload),
        }
      );

      if (!res.ok) throw new Error("Update failed");

      alert("User updated successfully");
      navigate("/admin/users");
    } catch (err) {
      console.error(err);
      alert("Failed to update user");
    }
  };

  if (loading) {
    return <div className="admin-page">Loading user...</div>;
  }

  return (
    <div className="admin-page">
      <div className="page-header">
        <h1>Edit User</h1>
      </div>

      <div className="edit-card">
        <form onSubmit={handleSubmit} className="edit-form">
          <div className="form-grid">
            <div className="form-group">
              <label>Name</label>
              <input
                name="name"
                value={formData.name}
                onChange={handleChange}
                required
              />
            </div>

            <div className="form-group">
              <label>Username</label>
              <input value={formData.username} disabled />
            </div>

            <div className="form-group">
              <label>Email</label>
              <input
                name="email"
                type="email"
                value={formData.email}
                onChange={handleChange}
                required
              />
            </div>

            <div className="form-group">
              <label>Phone</label>
              <input
                name="phoneNo"
                value={formData.phoneNo}
                onChange={handleChange}
              />
            </div>

            <div className="form-group">
              <label>Role</label>
              <select
                name="role"
                value={formData.role}
                onChange={handleChange}
              >
                <option value="Admin">Admin</option>
                <option value="Organizer">Organizer</option>
                <option value="User">User</option>
              </select>
            </div>
          </div>

          <div className="form-actions">
            <button
              type="button"
              className="secondary-btn"
              onClick={() => navigate("/admin/users")}
            >
              Cancel
            </button>
            <button type="submit" className="primary-btn">
              Save Changes
            </button>
          </div>
        </form>
      </div>
    </div>
  );
};

export default AdminEditUser;
