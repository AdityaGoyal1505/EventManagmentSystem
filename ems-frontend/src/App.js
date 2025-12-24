import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Login from "./Pages/Login";
import Register from "./Pages/Register";
import Home from "./Pages/Home";
import About from "./Pages/About";
import Events from "./Pages/Events";
import Contact from "./Pages/Contact";
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
function App() {
  return (
    <Router>
      <Routes>
        <Route path="/login" element={<Login />} />
        <Route path="/signup" element={<Register />} />
        <Route path="/" element={<Home />} />
        <Route path="/about" element={<About />} />
        <Route path="/events" element={<Events />}/>
        <Route path="/Contact" element={<Contact />}/>
        <Route path="/admin" element={<AdminLayout />}>
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
      </Routes>
    </Router>
  );
}

export default App;
