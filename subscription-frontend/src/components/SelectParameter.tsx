import React, {FC} from 'react';
import Select from "react-select";
import {IUpdatableParameterComponent} from "./Parameter";
import getSelectOptions from "../utils/getSelectOptions";
import {ISingleStringParameter} from "../stores/ParameterStore";
import {observer} from "mobx-react-lite";

interface ISelectParameter extends IUpdatableParameterComponent {
    parameter: ISingleStringParameter;
}

const SelectParameter: FC<ISelectParameter> = ({ parameter, onValueChange }) => {
    return (
        <Select
            defaultInputValue={parameter.value}
            options={getSelectOptions(parameter.options)}
            onChange={newValue => onValueChange(newValue!.value)}
        />
    );
};

export default observer(SelectParameter);