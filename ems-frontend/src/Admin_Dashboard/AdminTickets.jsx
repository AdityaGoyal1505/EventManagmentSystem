import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import "./AdminTickets.css";
import * as XLSX from "xlsx";

const AdminTickets = () => {
  const [tickets, setTickets] = useState([]);
  const navigate = useNavigate();
  const [paymentStatuses, setPaymentStatuses] = useState("PENDING");
  const token = localStorage.getItem("token");
  useEffect(() => {
    fetch("http://localhost:8080/api/tickets",{
      headers: {
      Authorization: `Bearer ${token}`,
      },
    })
      .then(res => res.json())
      .then(setTickets)
      .catch(() => alert("Failed to load tickets"));
  }, []);
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

const downloadXlsx = (tickets) => {
  if (!tickets || tickets.length === 0) {
    alert("No ticket data available");
    return;
  }

  const flattenedData = tickets.map(ticket => ({
    "Ticket ID": ticket.id,
    "Ticket Type": ticket.type,
    "Ticket Price": ticket.price,
    "Quantity": ticket.quantityAvailable,

    "Event ID": ticket.event?.id,
    "Event Title": ticket.event?.title,
    "Event Location": ticket.event?.location,
    "Event Start Time": ticket.event?.startTime,
    "Event End Time": ticket.event?.endTime,
    // "Event Last Date": ticket.event?.lastDate,
    // "Event Seats Left": ticket.event?.seatsLeft,

    "Organizer Name": ticket.event?.organizer?.name,
    "Organizer Email": ticket.event?.organizer?.email,

    "Category": ticket.event?.category?.name,

    "User Name": ticket.user?.name,
    "User Email": ticket.user?.email,
    "User Role": ticket.user?.role?.name
  }));

  const worksheet = XLSX.utils.json_to_sheet(flattenedData);
  const workbook = XLSX.utils.book_new();
  XLSX.utils.book_append_sheet(workbook, worksheet, "Tickets");

  XLSX.writeFile(workbook, "tickets.xlsx");
};



  return (
    <div className="admin-ticket">
      <div className="events-header">
        <h2>Manage Tickets</h2>
        <button onClick={() => downloadXlsx(tickets)}className="download-btn">
  Download Tickets (XLSX)
</button>

      </div>
      <div className="tickets-table">
        <div className="table-head-ticket">
          <span>User</span>
          <span>Event</span>
          <span>Qty</span>
          <span>Price</span>
          <span>Payment</span>
          <span>Actions</span>
        </div>

        {tickets.map(ticket => (
          <div className="table-row-ticket" key={ticket.id}>
            <span>{ticket.user.username}</span>
            <span>{ticket.event.title}</span>
            <span>{ticket.quantityAvailable}</span>
            <span>₹{ticket.price}</span>

            <span className={`status ${paymentStatuses[ticket.id] === "CONFIRMED" ? "confirmed" : "pending"}`}>
              {paymentStatuses[ticket.id] || "PENDING"}
            </span>

            <div className="action">
              <button className="edit" onClick={() => navigate(`/admin/tickets/${ticket.id}/edit`)}>
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

export default AdminTickets;
