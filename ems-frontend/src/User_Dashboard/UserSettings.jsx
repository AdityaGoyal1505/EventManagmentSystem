import { useEffect, useState } from "react";
import "./UserSettings.css";

const UserSettings = () => {
  const user = JSON.parse(localStorage.getItem("user"));

  const [loading, setLoading] = useState(true);

  const [profile, setProfile] = useState({
    fullName: "",
    email: "",
    username: "",
    phone: ""
  });

  const [passwords, setPasswords] = useState({
    currentPassword: "",
    newPassword: "",
    confirmPassword: ""
  });

  // Load existing profile
  useEffect(() => {
    if (!user?.id) return;

    fetch(`http://localhost:8080/api/users/${user.id}`)
      .then(res => res.json())
      .then(data => {
        setProfile({
          fullName: data.name || "",
          email: data.email || "",
          username: data.username || "",
          phone: data.phoneNo || ""
        });
        setLoading(false);
      })
      .catch(() => setLoading(false));
  }, [user?.id]);

  const handleProfileChange = (e) => {
    const { name, value } = e.target;
    setProfile(prev => ({ ...prev, [name]: value }));
  };

  const handlePasswordChange = (e) => {
    const { name, value } = e.target;
    setPasswords(prev => ({ ...prev, [name]: value }));
  };

  const saveProfile = async () => {
    await fetch(`http://localhost:8080/api/users/${user.id}`, {
      method: "PUT",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(profile)
    });

    alert("Profile updated successfully");
  };

  const changePassword = async () => {
    if (passwords.newPassword !== passwords.confirmPassword) {
      alert("Passwords do not match");
      return;
    }

    await fetch(`http://localhost:8080/api/users/change-password`, {
      method: "PUT",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({
        userId: user.id,
        currentPassword: passwords.currentPassword,
        newPassword: passwords.newPassword
      })
    });

    alert("Password updated");
    setPasswords({
      currentPassword: "",
      newPassword: "",
      confirmPassword: ""
    });
  };

  if (loading) return <p>Loading account settings...</p>;

  return (
    <div className="settings-page">
      <h2>Account Settings</h2>

      {/* Profile Section */}
      <div className="settings-card">
        <h3>Profile Information</h3>

        <input
          type="text"
          name="fullName"
          value={profile.fullName}
          onChange={handleProfileChange}
          placeholder="Full Name"
        />

        <input
          type="email"
          name="email"
          value={profile.email}
          onChange={handleProfileChange}
          placeholder="Email"
        />

        <input
          type="text"
          name="username"
          value={profile.username}
          onChange={handleProfileChange}
          placeholder="Username"
        />

        <input
          type="text"
          name="phone"
          value={profile.phone}
          onChange={handleProfileChange}
          placeholder="Phone Number"
        />

        <button className="primary-btn-set" onClick={saveProfile}>
          Save Profile
        </button>
      </div>

      {/* Password Section */}
      <div className="settings-card">
        <h3>Change Password</h3>

        <input
          type="password"
          name="currentPassword"
          value={passwords.currentPassword}
          onChange={handlePasswordChange}
          placeholder="Current Password"
        />

        <input
          type="password"
          name="newPassword"
          value={passwords.newPassword}
          onChange={handlePasswordChange}
          placeholder="New Password"
        />

        <input
          type="password"
          name="confirmPassword"
          value={passwords.confirmPassword}
          onChange={handlePasswordChange}
          placeholder="Confirm New Password"
        />

        <button className="secondary-btn-set" onClick={changePassword}>
          Update Password
        </button>
      </div>
    </div>
  );
};

export default UserSettings;
