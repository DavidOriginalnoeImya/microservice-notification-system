import React, {useEffect} from 'react';
import './App.css';
import EventList from "./component/EventList";
import eventStore from "./store/EventStore";
import serviceStore from "./store/ServiceStore";
import ServiceList from "./component/ServiceList";
import {observer} from "mobx-react-lite";

function App() {
  const {  events } = eventStore;

  const { services } = serviceStore;

  return (
      <div className="d-flex">
          <ServiceList services={services}/>
          <EventList events={events}/>
      </div>
  );
}

export default observer(App);
