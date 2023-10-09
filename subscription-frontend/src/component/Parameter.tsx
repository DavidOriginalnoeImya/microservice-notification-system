import React, {FC, ReactNode} from 'react';
import {InputType, IParameter} from "../store/ParameterStore";
import MultiselectParameter from "./MultiselectParameter";
import {Form} from "react-bootstrap";
import InputParameter from "./InputParameter";
import SelectParameter from "./SelectParameter";
import parameterSubscriptionStore from "../store/ParameterSubscriptionStore";
import eventStore from "../store/EventStore";
import serviceStore from "../store/ServiceStore";

export interface IParameterComponent {
    parameter: IParameter
}

const Parameter: FC<IParameterComponent> = ({ parameter }) => {
    const parameterComponents: Record<InputType, ReactNode> = {
        MULTISELECT: <MultiselectParameter parameter={parameter}/>,
        INPUT: <InputParameter/>,
        SELECT: <SelectParameter parameter={parameter}/>,
        CHECKBOX: null
    }

    const { currentEventName } = eventStore;

    const { currentServiceName } = serviceStore;

    const { addParameterSubscription, deleteParameterSubscription } = parameterSubscriptionStore;

    const isCheckboxParameter = () => {
        return parameter.inputType === InputType.CHECKBOX;
    }

    const onCheckboxChanged = (e: React.ChangeEvent<HTMLInputElement>) => {
        e.stopPropagation();
        e.target.checked ? addParameterSubscription(parameter.name, currentEventName, currentServiceName) :
            deleteParameterSubscription(parameter.name, currentEventName, currentServiceName);
    }

    return (
        <div className="d-flex">
            <Form.Check
                className="me-2"
                onChange={onCheckboxChanged}
            />
            <div>
                { parameter.caption }
                {
                    !isCheckboxParameter() &&
                    parameterComponents[parameter.inputType]
                }
            </div>
        </div>
    );
};

export default Parameter;