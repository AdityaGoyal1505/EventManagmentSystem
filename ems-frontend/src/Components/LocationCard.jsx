import React from "react";
import "./LocationCard.css";

export default function LocationCard({ title, image, events }) {
    return (
        <div className="location-card">
            <img src={image} alt={title} className="location-image" />

            <div className="location-content">
                <h3 className="location-title">{title}</h3>
                <p className="location-events">{events} events</p>
            </div>
        </div>
    );
}
