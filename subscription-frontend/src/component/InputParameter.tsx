import React, {FC} from 'react';
import {Form} from "react-bootstrap";
import {IParameterComponent} from "./Parameter";

const InputParameter = () => {//: FC<IParameterComponent> = ({ parameter }) => {
    return (
        //<div>
            <Form.Control type="text" placeholder="Normal text" />
        //</div>
    );
};

export default InputParameter;