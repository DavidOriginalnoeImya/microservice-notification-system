import {MultiValue} from "react-select";


const getSelectValues = (selectValues: MultiValue<{value: string, label: string}>) => {
    return selectValues.map(selectValue => selectValue.value);
}

export default getSelectValues;