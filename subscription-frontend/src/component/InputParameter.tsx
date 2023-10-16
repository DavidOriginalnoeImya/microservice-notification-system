import React, {FC} from 'react';
import {Form} from "react-bootstrap";
import {IUpdatableParameterComponent} from "./Parameter";
import {ISingleStringParameter} from "../store/ParameterStore";
import {observer} from "mobx-react-lite";

interface IInputParameter extends IUpdatableParameterComponent {
    parameter: ISingleStringParameter;
}

const InputParameter: FC<IInputParameter> = ({ parameter, onValueChange }) => {
    return (
        <Form.Control
            type="text"
            defaultValue={parameter.value}
            onBlur={event => onValueChange(event.target.value)}
        />
    );
};

export default observer(InputParameter);