import {makeAutoObservable} from "mobx";
import axios from "axios";
import getResourcePath from "../utils/getResourcePath";
import getSubscriptionPath from "../utils/getSubscriptionPath";
import {HttpConstant} from "../constant/HttpConstant";

interface IParameter {
    name: string;
    caption: string;
    options: string[];
    inputType: InputType;
}

export interface IValueParameter extends IParameter {
    checked: boolean;
    value?: SubscriptionValue;
}

export interface IMultiStringParameter extends IValueParameter {
    inputType: InputType.MULTISELECT;
    value: string[];
}

export interface ISingleStringParameter extends IValueParameter {
    inputType: InputType.SELECT | InputType.INPUT;
    value: string;
}

export enum InputType {
    MULTISELECT = "MULTISELECT",
    INPUT = "INPUT",
    SELECT = "SELECT",
    CHECKBOX = "CHECKBOX"
}

type SubscriptionValue = string | string[];

type ParameterSubscriptions = Record<string, SubscriptionValue>;

interface IParameterSubscription {
    parameterName: string;
    value: SubscriptionValue;
}

export interface IParameterInfo {
    parameterName: string;
    eventName: string;
    serviceName: string;
    inputType?: InputType;
}

class ParameterStore {
    private subscriptionPath = getSubscriptionPath("/api/parameter-subs");

    private resourcePath = getResourcePath("/api/parameters");

    parameters: IValueParameter[] = [];

    constructor() {
        makeAutoObservable(this);
    }

    public initParameterStore = async (eventName: string, serviceName: string) => {
        const response = await this.getParameterSubscriptions(eventName, serviceName);

        const parameterSubscriptions = this.convertToMap(response.data);

        this.getParameters(eventName, serviceName)
            .then(response => response.data)
            .then(parameters =>
                parameters.map(parameter => this.createParameter(parameter, parameterSubscriptions))
            )
            .then(parameters => this.setParameters(parameters))
            .catch(() => alert("Ошибка инициализации параметров"));
    }

    public addParameterSubscription = (parameterInfo: IParameterInfo) => {
        const body = {
            parameterName: parameterInfo.parameterName,
            eventName: parameterInfo.eventName,
            domainAppName: parameterInfo.serviceName
        }

        axios.post(this.subscriptionPath, body)
            .then(response => response.status === HttpConstant.HTTP_CREATED)
            .then((checked) =>
                this.setParameterChecked(this.findParameterByName(parameterInfo.parameterName)!, checked)
            )
            .catch(() => alert("Не удалось подписаться на параметр"));
    }

    public updateParameterSubscription = (parameterInfo: IParameterInfo, newValue: SubscriptionValue) => {
        const body = {
            parameterName: parameterInfo.parameterName, eventName: parameterInfo.eventName,
            domainAppName: parameterInfo.serviceName, inputType: parameterInfo.inputType,
            value: newValue,
        };

        type PutResponse = { parameterName: string; value: SubscriptionValue; }

        axios.put<PutResponse>(this.subscriptionPath, body)
            .then(response => response.data)
            .then(
                data => this.setParameterValue(
                    this.findParameterByName(parameterInfo.parameterName)!, data.value
                )
            )
            .catch(() => alert("Ошибка обновления подписки на параметр"));
    }

    public deleteParameterSubscription = async (parameterInfo: IParameterInfo) => {
        const params = new URLSearchParams([
            ["event-name", parameterInfo.eventName],
            ["service-name", parameterInfo.serviceName]
        ]);

        axios.delete(this.subscriptionPath + `/${parameterInfo.parameterName}`, {params: params})
            .then(response => response.status === HttpConstant.HTTP_NO_CONTENT)
            .then((checked) =>
                this.setParameterChecked(this.findParameterByName(parameterInfo.parameterName)!, !checked)
            )
            .catch(() => alert("Не удалось отписаться от параметра"));
    }

    private getParameters = (eventName: string, serviceName: string) => {
        const searchParams = new URLSearchParams([
            ["event-name", eventName],
            ["service-name", serviceName]
        ]);

        return  axios.get<IParameter[]>(this.resourcePath, {params: searchParams});
    }

    private getParameterSubscriptions = (eventName: string, serviceName: string) => {
        const params = new URLSearchParams([
            ["service-name", serviceName],
            ["event-name", eventName]
        ]);

        return  axios.get<IParameterSubscription[]>(this.subscriptionPath, {params: params});
    }

    private createParameter(serverParameter: IParameter, parameterSubscriptions: ParameterSubscriptions) {
        const parameter: IValueParameter = { ...serverParameter, checked: false };

        if (Object.hasOwn(parameterSubscriptions, parameter.name)) {
            this.setParameterValue(parameter, parameterSubscriptions[parameter.name]);
            this.setParameterChecked(parameter, true);
        }

        return parameter;
    }

    private convertToMap(parameterSubscriptions: IParameterSubscription[]) {
        const parameterSubscriptionsMap: ParameterSubscriptions = {};
        parameterSubscriptions.forEach(
            paramSub =>
                parameterSubscriptionsMap[paramSub.parameterName] = paramSub.value
        )

        return parameterSubscriptionsMap;
    }

    private setParameters(parameters: IValueParameter[]) {
        this.parameters = parameters;
    }

    private setParameterChecked(parameter: IValueParameter, checked: boolean) {
        parameter.checked = checked;
    }

    private setParameterValue(parameter: IValueParameter, value: SubscriptionValue) {
        parameter.value = value;
    }

    private findParameterByName(parameterName: string) {
        return this.parameters.find(parameter => parameter.name === parameterName);
    }
}

const parameterStore = new ParameterStore();

export default parameterStore;