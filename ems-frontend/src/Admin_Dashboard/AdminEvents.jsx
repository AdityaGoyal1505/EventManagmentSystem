import { useEffect, useState } from "react";
import "./AdminEvents.css";
import { useNavigate } from "react-router-dom";

const AdminEvents = () => {
  const [events, setEvents] = useState([]);
  const navigate = useNavigate();
  useEffect(() => {
    fetch("http://localhost:8080/api/events")
      .then(res => res.json())
      .then(data => setEvents(data));
  }, []);

  const handleDelete = async (id) => {
    const confirmed = window.confirm(
      "Are you sure you want to delete this event? This action cannot be undone."
    );

    if (!confirmed) return;

    try {
      const res = await fetch(
        `http://localhost:8080/api/events/${id}`,
        {
          method: "DELETE",
        }
      );

      if (!res.ok) {
        throw new Error("Failed to delete event");
      }

      // Remove event from UI without refetch
      setEvents(prev => prev.filter(event => event.id !== id));

      alert("Event deleted successfully");
    } catch (err) {
      console.error(err);
      alert("Failed to delete event. Try again.");
    }
  };

  return (
    <div className="admin-events">
      <div className="events-header">
        <h2>Manage Events</h2>
        <button className="create-btn" onClick={() => navigate(`/admin/events/new`)}>+ Create Event</button>
      </div>

      <div className="events-table">
        <div className="table-head">
          <span>Title</span>
          <span>Category</span>
          <span>Organizer</span>
          <span>Date</span>
          <span>Price</span>
          <span>Status</span>
          <span>Actions</span>
        </div>

        {events.map(event => (
          <div className="table-row" key={event.id}>
            <span className="title">{event.title}</span>
            <span>{event.categoryName}</span>
            <span>{event.organizerName}</span>
            <span>
              {new Date(event.startTime).toLocaleDateString()}
            </span>
            <span>
              ₹{event.amountPerTicket === 0 ? "Free" : event.amountPerTicket}
            </span>
            <span>
              <span className={`status ${event.published ? "published" : "draft"}`}>
                {event.published ? "Published" : "Draft"}
              </span>
            </span>
            <span className="actions">
              <button
                className="edit"
                onClick={() => navigate(`/admin/events/${event.id}/edit`)}
                >
                Edit
              </button>
              <button
                className="delete"
                onClick={() => handleDelete(event.id)}
              >
                Delete
              </button>
            </span>
          </div>
        ))}
      </div>
    </div>
  );
};

export default AdminEvents;
