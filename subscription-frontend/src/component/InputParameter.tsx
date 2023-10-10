import React, {FC} from 'react';
import {Form} from "react-bootstrap";
import {IUpdatableParameterComponent} from "./Parameter";

const InputParameter: FC<IUpdatableParameterComponent> = ({  onValueChange }) => {
    return (
        <Form.Control
            type="text"
            placeholder="Enter value..."
            onBlur={event => onValueChange(event.target.value)}
        />
    );
};

export default InputParameter;