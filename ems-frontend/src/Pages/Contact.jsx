import "./Contact.css";
import Header from "../Components/Header";
import Footer from "../Components/Footer";

const Contact = () => {
  return (
    <>
      <Header />
    <div className="contact-page">

      {/* Hero */}
      <section className="contact-hero">
        <div className="contact-hero-text">
          <h1>Let’s Talk Events</h1>
          <p>
            Whether you’re planning something big, need help using Festora,
            or just want to say hi — we’re listening.
          </p>
        </div>

        <div className="contact-hero-shapes">
          <span className="shape large"></span>
          <span className="shape small"></span>
        </div>
      </section>

      {/* Contact Options */}
      <section className="contact-options">
        <div className="contact-option">
          <h3>🎉 Event Organizers</h3>
          <p>Questions about hosting, tickets, or promotions.</p>
          <span>organizers@festora.com</span>
        </div>

        <div className="contact-option">
          <h3>🎟 Attendees</h3>
          <p>Need help with bookings, passes, or refunds?</p>
          <span>support@festora.com</span>
        </div>

        <div className="contact-option">
          <h3>🤝 Partnerships</h3>
          <p>Brands, venues, or collaborators — let’s build together.</p>
          <span>partners@festora.com</span>
        </div>
      </section>

      {/* Contact Form */}
      <section className="contact-form-section">
        <div className="contact-form-box">
          <h2>Send Us a Message</h2>
          <p>We usually reply within 24 hours.</p>

          <form className="contact-form">
            <input type="text" placeholder="Your name" />
            <input type="email" placeholder="Your email" />
            <input type="text" placeholder="Subject" />
            <textarea placeholder="Tell us a bit more..." rows="5"></textarea>

            <button type="submit">Send Message</button>
          </form>
        </div>
      </section>

    </div>
    <Footer />
    </>
  );
};

export default Contact;
