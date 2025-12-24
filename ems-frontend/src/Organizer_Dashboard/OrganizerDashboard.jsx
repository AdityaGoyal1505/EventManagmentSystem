import './OrganizerDashboard.css';

export default function OrganizerDashboard(){
    return (
        <>
      <h1 className="page-title-org">Dashboard</h1>

      <div className="stats-grid-org">
        <div className="stat-card-org">
          <h3>Total Events</h3>
          <p>24</p>
        </div>
        <div className="stat-card-org">
          <h3>Tickets Sold</h3>
          <p>9,430</p>
        </div>
      </div>
    </>
    )
}