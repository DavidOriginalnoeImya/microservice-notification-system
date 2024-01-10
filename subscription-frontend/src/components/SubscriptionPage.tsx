import React, {useEffect, useState} from 'react';
import eventStore from "../stores/EventStore";
import serviceStore from "../stores/ServiceStore";
import ServiceList from "./ServiceList";
import EventList from "./EventList";
import {observer} from "mobx-react-lite";

const SubscriptionPage = () => {
    const [curServiceName, setCurServiceName] = useState("");

    const { events, initEvents, addEventSubscription, delEventSubscription } = eventStore;
    const { services } = serviceStore;

    useEffect(() => {
        if (services.length > 0) {
            setCurServiceName(services[0].name);
        }
    }, [services]);

    useEffect(() => {
        if (curServiceName !== "") {
            initEvents(curServiceName);
        }
    }, [curServiceName]);

    const onEventCheck = (eventName: string) => {
        addEventSubscription(eventName, curServiceName);
    }

    const onEventUncheck = (eventName: string) => {
        delEventSubscription(eventName, curServiceName);
    }

    return (
        <div className="d-flex">
            <ServiceList
                services={services}
                onServiceClick={setCurServiceName}
            />
            <EventList
                events={events}
                onEventCheck={onEventCheck}
                onEventUncheck={onEventUncheck}
            />
        </div>
    );
};

export default observer(SubscriptionPage);