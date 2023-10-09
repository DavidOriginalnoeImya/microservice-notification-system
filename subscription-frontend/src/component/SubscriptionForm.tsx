import React, {FC, useEffect} from 'react';
import eventStore, {IEvent} from "../store/EventStore";
import {Col, Form, Row} from "react-bootstrap";
import {observer} from "mobx-react-lite";
import ParameterList from "./ParameterList";
import parameterStore from "../store/ParameterStore";
import serviceStore from "../store/ServiceStore";

interface IEventList {
    events: IEvent[];
}

const SubscriptionForm: FC<IEventList> = ({ events }) => {
    const componentStyle = {
        width: "50%",
        marginTop: "3%",
        marginLeft: "5%"
    }

    const { setCurrentEventName, currentEventName, eventSubscriptions } = eventStore;

    const { initParameters, parameters } = parameterStore;

    const { currentServiceName } = serviceStore;

    useEffect(() => {
        if (events.length > 0) {
            initParameters(events[0].name, currentServiceName);
            setCurrentEventName(events[0].name);
        }
    }, [events]);

    const getEventNameByCaption = (eventCaption: string) => {
        return events
            .find(event => event.caption === eventCaption)!
            .name;
    }

    const onEventChanged = (e: React.ChangeEvent<HTMLSelectElement>) => {
        e.stopPropagation();

        const currentEventName = getEventNameByCaption(e.target.value);
        initParameters(currentEventName, currentServiceName);
        setCurrentEventName(currentEventName);
    }

    return (
        <Form style={componentStyle}>
            <Col>
                <Row>
                    <Form.Check
                        // className="me-2"
                        // onChange={onCheckboxClicked}
                        defaultChecked={eventSubscriptions.has(currentEventName)}
                    />
                    <Form.Select
                        onChange={onEventChanged}
                    >
                        {
                            events.map(
                                event =>
                                    <option>
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