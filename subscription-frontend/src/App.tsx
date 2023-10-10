import React, {useEffect} from 'react';
import SubscriptionForm from "./component/SubscriptionForm";
import eventStore from "./store/EventStore";
import serviceStore from "./store/ServiceStore";
import ServiceList from "./component/ServiceList";
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
