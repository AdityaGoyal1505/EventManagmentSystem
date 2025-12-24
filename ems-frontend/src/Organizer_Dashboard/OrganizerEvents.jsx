import './OrganizerEvents.css';
import { useNavigate } from 'react-router-dom';
import { useEffect, useState } from 'react';

export default function OrganizerEvents() {
    const navigate = useNavigate();
    const [events, setEvents] = useState([]);
    const users = JSON.parse(localStorage.getItem("user"));
    const organizerId = users?.id 

    useEffect(() => {
        fetch(`http://localhost:8080/api/events/organizer/${organizerId}`)
          .then(res => res.json())
          .then(data => setEvents(data));
    },[organizerId]);
    
    const handleDelete = async (id) =>{
        const confirm = window.confirm("Are you sure you want to delete this event?");
        if(!confirm) return;
        try{
            const res = await fetch(`http://localhost:8080/api/events/${id}`,
                {
                    method:'DELETE'
                }
            );

            if (!res.ok) {
                throw new Error("Failed to delete event");
            }

            setEvents(prev => prev.filter(event => event.id !== id));
        }catch(err){
            alert("Failed to delete event. Try again.");
            console.error(err);
            return;
        }
    }
    return (
        <div className="admin-events">
      <div className="events-header">
        <h2>Manage Events</h2>
        <button className="create-btn" onClick={() => navigate(`/admin/events/new`)}>+ Create Event</button>
      </div>

      <div className="events-table">
        <div className="table-head-event">
          <span>Title</span>
          <span>Category</span>
          <span>Organizer</span>
          <span>Date</span>
          <span>Price</span>
          <span>Status</span>
          <span>Actions</span>
        </div>

        {events.map(event => (
          <div className="table-row-event" key={event.id}>
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
              <span className={`statuss ${event.published ? "published" : "draft"}`}>
                {event.published ? "Published" : "Draft"}
              </span>
            </span>
            <span className="actions">
              <button
                className="edit"
                onClick={() => navigate(`/organizer/events/${event.id}/edit`)}
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
    )
}