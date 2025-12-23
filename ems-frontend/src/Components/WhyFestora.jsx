import React from "react";
import {
  LuZap,
  LuShieldCheck,
  LuSmartphone,
  LuSmilePlus
} from "react-icons/lu";
import "./WhyFestora.css";

const features = [
  {
    icon: <LuZap />,
    title: "Fast & Effortless",
    desc: "Find events instantly with a smooth and optimized search experience."
  },
  {
    icon: <LuShieldCheck />,
    title: "Secure Bookings",
    desc: "Every transaction is safe, verified, and protected for peace of mind."
  },
  {
    icon: <LuSmartphone />,
    title: "Mobile Friendly",
    desc: "Experience a seamless interface across all devices—fast and responsive."
  },
  {
    icon: <LuSmilePlus />,
    title: "Curated For You",
    desc: "Personalized event recommendations based on your interests."
  }
];

export default function WhyFestora() {
  return (
    <section className="why-section">
      <h2 className="why-heading">Why Festora?</h2>

      <div className="why-grid">
        {features.map((item, idx) => (
          <div className="why-card" key={idx}>
            <div className="why-icon">{item.icon}</div>
            <h3 className="why-title">{item.title}</h3>
            <p className="why-desc">{item.desc}</p>
          </div>
        ))}
      </div>
    </section>
  );
};
