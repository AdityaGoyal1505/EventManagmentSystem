import "./EventCard.css";

const EventCard = ({ event, featured = false }) => {
  return (
    <div className={`event-card ${featured ? "featured" : ""}`}>
      
      {/* Image */}
      <div className="event-image">
        {featured && <span className="featured-badge">Featured</span>}
      </div>

      {/* Content */}
      <div className="event-content">
        <span className="event-category">{event.category}</span>

        <h3 className="event-title">
          {event.title}
        </h3>

        <p className="event-description">
          {event.description}
        </p>

        <div className="event-footer">
          <div className="event-info">
            <span>📍 {event.location}</span>
            <span>
              🎟 {event.amountPerTicket === 0 ? "Free" : `₹${event.amountPerTicket}`}
            </span>
          </div>

          <button className="event-btn">View Event</button>
        </div>
      </div>

    </div>
  );
};

export default EventCard;
