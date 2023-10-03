import React, {FC} from 'react';
import {ListGroup, ListGroupItem} from "react-bootstrap";
import {IService} from "../store/ServiceStore";
import {observer} from "mobx-react-lite";

interface IServiceList {
    services: IService[];
}


const ServiceList: FC<IServiceList> = ({ services }) => {
    return (
        <ListGroup
            style={{width: "20%"}}
            className="rounded-0"
        >
            {
                services.map(
                    service =>
                        <ListGroupItem key={service.name}>
                            {service.caption}
                        </ListGroupItem>
                )
            }
        </ListGroup>
    );
};

export default observer(ServiceList);