import { NavLink, Outlet } from "react-router-dom";
import "./OrganizerLayout.css";

const AdminLayout = () => {
  return (
    <div className="organizer-layout">
      {/* Sidebar */}
      <aside className="organizer-sidebar">
        <a href="/" className="brand">
          🎉 Festora
        </a>

        <nav className="organizer-nav">
          <NavLink to="/organizer/dashboard">Dashboard</NavLink>
          <NavLink to="/organizer/mytickets">My Tickets</NavLink>
          <NavLink to="/organizer/events">Events</NavLink>
          <NavLink to="/organizer/tickets">Tickets</NavLink>
          <NavLink to="/organizer/settings">Settings</NavLink>
        </nav>
      </aside>

      {/* Main Area */}
      <main className="organizer-main">
        <Outlet />
      </main>
    </div>
  );
};

export default AdminLayout;
