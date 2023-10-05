import React, {FC} from 'react';
import {Form} from "react-bootstrap";
import {IParameter} from "../store/ParameterStore";

interface IParameterList {
    parameters: IParameter[];
}

const ParameterList: FC<IParameterList> = ({ parameters }) => {
    return (
        <>
            {
                parameters.map(
                    (parameter) =>
                        <div className="d-flex">
                            <Form.Check
                                className="me-2"
                            />
                            { parameter.parameterCaption }
                        </div>

                )
            }
        </>
    );
};

export default ParameterList;