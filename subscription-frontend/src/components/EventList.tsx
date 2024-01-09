import React, {ChangeEvent, FC} from 'react';
import {IEvent} from "../stores/EventStore";
import {Col, Form} from "react-bootstrap";
import {observer} from "mobx-react-lite";
import serviceStore from "../stores/ServiceStore";

interface IEventList {
    events: IEvent[];
    onEventCheck: (eventName: string) => void;
    onEventUncheck: (eventName: string) => void;
}

const EventList: FC<IEventList> = ({ events, onEventCheck, onEventUncheck }) => {
    const { currentServiceName } = serviceStore;

    const onEventCheckChange = (eventName: string) => (e: ChangeEvent<HTMLInputElement>) => {
        e.target.checked ? onEventCheck(eventName) : onEventUncheck(eventName);
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
                                    onChange={() => onEventCheckChange(event.name)}
                                    checked={event.checked}
                                />
                        )
                    }
            </Col>
        </Form>
    );
};

const componentStyle = {
    width: "50%",
    marginTop: "3%",
    marginLeft: "5%"
}

export default observer(EventList);