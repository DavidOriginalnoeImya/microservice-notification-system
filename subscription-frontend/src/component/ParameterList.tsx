import React, {FC} from 'react';
import {Form} from "react-bootstrap";

interface IParameterList {
    parameters: string[];
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
                            { parameter }
                        </div>

                )
            }
        </>
    );
};

export default ParameterList;