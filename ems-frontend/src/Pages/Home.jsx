import React from 'react';
import Header from '../Components/Header';
import Footer from '../Components/Footer';
import LocationCard from '../Components/LocationCard';
import './Home.css';
import WhyFestora from '../Components/WhyFestora';
import HowItWorks from '../Components/HowItWorks';
import TopEvents from '../Components/TopEvents';
import {PlayCircle} from 'lucide-react';


export default function Home() {
    const popularLocations = [
        {
            title: "Mumbai",
            image: "Mumbai.jpg",
            events: 32,
        },
        {
            title: "Delhi",
            image: "Delhi.webp",
            events: 28,
        },
        {
            title: "Bangalore",
            image: "Banglore.jpg",
            events: 19,
        },
        {
            title: "Chennai",
            image: "Chennai.jpg",
            events: 14,
        }
    ];

    const categories = [
        { icon: "🎶", label: "Music" },
        { icon: "🎭", label: "Arts" },
        { icon: "🎓", label: "College" },
        { icon: "👨‍💻", label: "Tech" },
        { icon: "🍽️", label: "Food" },
        { icon: "🎤", label: "Live Shows" },
    ];

    return(
        
        <>
            <Header />
            <div className="Hero">
                <h2>Welcome to <i>Festora</i></h2>
                <p>Your one-stop solution for managing and attending events seamlessly.</p>

                <div className="hero-search">
                    <input 
                        type="text" 
                        placeholder="Search for events, categories or locations..."
                    />
                    <button>Get Started</button>
                </div>
            </div>

            <div className="wave-container">
                <svg viewBox="0 0 1440 160" xmlns="http://www.w3.org/2000/svg">
                    <path fill="#FF6B57" d="
                    M0,80 
                    C180,120 360,120 540,95 
                    C720,70 900,20 1080,40 
                    C1260,60 1380,90 1440,95 
                    V160 H0 Z">
                    </path>
                </svg>
            </div>
            <div className="locations-section">
                <div className="locations-grid">
                    {popularLocations.map((loc, index) => (
                        <LocationCard
                            key={index}
                            title={loc.title}
                            image={loc.image}
                            events={loc.events}
                        />
                    ))}
                </div>
            </div>

            <section className="category-section">
                <h2 className="category-title">Browse by Category</h2>

                <div className="category-container">
                    {categories.map((cat, index) => (
                    <button key={index} className="pill">
                        <span className="pill-icon">{cat.icon}</span>
                        {cat.label}
                    </button>
                    ))}
                </div>
            </section>

            <WhyFestora />
            <HowItWorks />
            {/* <TopEvents /> */}
            <section className="start-now">
                <div className="start-now-content">
                    <div className='inside'>
                    <h2>Start Now</h2>
                    <p>Discover events and manage tickets with ease</p>
                    </div>
                    <div className='outside'>
                    <button className="primary-btn-start" onClick={() => (window.location.href = "/")}>
                    <PlayCircle size={20} />
                    Get Started
                    </button>
                    </div>
                </div>
            </section>
            <Footer />
        </>
    )
}
