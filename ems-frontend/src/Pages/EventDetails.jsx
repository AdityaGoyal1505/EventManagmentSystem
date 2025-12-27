import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import "./EventDetails.css";
import Header from "../Components/Header";
import Footer from "../Components/Footer";


const EventDetails = () => {
  const { id } = useParams();
  const [event, setEvent] = useState(null);
    console.log("Event ID:", id);
  useEffect(() => {
    fetch(`http://localhost:8080/api/events/${id}`)
      .then(res => res.json())
      .then(setEvent)
      .catch(() => alert("Failed to load event"));
  }, [id]);

  if (!event) return <p className="loading">Loading event...</p>;

  return (
    <>
        <Header />
    <div className="event-details">
      <div className="event-hero">
        <h1>{event.title}</h1>
        <span className="category">{event.categoryName}</span>
      </div>

      <div className="event-meta">
        <p><strong>📍 Location:</strong> {event.location}</p>
        <p><strong>🕒 Start:</strong> {new Date(event.startTime).toLocaleString()}</p>
        <p><strong>🕕 End:</strong> {new Date(event.endTime).toLocaleString()}</p>
        <p><strong>👤 Organizer:</strong> {event.organizerName}</p>
      </div>

      <div className="event-description">
        <h3>About this event</h3>
        <p>{event.description}</p>
      </div>

      <div className="event-stats">
        <div>
          <span>Seats Left</span>
          <strong>{event.maxAttendees}</strong>
        </div>
        <div>
          <span>Price</span>
          <strong>
            {event.amountPerTicket > 0 ? `₹${event.amountPerTicket}` : "Free"}
          </strong>
        </div>
        {/* <div className="cta-card"> */}
        <button
            className="book-botton"
            disabled={!event.published}
            onClick={() => window.location.href = `/book-tickets/${event.id}`}
        >
            {event.published ? "🎟 Book Ticket" : "Not Available"}
        </button>
        {/* </div> */}
      </div>
    </div>
    <Footer />
    </>
  );
};

export default EventDetails;
