import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import "./OrganizerTickets.css";

export default function OrganizerTickets(){
    const [events, setEvents] = useState([]);
  const [tickets, setTickets] = useState([]);
  const navigate = useNavigate();
  const [paymentStatuses, setPaymentStatuses] = useState("PENDING");
  const user=JSON.parse(localStorage.getItem("user"));
  const organizerId=user?.id;
    useEffect(() => {
        if (!organizerId) return;

        fetch(`http://localhost:8080/api/events/organizer/${organizerId}`)
            .then(res => res.json())
            .then(data => setEvents(data));
        }, [organizerId]);

    useEffect(() => {
        if (events.length === 0) return;
        Promise.all(
            events.map(e =>
            fetch(`http://localhost:8080/api/tickets/event/${e.id}`)
                .then(res => res.json())
            )
        ).then(results => {
            const merged = results.flat();
            setTickets(merged);
        });
    }, [events]);

    useEffect(() => {
        if (tickets.length === 0) return;
        
        // Fetch payment status for each ticket
        tickets.forEach(ticket => {
            fetchPaymentStatus(ticket.id).then(status => {
            setPaymentStatuses(prev => ({
                ...prev,
                [ticket.id]: status
            }));
            });
        });
        }, [tickets]);


        const fetchPaymentStatus = async (ticketId) => {
        try {
            const res = await fetch(`http://localhost:8080/api/payments/ticket/${ticketId}`);
            if (!res.ok) throw new Error("Failed to fetch payment");
            const data = await res.json();
            return data[0]?.status ?? "PENDING";
        } catch (err) {
            console.error(err);
            return "PENDING";
        }
    };

  const handleDelete = (id) => {
    if (!window.confirm("Delete this ticket?")) return;

    fetch(`http://localhost:8080/api/tickets/${id}`, {
      method: "DELETE"
    })
      .then(() => {
        setTickets(prev => prev.filter(t => t.id !== id));
      })
      .catch(() => alert("Delete failed"));
  };

  return (
    <div className="admin-events">
      <h2>Manage Tickets</h2>

      <div className="events-table">
        <div className="table-head">
          <span>User</span>
          <span>Event</span>
          <span>Qty</span>
          <span>Price</span>
          <span>Payment</span>
          <span>Actions</span>
        </div>

                {tickets.map(ticket => (
                <div className="table-row" key={ticket.id}>
                    <span>{ticket.user.username}</span>
                    <span>{ticket.event.title}</span>
                    <span>{ticket.quantityAvailable}</span>
                    <span>₹{ticket.price}</span>

                    <span className={`status ${paymentStatuses[ticket.id] === "SUCCESS" ? "published" : "draft"}`}>
        {paymentStatuses[ticket.id] || "PENDING"}
        </span>

            <div className="action">
              <button className="edit" onClick={() => navigate(`/organizer/tickets/${ticket.id}/edit`)}>
                Edit
              </button>
              <button className="delete" onClick={() => handleDelete(ticket.id)}>
                Delete
              </button>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
};

// export default AdminTickets;
