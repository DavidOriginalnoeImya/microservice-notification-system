import React, {FC} from 'react';
import Select from "react-select";
import {IUpdatableParameterComponent} from "./Parameter";
import getSelectOptions from "../utils/getSelectOptions";
import getSelectValues from "../utils/getSelectValues";
import {IMultiStringParameter} from "../stores/ParameterStore";
import {observer} from "mobx-react-lite";

export interface IMultiselectParameter extends IUpdatableParameterComponent{
    parameter: IMultiStringParameter;
}

const MultiselectParameter: FC<IMultiselectParameter> = ({ parameter, onValueChange }) => {
    console.log(parameter.value)

    return (
        <Select
            value={getSelectOptions(parameter.value)}
            options={getSelectOptions(parameter.options)}
            isMulti={true}
            onChange={(newValues) => onValueChange(getSelectValues(newValues))}
        />
    );
};

export default observer(MultiselectParameter);