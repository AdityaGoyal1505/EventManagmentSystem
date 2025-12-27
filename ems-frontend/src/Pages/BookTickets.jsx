import { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import "./BookTickets.css";
import Header from "../Components/Header";
import Footer from "../Components/Footer";

const BookTickets = () => {
   const { id } = useParams();
  const navigate = useNavigate();
  const user = JSON.parse(localStorage.getItem("user"));
    const userId=user?.id;
  const [event, setEvent] = useState(null);
  const [ticketType, setTicketType] = useState("REGULAR");
  const [quantity, setQuantity] = useState(1);
  const [loading, setLoading] = useState(true);
  const [booking, setBooking] = useState(false);

  useEffect(() => {
    fetch(`http://localhost:8080/api/events/${id}`)
      .then(res => res.json())
      .then(data => {
        setEvent(data);
        setLoading(false);
      })
      .catch(() => setLoading(false));
  }, [id]);

  const handleBooking = async () => {
    if (!user?.id) {
      alert("Please login to book tickets");
      return;
    }

    if (quantity < 1) {
      alert("Quantity must be at least 1");
      return;
    }

    setBooking(true);

    try {
      const res = await fetch("http://localhost:8080/api/tickets", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
          userId: userId,
          eventId: id,
          type: ticketType,
          quantityAvailable: quantity
        })
      });
      if (!res.ok) throw new Error();

      alert("Ticket booked successfully (Payment Pending)");
    //   navigate("/");

    } catch {
      alert("Booking failed");
    } finally {
      setBooking(false);
    }
  };

  if (loading) return <p>Loading event...</p>;
  if (!event) return <p>Event not found</p>;

  return (
    <>
    <Header />
    
    <div className="book-ticket-page">
      <button class="back-btn" onClick={() => (window.location.href = "/events")}>
        <span class="arrow">←</span>
        <span class="label">Back to events</span>
      </button>

      <div className="event-summary">
        <h2>{event.title}</h2>
        <p>{event.description}</p>
        <p>
          <strong>Start Time:</strong> {new Date(event.startTime).toLocaleString()}
        </p>
        <p>
          <strong>End Time:</strong> {new Date(event.endTime).toLocaleString()}
        </p>
        <p>
          <strong>Venue:</strong> {event.location}
        </p>
      </div>

      <div className="booking-card">
        <h3>Book Your Ticket</h3>

        <label>Ticket Type</label>
        <select value={ticketType} onChange={e => setTicketType(e.target.value)}>
          <option value="REGULAR">Regular</option>
        </select>

        <label>Quantity</label>
        <input
          type="number"
          min="1"
          value={quantity}
          onChange={e => setQuantity(Number(e.target.value))}
        />

        <button
          className="book-btn"
          onClick={handleBooking}
          disabled={booking}
        >
          {booking ? "Booking..." : "Book Ticket"}
        </button>
      </div>
    </div>
    <Footer />
    </>
  );
};

export default BookTickets;
