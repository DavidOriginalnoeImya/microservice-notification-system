import React, {FC} from 'react';
import Select from "react-select";
import parameter, {IParameterComponent} from "./Parameter";
import getSelectOptions from "../utils/SelectOptionsConverters";

const MultiselectParameter: FC<IParameterComponent> = ({ parameter }) => {
    return (
        //<div>
          //  { parameter.caption }
            <Select
                options={getSelectOptions(parameter.options)}
                isMulti={true}
            />
        //</div>
    );
};

export default MultiselectParameter;