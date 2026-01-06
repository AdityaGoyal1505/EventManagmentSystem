import { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import "./AdminEditTickets.css";

const AdminEditTickets = () => {
  const { id } = useParams(); // ticketId
  const navigate = useNavigate();
  const token=localStorage.getItem("token");
  const [ticket, setTicket] = useState(null);
  const [payments, setPayments] = useState([]);
  const [selectedPaymentId, setSelectedPaymentId] = useState(null);
  const [status, setStatus] = useState("PENDING");
  const [paymentMethod,setPaymentMethod] = useState("Manual");
  const [referenceNote, setReferenceNote] = useState(null);


  useEffect(() => {
    // 1️⃣ Load ticket
    fetch(`http://localhost:8080/api/tickets/${id}`,{
      headers:{
        Authorization: `Bearer ${token}`,
      }
    })
      .then(res => res.json())
      .then(setTicket);

    // 2️⃣ Load payments for ticket
    fetch(`http://localhost:8080/api/payments/ticket/${id}`,{
      headers:{
        Authorization: `Bearer ${token}`,
      }
    })
      .then(res => res.json())
      .then(data => {
        setPayments(data);
        if (data.length) {
          const latest = data[data.length - 1];
          setSelectedPaymentId(latest.id);
          setStatus(latest.status);
        }
      });
  }, [id]);

  const handleTicketChange = (e) => {
    const { name, value } = e.target;
    setTicket(prev => ({ ...prev, [name]: value }));
  };

  const handleSave = async (e) => {
    e.preventDefault();

    try {
      // 1️⃣ Update ticket
      await fetch(`http://localhost:8080/api/tickets/${id}`, {
        method: "PUT",
        headers: { Authorization: `Bearer ${token}`,"Content-Type": "application/json" },
        body: JSON.stringify({
          type: ticket.type,
          price: ticket.price,
          quantityAvailable: ticket.quantityAvailable
        })
      });

      // 2️⃣ Update payment status
      if (selectedPaymentId) {
        await fetch(
          `http://localhost:8080/api/payments/status/ticket/${id}`,
          {
            method: "PUT",
            headers: { Authorization: `Bearer ${token}`,"Content-Type": "application/json" },
            body: JSON.stringify({
              status,
              method: paymentMethod,
              referenceNote
            })
          }
        );
      }

      alert("Ticket & payment updated");
      navigate("/admin/tickets");

    } catch {
      alert("Update failed");
    }
  };

  if (!ticket) return <p>Loading...</p>;

  return (
    <div className="edit-ticket">
      <h2>Edit Ticket</h2>

      <form onSubmit={handleSave}>
        <input value={ticket.user.username} disabled />
        <input value={ticket.event.title} disabled />

        <select name="type" value={ticket.type} onChange={handleTicketChange}>
          <option value="REGULAR">Regular</option>
          <option value="VIP">VIP</option>
        </select>

        <input
          type="number"
          name="quantityAvailable"
          value={ticket.quantityAvailable}
          onChange={handleTicketChange}
        />

        <input
          type="number"
          name="price"
          value={ticket.price}
          onChange={handleTicketChange}
        />

        <select value={status} onChange={e => setStatus(e.target.value)}>
          <option value="PENDING">Pending</option>
          <option value="CONFIRMED">Confirmed</option>
          <option value="FAILED">Failed</option>
          <option value="REFUNDED">Refunded</option>
        </select>

        <select
          value={paymentMethod}
          onChange={(e) => setPaymentMethod(e.target.value)}
        >
          <option value="MANUAL">Manual</option>
          <option value="UPI">UPI</option>
          <option value="CARD">Card</option>
          <option value="NETBANKING">Net Banking</option>
        </select>

        <input
          type="text"
          placeholder="Payment reference / remark"
          value={referenceNote}
          onChange={(e) => setReferenceNote(e.target.value)}
        />

        <div className="form-actions">
          <button type="submit">Save</button>
          <button type="button" onClick={() => navigate("/admin/tickets")}>
            Cancel
          </button>
        </div>
      </form>
    </div>
  );
};

export default AdminEditTickets;
