import React, {ChangeEvent, FC, useEffect} from 'react';
import eventStore, {IEvent} from "../stores/EventStore";
import {Col, Form} from "react-bootstrap";
import {observer} from "mobx-react-lite";
import serviceStore from "../stores/ServiceStore";

interface IEventList {
    events: IEvent[];
}

const EventList: FC<IEventList> = ({ events }) => {
    const componentStyle = {
        width: "50%",
        marginTop: "3%",
        marginLeft: "5%"
    }

    const { currentServiceName } = serviceStore;

    const onEventChecked = (eventName: string) => (e: ChangeEvent<HTMLInputElement>) => {
        e.stopPropagation();
        e.target.checked ?
            eventStore.addEventSubscriptions(eventName, currentServiceName) :
            eventStore.delEventSubscription(eventName, currentServiceName);
    }

    return (
        <Form style={componentStyle}>
            <Col>
                    {
                        events.map(
                            event =>
                                <Form.Check
                                    key={currentServiceName + event.name}
                                    label={event.caption}
                                    onChange={() => onEventChecked(event.name)}
                                    checked={event.checked}
                                    className="col-auto"
                                />
                        )
                    }
            </Col>
        </Form>
    );
};

export default observer(EventList);