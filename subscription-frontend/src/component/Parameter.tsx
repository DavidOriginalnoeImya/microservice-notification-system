import React, {FC, ReactNode} from 'react';
import {InputType, IParameter} from "../store/ParameterStore";
import MultiselectParameter from "./MultiselectParameter";
import {Col, Form, Row} from "react-bootstrap";
import InputParameter from "./InputParameter";
import SelectParameter from "./SelectParameter";
import parameterSubscriptionStore from "../store/ParameterSubscriptionStore";
import eventStore from "../store/EventStore";
import serviceStore from "../store/ServiceStore";
import getSelectValues from "../utils/getSelectValues";

export interface IParameterComponent {
    parameter: IParameter
}

type ParameterValue = string | string[];

export interface IUpdatableParameterComponent extends IParameterComponent {
    onValueChange(value: ParameterValue): void;
}

const Parameter: FC<IParameterComponent> = ({ parameter }) => {
    const { currentEvent } = eventStore;

    const { currentServiceName } = serviceStore;

    const { updateParameterSubscription } = parameterSubscriptionStore;

    const onValueChange = (newValue: string | string[]) => {
        const parameterInfo = {
            parameterName: parameter.name,
            eventName: currentEvent.name,
            serviceName: currentServiceName,
            inputType: parameter.inputType
        };

        updateParameterSubscription(parameterInfo, newValue);
    }

    const parameterComponents: Record<InputType, ReactNode> = {
        MULTISELECT: <MultiselectParameter parameter={parameter} onValueChange={onValueChange}/>,
        INPUT: <InputParameter parameter={parameter} onValueChange={onValueChange}/>,
        SELECT: <SelectParameter parameter={parameter} onValueChange={onValueChange}/>,
        CHECKBOX: null
    }

    const { addParameterSubscription, deleteParameterSubscription } = parameterSubscriptionStore;

    const isCheckboxParameter = () => {
        return parameter.inputType === InputType.CHECKBOX;
    }

    const onCheckboxChanged = (e: React.ChangeEvent<HTMLInputElement>) => {
        e.stopPropagation();
        e.target.checked ?
            addParameterSubscription(parameter.name, currentEvent.name, currentServiceName) :
            deleteParameterSubscription(parameter.name, currentEvent.name, currentServiceName);
    }

    return (
        <div className="d-flex mb-3">
            <Form.Check
                className="me-2"
                onChange={onCheckboxChanged}
            />
            <Col>
                { parameter.caption }
                {
                    !isCheckboxParameter() &&
                    parameterComponents[parameter.inputType]
                }
            </Col>
        </div>
    );
};

export default Parameter;