import { useState } from "react";
import { useNavigate } from "react-router-dom";
import "./AdminCreate.css";

const CATEGORY_MAP = {
  Music: 1,
  Arts: 2,
  Technology: 3,
  College: 4,
  Food: 5,
  "Live Show": 6
};

const AdminCreateEvent = () => {
  const navigate = useNavigate();

  const [event, setEvent] = useState({
    title: "",
    description: "",
    location: "",
    startTime: "",
    endTime: "",
    amountPerTicket: "",
    maxAttendees: "",
    published: false,
    categoryName: "",
    venueName: ""
  });

  const handleChange = (e) => {
    const { name, value, type, checked } = e.target;
    setEvent({
      ...event,
      [name]: type === "checkbox" ? checked : value
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    const user = JSON.parse(localStorage.getItem("user"));
    const organizerId = user?.id;

    if (!organizerId) {
    alert("Organizer not logged in");
    return;
    }

    const payload = {
      title: event.title,
      description: event.description,
      location: event.location,
      startTime: event.startTime + ":00",
      endTime: event.endTime + ":00",
      amountPerTicket: Number(event.amountPerTicket),
      maxAttendees: Number(event.maxAttendees),
      published: event.published,
      organizerId: organizerId,
      categoryId: CATEGORY_MAP[event.categoryName],
      venueName: event.venueName
    };

    const res = await fetch("http://localhost:8080/api/events/create", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(payload)
    });

    if (res.ok) {
      alert("Event created successfully");
      navigate("/admin/events");
    } else {
      alert("Failed to create event");
    }
  };

  return (
    <div className="form-container">
      <h2>Create Event</h2>

      <form onSubmit={handleSubmit}>
        <input name="title" placeholder="Title" value={event.title} onChange={handleChange} required />
        <textarea name="description" placeholder="Description" value={event.description} onChange={handleChange} required />
        <input name="location" placeholder="Location" value={event.location} onChange={handleChange} required />

        <input type="datetime-local" name="startTime" value={event.startTime} onChange={handleChange} required />
        <input type="datetime-local" name="endTime" value={event.endTime} onChange={handleChange} required />

        <input type="number" name="amountPerTicket" placeholder="Ticket Price" value={event.amountPerTicket} onChange={handleChange} />
        <input type="number" name="maxAttendees" placeholder="Max Attendees" value={event.maxAttendees} onChange={handleChange} />

        <select name="categoryName" value={event.categoryName} onChange={handleChange} required>
          <option value="">Select Category</option>
          <option>Music</option>
          <option>Arts</option>
          <option>Technology</option>
          <option>College</option>
          <option>Food</option>
          <option>Live Show</option>
        </select>

        <input name="venueName" placeholder="Venue Name" value={event.venueName} onChange={handleChange} />

        <label className="checkbox">
          <input type="checkbox" name="published" checked={event.published} onChange={handleChange} />
          Publish Event
        </label>

        <button type="submit">Create Event</button>
      </form>
    </div>
  );
};

export default AdminCreateEvent;
