import React, {FC} from 'react';
import {IEvent} from "../store/EventStore";
import {ListGroup} from "react-bootstrap";
import Event from "./Event";
import {observer} from "mobx-react-lite";

interface IEventList {
    events: IEvent[];
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
                    event =>
                        <Event
                            event={event}
                            key={event.name}
                        />
                )
            }
        </ListGroup>
    );
};

export default EventList;