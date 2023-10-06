import React, {FC} from 'react';
import Select from "react-select";
import {IParameterComponent} from "./Parameter";
import getSelectOptions from "../utils/SelectOptionsConverters";

const SelectParameter: FC<IParameterComponent> = ({ parameter }) => {
    return (
        <Select
            options={getSelectOptions(parameter.options)}
            isMulti={true}
        />
    );
};

export default SelectParameter;