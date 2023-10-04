import React, {FC, useState} from 'react';
import {IEvent} from "../store/EventStore";
import {Card, Form, ListGroup} from "react-bootstrap";
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import {faCircleChevronDown, faCircleChevronLeft} from "@fortawesome/free-solid-svg-icons";
import ParameterList from "./ParameterList";

interface IEventListItem {
    event: IEvent;
}

const EventListItem: FC<IEventListItem> = ({ event }) => {
    const [toggled, setToggled] = useState(false);

    const onCheckboxClicked = (event: React.MouseEvent<HTMLInputElement, MouseEvent>) => {
        event.stopPropagation();
    }

    return (
        <Card
            bg={'light'}
        >
            <Card.Header className="d-flex" onClick={() => setToggled(!toggled)}>
                 <Form.Check
                    className="me-2"
                    onClick={onCheckboxClicked}
                />
                    {event.caption}
                <FontAwesomeIcon
                    icon={!toggled ? faCircleChevronLeft : faCircleChevronDown}
                    className="pt-1 ms-auto"
                />
            </Card.Header>
            {
                toggled &&
                <Card.Body className="me-3">

                </Card.Body>
            }
        </Card>
    );
};

export default EventListItem;