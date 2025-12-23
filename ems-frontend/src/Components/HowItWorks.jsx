import React from "react";
import "./HowItWorks.css";
import { Plus, Users, Megaphone, BarChart2 } from "lucide-react";

const steps = [
  {
    id: "01",
    title: "Create Your Event",
    desc: "Set up event details, schedules, and categories effortlessly.",
    icon: <Plus size={18} />
  },
  {
    id: "02",
    title: "Manage Participants",
    desc: "Track registrations, send updates, and handle queries.",
    icon: <Users size={18} />
  },
  {
    id: "03",
    title: "Publish & Promote",
    desc: "Share your event with your audience instantly.",
    icon: <Megaphone size={18} />
  },
  {
    id: "04",
    title: "Track & Analyse",
    desc: "Get real-time insights, attendance data, and reports.",
    icon: <BarChart2 size={18} />
  }
];

export default function HowItWorks() {
  return (
    <section className="how-container">
      <h2 className="how-title">How It Works</h2>

      <div className="timeline">
        <div className="timeline-line" />

        {steps.map((step, index) => (
          <div
            key={step.id}
            className={`timeline-item ${index % 2 === 0 ? "right" : "left"}`}
          >
            <div className="marker">
              <span className="step-number">{step.id}</span>
            </div>

            <div className="content">
              <div className="icon">{step.icon}</div>
              <div>
                <h3>{step.title}</h3>
                <p>{step.desc}</p>
              </div>
            </div>
          </div>
        ))}
      </div>
    </section>
  );
}
