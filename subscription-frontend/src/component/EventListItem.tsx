import React, {FC, useState} from 'react';
import {IEvent} from "../store/EventStore";
import {Card, Form} from "react-bootstrap";
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome'
import {faCircleChevronDown, faCircleChevronLeft} from "@fortawesome/free-solid-svg-icons";
import ParameterList from "./ParameterList";
import parameterStore from "../store/ParameterStore";
import {observer} from "mobx-react-lite";
import serviceStore from "../store/ServiceStore";

interface IEventListItem {
    event: IEvent;
}

const EventListItem: FC<IEventListItem> = ({ event }) => {
    const [toggled, setToggled] = useState(false);

    const { initParameters, parameters } = parameterStore;

    const { currentServiceName } = serviceStore;

    const onCheckboxClicked = (event: React.MouseEvent<HTMLInputElement, MouseEvent>) => {
        event.stopPropagation();
    }

    const onEventClicked = (eventName: string) => {
        setToggled(!toggled);

        if (!toggled && !parameters[eventName]) {
            initParameters(eventName, currentServiceName);
        }
    }

    const isEventHasParameters = () => {
        return parameters[event.name] && parameters[event.name].length > 0;
    }

    return (
        <Card
            bg={'light'}
        >
            <Card.Header className="d-flex" onClick={() => onEventClicked(event.name)}>
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
                toggled && isEventHasParameters() &&
                <Card.Body className="me-3">
                    <ParameterList
                        parameters={parameters[event.name]}
                    />
                </Card.Body>
            }
        </Card>
    );
};

export default observer(EventListItem);