import React, {FC} from 'react';
import Select from "react-select";
import {IUpdatableParameterComponent} from "./Parameter";
import getSelectOptions from "../utils/getSelectOptions";

const SelectParameter: FC<IUpdatableParameterComponent> = ({ parameter, onValueChange }) => {
    return (
        <Select
            options={getSelectOptions(parameter.options)}
            onChange={newValue => onValueChange(newValue!.value)}
        />
    );
};

export default SelectParameter;