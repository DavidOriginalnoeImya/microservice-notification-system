import React, {FC} from 'react';
import {IEvent} from "../store/EventStore";
import {Button, Form, ListGroup} from "react-bootstrap";
import EventListItem from "./EventListItem";
import parameterStore from "../store/ParameterStore";

interface IEventList {
    events: IEvent[]
}

const EventList: FC<IEventList> = ({ events }) => {
    const componentStyle = {
        width: "70%",
        marginTop: "3%",
        marginLeft: "5%"
    }

    return (
        <ListGroup style={componentStyle}>
            {
                events.map(
                    event => <EventListItem event={event}/>
                )
            }
        </ListGroup>
    );
};

export default EventList;