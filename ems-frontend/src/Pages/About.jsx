import "./About.css";
import Header from "../Components/Header";
import Footer from "../Components/Footer";

export default function About() {
  return (
    <>
      <Header />
    <section className="about">
      {/* HERO */}
      <div className="about-hero">
        <div className="about-text">
          <h1>About <span>Festora</span></h1>
          <p>
            Festora is where events stop feeling like work and start feeling
            like experiences. Create, promote, and manage events without
            chaos, clutter, or complexity.
          </p>
        </div>

        <div className="about-visual">
          <span className="blob blob-lg" />
          <span className="blob blob-sm" />
        </div>
      </div>

      {/* STRIP */}
      <div className="about-strip shadow-box">
        🎉 Events are moments. We build tools that respect that.
      </div>

      {/* WHY */}
      <div className="about-why">
        <div className="about-visual">
          <span className="blob blob-lg" />
          <span className="blob blob-sm" />
        </div>
        <div className="about-why-text">
        <h2>Why Festora Exists</h2>
          <p>
            Organizing events today is fragmented — registrations here,
            promotions there, analytics somewhere else.
          </p>
          <p>
            Festora brings everything into one clean flow, so the focus stays
            on people, not platforms.
          </p>
        </div>
      </div>

      {/* VALUES */}
      <div className="about-values">
        <div className="value shadow-box">
          <span>⚡</span>
          <h3>Fast to launch</h3>
          <p>No learning curve. No setup pain.</p>
        </div>

        <div className="value shadow-box">
          <span>🎶</span>
          <h3>Built for energy</h3>
          <p>Design that feels like live events feel.</p>
        </div>

        <div className="value shadow-box">
          <span>📊</span>
          <h3>Clarity over noise</h3>
          <p>Insights without overwhelming dashboards.</p>
        </div>
      </div>
    </section>
    <Footer />
    </>
  );
}
