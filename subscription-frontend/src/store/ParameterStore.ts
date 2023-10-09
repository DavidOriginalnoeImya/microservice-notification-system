import {makeAutoObservable} from "mobx";
import axios from "axios";
import getResourcePath from "../utils/ServerPathCreator";

export interface IParameter {
    name: string;
    caption: string;
    options: string[];
    inputType: InputType;
    value?: string[];
}

export enum InputType {
    MULTISELECT = "MULTISELECT",
    INPUT = "INPUT",
    SELECT = "SELECT",
    CHECKBOX = "CHECKBOX"
}

class ParameterStore {
    parameters: IParameter[] = [];

    constructor() {
        makeAutoObservable(this);
    }

    public initParameters = async (eventName: string, serviceName: string) => {
        const params = new URLSearchParams([
            ["event-name", eventName],
            ["service-name", serviceName]
        ]);

        const { data } = await axios.get(getResourcePath("/api/parameters"), {params: params});

        if (Array.isArray(data)) {
            this.setParameters(data);
        }
    }

    private setParameters(parameters: IParameter[]) {
        this.parameters = parameters;
    }
}

const parameterStore = new ParameterStore();

export default parameterStore;