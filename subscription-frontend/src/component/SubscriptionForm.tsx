import React, {ChangeEvent, FC, useEffect, useState} from 'react';
import eventStore, {IEvent} from "../store/EventStore";
import {Col, Form, Row} from "react-bootstrap";
import {observer} from "mobx-react-lite";
import ParameterList from "./ParameterList";
import parameterStore from "../store/ParameterStore";
import serviceStore from "../store/ServiceStore";

interface ISubscriptionForm {
    events: IEvent[];
}

const SubscriptionForm: FC<ISubscriptionForm> = ({ events }) => {
    const componentStyle = {
        width: "50%",
        marginTop: "3%",
        marginLeft: "5%"
    }

    const { initParameters, parameters } = parameterStore;

    const { currentEvent } = eventStore;

    const { currentServiceName } = serviceStore;

    useEffect(() => {
        if (events.length > 0) {
            initParameters(events[0].name, currentServiceName);
            eventStore.setCurrentEvent(events[0]);
        }
    }, [events]);

    const onEventChanged = (e: ChangeEvent<HTMLSelectElement>) => {
        e.stopPropagation();
        const currentEvent = eventStore.getEventByCaption(e.target.value)!;
        initParameters(currentEvent.name, currentServiceName);
        eventStore.setCurrentEvent(currentEvent);
    }

    const onEventChecked = (e: ChangeEvent<HTMLInputElement>) => {
        e.stopPropagation();
        e.target.checked ?
            eventStore.addEventSubscriptions(currentEvent.name, currentServiceName) :
            eventStore.delEventSubscription(currentEvent.name, currentServiceName);
    }

    return (
        <Form style={componentStyle}>
            <Col>
                <Row className="flex-nowrap">
                    <Form.Check
                        onChange={onEventChecked}
                        checked={currentEvent.checked}
                        className="col-auto"
                    />
                    <Form.Select
                        onChange={onEventChanged}
                    >
                        {
                            events.map(
                                event =>
                                    <option key={currentServiceName + event.name}>
                                        {event.caption}
                                    </option>
                            )
                        }
                    </Form.Select>
                </Row>
                <ParameterList
                    parameters={parameters}
                />
            </Col>
        </Form>
    );
};

export default observer(SubscriptionForm);