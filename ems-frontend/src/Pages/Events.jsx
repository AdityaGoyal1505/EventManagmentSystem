import { useState, useEffect } from "react";
import "./Events.css";
import EventCard from "../Components/EventCard";
import Header from "../Components/Header";
import Footer from "../Components/Footer";

const Events = () => {
  const [events, setEvents] = useState([]);
  const [search, setSearch] = useState("");
  const [category, setCategory] = useState("All");
  const [location, setLocation] = useState("All");
  const [price, setPrice] = useState("All");

  useEffect(() => {
    async function getEvents() {
      try {
        const res = await fetch("http://localhost:8080/api/events");
        if (!res.ok) throw new Error();
        setEvents(await res.json());
      } catch {
        setEvents(events); // fallback dummy data
      }
    }
    getEvents();
  }, []);

  const filteredEvents = events.filter(event => {
    return (
      event.title.toLowerCase().includes(search.toLowerCase()) &&
      (category === "All" || event.categoryName === category) &&
      (location === "All" || event.location.includes(location)) &&
      (price === "All" ||
        (price === "Free" && event.amountPerTicket === 0) ||
        (price === "Paid" && event.amountPerTicket > 0))
    );
  });

  const publishedEvents = filteredEvents.filter(e => e.published);
  const categories = ["All", ...new Set(events.map(e => e.categoryName))];
  const locations = ["All", ...new Set(
    events.map(e => e.location?.split(",")[0])
  )];

  return (
    <>
    <Header />
    <div className="events-page">

      {/* Hero */}
      <section className="events-hero">
        <h1>Explore Events</h1>
        <p>
          Discover workshops, concerts, meetups and experiences happening
          around you.
        </p>
      </section>

      {/* Filters */}
      <section className="events-filters">
        <input
          type="text"
          placeholder="Search events"
          value={search}
          onChange={(e) => setSearch(e.target.value)}
        />

        <select value={category} onChange={(e) => setCategory(e.target.value)}>
          {categories.map(cat => (
            <option key={cat} value={cat}>{cat}</option>
          ))}
        </select>

        <select value={location} onChange={(e) => setLocation(e.target.value)}>
          {locations.map(loc => (
            <option key={loc} value={loc}>{loc}</option>
          ))}
        </select>

        <select value={price} onChange={(e) => setPrice(e.target.value)}>
          <option value="All">Any Price</option>
          <option value="Free">Free</option>
          <option value="Paid">Paid</option>
        </select>
      </section>

      {/* Events Grid */}
      <section className="events-grid">  
        {publishedEvents.length ? (
          publishedEvents.map(e => (
            <EventCard key={e.id} event={e} />
          ))
        ) : (
          <p className="no-events">No events match your filters</p>
        )}
      </section>

    </div>
    <Footer />
    </>
  );
};

export default Events;
