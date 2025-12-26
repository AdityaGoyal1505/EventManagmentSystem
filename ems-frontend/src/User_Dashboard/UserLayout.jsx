import { NavLink, Outlet } from "react-router-dom";
import "./UserLayout.css";

export default function UserLayout(){
  return (
    <div className="user-layout">
      {/* Sidebar */}
      <aside className="user-sidebar">
        <a href="/" className="brand">
          🎉 Festora
        </a>

        <nav className="user-nav">
          <NavLink to="/user/tickets">My Tickets</NavLink>
          <NavLink to="/user/settings">Settings</NavLink>
        </nav>
      </aside>

      {/* Main Area */}
      <main className="user-main">
        <Outlet />
      </main>
    </div>
  );
};
