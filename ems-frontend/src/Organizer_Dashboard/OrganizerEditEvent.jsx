import { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import "./OrganizerEditEvent.css";

const OrganizerEditEvent = () => {
  const { id } = useParams();
  const navigate = useNavigate();
  const CATEGORY_OPTIONS = [
    { id: 1, name: "Technology" },
    { id: 2, name: "Arts" },
    { id: 3, name: "Music" },
    { id: 4, name: "College" },
    { id: 5, name: "Food" },
    { id: 6, name: "Live Show" }
  ];

  
  const [event, setEvent] = useState({
    title: "",
    description: "",
    location: "",
    startTime: "",
    endTime: "",
    categoryId: "",
    AmountPerTicket: 0,
    maxAttendees: 0,
    published: false,
  });

  useEffect(() => {
    if (!id) return;

    fetch(`http://localhost:8080/api/events/${id}`)
      .then(res => res.json())
      .then(data => {
        setEvent({
          ...data,
          startTime: data.startTime ? data.startTime.slice(0, 16) : "",
          endTime: data.endTime ? data.endTime.slice(0, 16) : "",
          categoryId: Number(data.categoryId || ""),
        });
      });
  }, [id]);


  const handleChange = (e) => {
    const { name, value, type, checked } = e.target;
    setEvent(prev => ({
      ...prev,
      [name]: type === "checkbox" ? checked : value,
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    const res = await fetch(
      `http://localhost:8080/api/events/${id}`,
      {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(event),
      }
    );

    if (res.ok) {
      alert("Event updated successfully");
      navigate("/organizer/events");
    } else {
      alert("Failed to update event");
    }
  };

  return (
    <div className="edit-event">
      <h2>Edit Event</h2>

      <form onSubmit={handleSubmit} className="edit-form">
        <label>
          Title
          <input
            name="title"
            value={event.title}
            onChange={handleChange}
            required
          />
        </label>

        <label>
          Description
          <textarea
            name="description"
            value={event.description}
            onChange={handleChange}
          />
        </label>

        <label>
          Location
          <input
            name="location"
            value={event.location}
            onChange={handleChange}
          />
        </label>

        <label>
          Start Time
          <input
            type="datetime-local"
            name="startTime"
            value={event.startTime }
            onChange={handleChange}
          />
        </label>

        <label>
          End Time
          <input
            type="datetime-local"
            name="endTime"
            value={event.endTime}
            onChange={handleChange}
          />
        </label>
        <label>
          Category
          <select
            name="categoryId"
            value={event.categoryId || ""}
            onChange={handleChange}
            required
          >
            <option value="" disabled>
              Select Category
            </option>

            {CATEGORY_OPTIONS.map(cat => (
              <option key={cat.id} value={cat.id}>
                {cat.name}
              </option>
            ))}
          </select>
        </label>

        <label>
          Price (₹)
          <input
            type="number"
            name="amountPerTicket"
            value={event.amountPerTicket}
            onChange={handleChange}
          />
        </label>

        <label>
          Max Attendees
          <input
            type="number"
            name="maxAttendees"
            value={event.maxAttendees}
            onChange={handleChange}
          />
        </label>

        <label className="checkbox">
          <input
            type="checkbox"
            name="published"
            checked={event.published}
            onChange={handleChange}
          />
          Published
        </label>

        <div className="actions">
          <button type="submit" className="save">Save</button>
          <button
            type="button"
            className="cancel"
            onClick={() => navigate("/organizer/events")}
          >
            Cancel
          </button>
        </div>
      </form>
    </div>
  );
};

export default OrganizerEditEvent;
