import React, {useEffect} from 'react';
import SubscriptionForm from "./components/SubscriptionForm";
import eventStore from "./stores/EventStore";
import serviceStore from "./stores/ServiceStore";
import ServiceList from "./components/ServiceList";
import {observer} from "mobx-react-lite";

function App() {
  const {  events } = eventStore;

  const { services } = serviceStore;

    useEffect(() => {
        if (services.length > 0) {
            serviceStore.setCurrentServiceName(services[0].name);
            eventStore.initEvents(services[0].name);
        }
    }, [services]);

  return (
      <div className="d-flex">
          <ServiceList services={services}/>
          <SubscriptionForm events={events}/>
      </div>
  );
}

export default observer(App);
