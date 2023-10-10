import React, {FC} from 'react';
import {Col, Form} from "react-bootstrap";
import {IParameter} from "../store/ParameterStore";
import Parameter from "./Parameter";
import eventStore from "../store/EventStore";
import serviceStore from "../store/ServiceStore";

interface IParameterList {
    parameters: IParameter[];
}

const ParameterList: FC<IParameterList> = ({ parameters }) => {

    const { currentEvent } = eventStore;

    const { currentServiceName } = serviceStore;

    return (
        <Col className="mt-3 ms-4">
            {
                parameters.map(
                    (parameter) =>
                        <Parameter
                            parameter={parameter}
                            key={currentServiceName + currentEvent + parameter.name}
                        />
                )
            }
        </Col>
    );
};

export default ParameterList;