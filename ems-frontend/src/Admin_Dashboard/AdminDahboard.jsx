import "./AdminDashboard.css";

const AdminDashboard = () => {
  return (
    <>
      <h1 className="page-title">Dashboard</h1>

      <div className="stats-grid">
        <div className="stat-card">
          <h3>Total Events</h3>
          <p>24</p>
        </div>
        <div className="stat-card">
          <h3>Active Users</h3>
          <p>1,284</p>
        </div>
        <div className="stat-card">
          <h3>Tickets Sold</h3>
          <p>9,430</p>
        </div>
      </div>
    </>
  );
};

export default AdminDashboard;
