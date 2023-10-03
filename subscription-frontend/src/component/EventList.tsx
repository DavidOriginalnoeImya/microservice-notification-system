import React, {FC} from 'react';
import {IEvent} from "../store/EventStore";
import {Button, Form, ListGroup} from "react-bootstrap";
import EventListItem from "./EventListItem";

interface IEventList {
    events: IEvent[]
}

const EventList: FC<IEventList> = ({ events }) => {
    return (
        <ListGroup>
            {
                events.map(
                    event => <EventListItem event={event}/>
                )
            }
        </ListGroup>
    );
};

export default EventList;