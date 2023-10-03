import React, {FC, useState} from 'react';
import {IEvent} from "../store/EventStore";
import {Form, ListGroup} from "react-bootstrap";
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import {faCircleChevronDown, faCircleChevronLeft} from "@fortawesome/free-solid-svg-icons";

interface IEventListItem {
    event: IEvent;
}

const EventListItem: FC<IEventListItem> = ({ event }) => {
    const [toggled, setToggled] = useState(false);

    const onCheckboxClicked = (event: React.MouseEvent<HTMLInputElement, MouseEvent>) => {
        event.stopPropagation();
    }

    return (
        <ListGroup.Item
            onClick={() => setToggled(!toggled)}
            className="d-flex"
        >
            <Form.Check
                className="me-2"
                onClick={onCheckboxClicked}
            />
                {event.caption}
            <FontAwesomeIcon
                icon={!toggled ? faCircleChevronLeft : faCircleChevronDown}
                className="pt-1 ms-auto"
            />
        </ListGroup.Item>
    );
};

export default EventListItem;