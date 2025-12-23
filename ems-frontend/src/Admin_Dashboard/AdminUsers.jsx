import "./AdminUsers.css";
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

const AdminUsers = () => {
  const [users, setUsers] = useState([]);
  const [loading, setLoading] = useState(true);
  const navigate = useNavigate();

  // Fetch users from backend
  useEffect(() => {
    fetchUsers();
  }, []);

  const fetchUsers = async () => {
    try {
      const res = await fetch("http://localhost:8080/api/users");
      if (!res.ok) throw new Error("Failed to fetch users");

      const data = await res.json();
      setUsers(data);
    } catch (err) {
      console.error(err);
      alert("Unable to load users");
    } finally {
      setLoading(false);
    }
  };

  const handleDelete = async (id) => {
    const confirm = window.confirm(
      "Are you sure you want to delete this user?"
    );
    if (!confirm) return;

    try {
      const res = await fetch(
        `http://localhost:8080/api/users/${id}`,
        { method: "DELETE" }
      );

      if (!res.ok) throw new Error("Delete failed");

      // Remove user from UI immediately
      setUsers(users.filter((u) => u.id !== id));
    } catch (err) {
      console.error(err);
      alert("Failed to delete user");
    }
  };

  if (loading) {
    return <div className="admin-page">Loading users...</div>;
  }

  return (
    <div className="admin-page">
      <div className="page-header">
        <h1>Users</h1>
        <button className="primary-btn" onClick={() => navigate(`/admin/users/new`)}>+ Add User</button>
      </div>

      <div className="table-card">
        <table className="admin-table">
          <thead>
            <tr>
              <th>Name</th>
              <th>Username</th>
              <th>Email</th>
              <th>Phone</th>
              <th>Role</th>
              <th className="actions-col">Actions</th>
            </tr>
          </thead>

          <tbody>
            {users.length === 0 && (
              <tr>
                <td colSpan="6" className="empty-state">
                  No users found
                </td>
              </tr>
            )}

            {users.map((user) => (
              <tr key={user.id}>
                <td>{user.name}</td>
                <td className="username">@{user.username}</td>
                <td>{user.email}</td>
                <td>{user.phoneNo ?? "—"}</td>

                <td>
                  <span
                    className={`role-badge ${
                      user.role === "Admin" ? "admin" : "organizer"
                    }`}
                  >
                    {user.role}
                  </span>
                </td>

                <td>
                  <div className="row-actions">
                    <button
                      className="action-btn edit"
                      onClick={() =>
                        navigate(`/admin/users/${user.id}/edit`)
                      }
                    >
                      Edit
                    </button>

                    <button
                      className="action-btn delete"
                      onClick={() => handleDelete(user.id)}
                    >
                      Delete
                    </button>
                  </div>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
};

export default AdminUsers;
