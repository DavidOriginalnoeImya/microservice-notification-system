import {makeAutoObservable} from "mobx";
import axios from "axios";
import getResourcePath from "../utils/ServerPathCreator";

export interface IParameter {
    name: string;
    parameterCaption: string;
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

    public initParameters = async (eventName: string, serviceName: string) => {
        const params = new URLSearchParams([
            ["event-name", eventName],
            ["service-name", serviceName]
        ]);

        const { data } = await axios.get(getResourcePath("/api/parameters"), {params: params});

        if (Array.isArray(data)) {
            this.setParameters(eventName, data);
        }
    }

    public cleanParameters = () => {
        this.parameters = {};
    }

    private setParameters(eventName: string, parameters: IParameter[]) {
        this.parameters[eventName] = parameters;
    }
}

const parameterStore = new ParameterStore();

export default parameterStore;