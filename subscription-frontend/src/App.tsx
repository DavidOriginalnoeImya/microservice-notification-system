import React, {useEffect} from 'react';
import './App.css';
import EventList from "./component/EventList";
import eventStore from "./store/EventStore";
import serviceStore from "./store/ServiceStore";
import ServiceList from "./component/ServiceList";
import {observer} from "mobx-react-lite";
import parameterStore from "./store/ParameterStore";
import eventSubscriptionStore from "./store/EventSubscriptionStore";

function App() {
  const {  events } = eventStore;

  const { services } = serviceStore;

    useEffect(() => {
        if (services.length > 0) {
            serviceStore.setCurrentServiceName(services[0].name);
            eventStore.getEventsFromServer(services[0].name);
        }
    }, [services]);

  return (
      <div className="d-flex">
          <ServiceList services={services}/>
          <EventList events={events}/>
      </div>
  );
}

export default observer(App);
