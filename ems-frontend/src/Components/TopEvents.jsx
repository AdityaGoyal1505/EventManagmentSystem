import "./TopEvents.css";

const events = [
  {
    id: 1,
    date: "12 Jan",
    title: "Tech Summit 2025",
    emoji: "🚀",
    featured: true,
  },
  {
    id: 2,
    date: "20 Feb",
    title: "Music Fiesta",
    emoji: "🎵",
  },
  {
    id: 3,
    date: "05 Mar",
    title: "Startup Expo",
    emoji: "💡",
  },
  {
    id: 4,
    date: "18 Mar",
    title: "Design Week",
    emoji: "🎨",
  },
];

export default function TopEvents() {
  return (
    <section className="top-events">
      <div className="top-events-header">
        <h2>Top Events</h2>
        <p>What people are loving right now</p>
      </div>

      <div className="events-row">
        {events.map((event) => (
          <div
            key={event.id}
            className={`event-tile ${event.featured ? "featured" : ""}`}
          >
            <span className="bg-emoji">{event.emoji}</span>

            <div className="tile-main">
              <span className="date">{event.date}</span>
              <h3>{event.title}</h3>
            </div>

            <div className="tile-extra">
              <span>View Event →</span>
            </div>
          </div>
        ))}
      </div>
    </section>
  );
}
