import React, {FC} from 'react';
import {ListGroup, ListGroupItem} from "react-bootstrap";
import serviceStore, {IService} from "../store/ServiceStore";
import {observer} from "mobx-react-lite";
import eventStore from "../store/EventStore";

interface IServiceList {
    services: IService[];
}


const ServiceList: FC<IServiceList> = ({ services }) => {
    const onServiceClicked = (serviceName: string) => {
        serviceStore.setCurrentServiceName(serviceName);
        eventStore.initEvents(serviceName);
    }

    return (
        <ListGroup
            style={{width: "20%"}}
            className="rounded-0"
        >
            {
                services.map(
                    service =>
                        <ListGroupItem
                            key={service.name}
                            onClick={() => onServiceClicked(service.name)}
                        >
                            {service.caption}
                        </ListGroupItem>
                )
            }
        </ListGroup>
    );
};

export default observer(ServiceList);