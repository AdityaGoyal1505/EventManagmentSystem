import { FaInstagram, FaTwitter, FaLinkedin } from "react-icons/fa";
import "./Footer.css";

const Footer = () => {
  return (
    <footer className="footer">
      <div className="footer-container">

        {/* Brand */}
        <div className="footer-brand">
          <h2>Festora</h2>
          <p>Your all-in-one platform to create, promote, and manage events effortlessly.</p>
        </div>

        {/* Product */}
        <div className="footer-col">
          <h4>Product</h4>
          <ul>
            <li>Create Event</li>
            <li>Manage Participants</li>
            <li>Promotions</li>
            <li>Analytics</li>
          </ul>
        </div>

        {/* Company */}
        <div className="footer-col">
          <h4>Company</h4>
          <ul>
            <li>About Us</li>
            <li>Careers</li>
            <li>Contact</li>
          </ul>
        </div>

        {/* Support */}
        <div className="footer-col">
          <h4>Support</h4>
          <ul>
            <li>Help Center</li>
            <li>FAQs</li>
            <li>Report Issue</li>
          </ul>
        </div>

        {/* Follow Icons */}
        <div className="footer-follow">
          <h4>Follow</h4>
          <div className="follow-icons">
            <FaInstagram />
            <FaTwitter />
            <FaLinkedin />
          </div>
        </div>
      </div>

      <div className="footer-bottom">
        © 2025 Festora. All rights reserved.
      </div>
    </footer>
  );
};

export default Footer;
