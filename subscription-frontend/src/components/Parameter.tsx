import React, {FC, ReactNode} from 'react';
import parameterStore, {
    IMultiStringParameter,
    InputType,
    IValueParameter,
    ISingleStringParameter
} from "../stores/ParameterStore";
import MultiselectParameter from "./MultiselectParameter";
import {Col, Form} from "react-bootstrap";
import InputParameter from "./InputParameter";
import SelectParameter from "./SelectParameter";
import eventStore from "../stores/EventStore";
import serviceStore from "../stores/ServiceStore";
import {observer} from "mobx-react-lite";

export interface IParameterComponent {
    parameter: IValueParameter
}

type SubscriptionValue = string | string[];

export interface IUpdatableParameterComponent {
    onValueChange(value: SubscriptionValue): void;
}

const Parameter: FC<IParameterComponent> = ({ parameter }) => {
    const { currentEvent } = eventStore;

    const { currentServiceName } = serviceStore;

    const onValueChange = (newValue: SubscriptionValue) => {
        const parameterInfo = {
            parameterName: parameter.name, eventName: currentEvent.name,
            serviceName: currentServiceName, inputType: parameter.inputType
        };

        parameterStore.updateParameterSubscription(parameterInfo, newValue);
    }

    const parameterComponents: Record<InputType, ReactNode> = {
        MULTISELECT: <MultiselectParameter parameter={parameter as IMultiStringParameter} onValueChange={onValueChange}/>,
        INPUT: <InputParameter parameter={parameter as ISingleStringParameter} onValueChange={onValueChange}/>,
        SELECT: <SelectParameter parameter={parameter as ISingleStringParameter} onValueChange={onValueChange}/>,
        CHECKBOX: null
    }
    
    const isCheckboxParameter = () => {
        return parameter.inputType === InputType.CHECKBOX;
    }

    const onCheckboxChanged = (e: React.ChangeEvent<HTMLInputElement>) => {
        e.stopPropagation();

        const parameterInfo = {
            parameterName: parameter.name,
            eventName: currentEvent.name,
            serviceName: currentServiceName
        };

        e.target.checked ? parameterStore.addParameterSubscription(parameterInfo)
                         : parameterStore.deleteParameterSubscription(parameterInfo);
    }

    return (
        <div className="d-flex mb-3">
            <Form.Check
                className="me-2"
                onChange={onCheckboxChanged}
                checked={parameter.checked}
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

export default observer(Parameter);