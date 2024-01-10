import React, {FC} from 'react';
import {ListGroup, ListGroupItem} from "react-bootstrap";
import {IService} from "../stores/ServiceStore";
import {observer} from "mobx-react-lite";

interface IServiceList {
    services: IService[];
    onServiceClick: (serviceName: string) => void;
}

const ServiceList: FC<IServiceList> = ({ services, onServiceClick }) => {
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
                            onClick={() => onServiceClick(service.name)}
                        >
                            {service.caption}
                        </ListGroupItem>
                )
            }
        </ListGroup>
    );
};

export default observer(ServiceList);