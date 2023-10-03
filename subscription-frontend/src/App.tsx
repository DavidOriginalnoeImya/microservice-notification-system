import React from 'react';
import logo from './logo.svg';
import './App.css';
import EventList from "./component/EventList";
import eventStore from "./store/EventStore";
import serviceStore from "./store/ServiceStore";
import ServiceList from "./component/ServiceList";

function App() {
  const { events } = eventStore;

  const { services } = serviceStore;

  return (
    <div className="d-flex">
        <ServiceList services={services}/>
        <EventList events={events}/>
    </div>
  );
}

export default App;
