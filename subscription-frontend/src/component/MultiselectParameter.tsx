import React, {FC} from 'react';
import Select, {MultiValue} from "react-select";
import {IUpdatableParameterComponent} from "./Parameter";
import getSelectOptions from "../utils/getSelectOptions";
import parameterSubscriptionStore from "../store/ParameterSubscriptionStore";
import getSelectValues from "../utils/getSelectValues";

const MultiselectParameter: FC<IUpdatableParameterComponent> = ({ parameter, onValueChange }) => {
    return (
        <Select
            options={getSelectOptions(parameter.options)}
            isMulti={true}
            onChange={(newValues) => onValueChange(getSelectValues(newValues))}
        />
    );
};

export default MultiselectParameter;