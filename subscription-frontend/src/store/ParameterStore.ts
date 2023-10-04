import {makeAutoObservable} from "mobx";

export interface IParameter {
    name: string;
    caption: string;
    options: string[];
    inputType: InputType;
    value?: string[];
}

enum InputType {
    MULTISELECT = "MULTISELECT",
}

class ParameterStore {
    parameters: Record<string, IParameter[]> = {};

    constructor() {
        makeAutoObservable(this);
    }


}

const parameterStore = new ParameterStore();

export default parameterStore;