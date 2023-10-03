import React from 'react';
import logo from './logo.svg';
import './App.css';
import EventList from "./component/EventList";
import eventStore from "./store/EventStore";

function App() {
  const { events } = eventStore;

  return (
    <div className="App">
      <EventList events={events}/>
    </div>
  );
}

export default App;
