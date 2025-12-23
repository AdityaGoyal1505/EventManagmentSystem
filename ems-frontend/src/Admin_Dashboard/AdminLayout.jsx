import { NavLink, Outlet } from "react-router-dom";
import "./AdminLayout.css";

const AdminLayout = () => {
  return (
    <div className="admin-layout">
      {/* Sidebar */}
      <aside className="admin-sidebar">
        <a href="/" className="brand">
          🎉 Festora
        </a>

        <nav className="admin-nav">
          <NavLink to="/admin/dashboard">Dashboard</NavLink>
          <NavLink to="/admin/events">Events</NavLink>
          <NavLink to="/admin/users">Users</NavLink>
          <NavLink to="/admin/tickets">Tickets</NavLink>
          <NavLink to="/admin/settings">Settings</NavLink>
        </nav>
      </aside>

      {/* Main Area */}
      <main className="admin-main">
        <Outlet />
      </main>
    </div>
  );
};

export default AdminLayout;
