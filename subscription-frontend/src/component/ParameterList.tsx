import React, {FC} from 'react';
import {Form} from "react-bootstrap";
import {IParameter} from "../store/ParameterStore";
import Parameter from "./Parameter";

interface IParameterList {
    parameters: IParameter[];
}

const ParameterList: FC<IParameterList> = ({ parameters }) => {
    return (
        <>
            {
                parameters.map(
                    (parameter) => <Parameter parameter={parameter} />
                )
            }
        </>
    );
};

export default ParameterList;