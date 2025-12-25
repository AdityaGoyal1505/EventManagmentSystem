import { BrowserRouter as Router, Routes, Route, Navigate } from "react-router-dom";
import Login from "./Pages/Login";
import Register from "./Pages/Register";
import Home from "./Pages/Home";
import About from "./Pages/About";
import Events from "./Pages/Events";
import Contact from "./Pages/Contact";
import EventDetails from "./Pages/EventDetails";

import AdminLayout from "./Admin_Dashboard/AdminLayout";
import AdminEvents from "./Admin_Dashboard/AdminEvents";
import AdminUsers from "./Admin_Dashboard/AdminUsers";
import AdminTickets from "./Admin_Dashboard/AdminTickets";
import AdminDashboard from "./Admin_Dashboard/AdminDahboard";
import AdminEditUser from "./Admin_Dashboard/AdminEditUser";
import AdminEditTickets from "./Admin_Dashboard/AdminEditTickets";
import AdminEditEvent from "./Admin_Dashboard/AdminEditEvent";
import AdminCreateEvent from "./Admin_Dashboard/AdminCreateEvent";
import AdminCreateUser from "./Admin_Dashboard/AdminCreateUser";

import OrganizerLayout from "./Organizer_Dashboard/OrganizerLayout";
import OrganizerDashboard from "./Organizer_Dashboard/OrganizerDashboard";
import OrganizerEvents from "./Organizer_Dashboard/OrganizerEvents";
import OrganizerTickets from "./Organizer_Dashboard/OrganizerTickets";
import OrganizerEditEvent from "./Organizer_Dashboard/OrganizerEditEvent";
import OrganizerEditTickets from "./Organizer_Dashboard/OrganizerEditTicket";
import OrganizerCreateEvent from "./Organizer_Dashboard/OrganizerCreateEvent";

const checkAdmin = () =>{
  const user = JSON.parse(localStorage.getItem("user"));
  const admin = user?.role;
  return admin === "Admin";
}

const checkOrganizer = () =>{
  const user = JSON.parse(localStorage.getItem("user"));
  const organizer = user?.role;
  return organizer === "Organizer";
}

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/login" element={<Login />} />
        <Route path="/signup" element={<Register />} />
        <Route path="/" element={<Home />} />
        <Route path="/about" element={<About />} />
        <Route path="/events" element={<Events />}/>
        <Route path="/events/:id" element={<EventDetails />} />
        <Route path="/Contact" element={<Contact />}/>
        <Route path="/admin" element={checkAdmin() ? <AdminLayout /> : <Navigate to="/" replace />}>
          <Route path="dashboard" element={<AdminDashboard />} />
          <Route path="events" element={<AdminEvents />} />
          <Route path="users" element={<AdminUsers />} />
          <Route path="tickets" element={<AdminTickets />} />
          <Route path="users/:id/edit" element={<AdminEditUser />} />   
          <Route path="/admin/events/:id/edit" element={<AdminEditEvent />} />
          <Route path="tickets/:id/edit" element={<AdminEditTickets />} />
          <Route path="events/new" element={<AdminCreateEvent />} />
          <Route path="users/new" element={<AdminCreateUser />} />
        </Route>
        <Route path="/organizer" element={checkOrganizer() ? <OrganizerLayout /> : <Navigate to="/" replace />}>
          <Route path="dashboard" element={<OrganizerDashboard />} />
          <Route path="events" element={<OrganizerEvents />} />
          <Route path="tickets" element={<OrganizerTickets />} />
          <Route path="events/:id/edit" element={<OrganizerEditEvent />} />
          <Route path="tickets/:id/edit" element={<OrganizerEditTickets />} />
          <Route path="events/new" element={<OrganizerCreateEvent />} />
        </Route>
      </Routes>
    </Router>
  );
}

export default App;
