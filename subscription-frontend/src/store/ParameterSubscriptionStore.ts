import axios from "axios";
import getSubscriptionPath from "../utils/getSubscriptionPath";
import {InputType} from "./ParameterStore";

type ParameterValue = string | string[];

export interface IParameterInfo {
    parameterName: string;
    eventName: string;
    serviceName: string;
    inputType?: InputType;
}

class ParameterSubscriptionStore {
    private serverPath = getSubscriptionPath("/api/parameter-subs");


    public addParameterSubscription =
        async (parameterName: string, eventName: string, serviceName: string) => {
            const body = {
                parameterName: parameterName,
                eventName: eventName,
                domainAppName: serviceName
            }

            const { data } = await axios.post(this.serverPath, body);
        }

    public updateParameterSubscription =
        async (parameterInfo: IParameterInfo, newValue: ParameterValue) => {
            const body = {
                parameterName: parameterInfo.parameterName,
                eventName: parameterInfo.eventName,
                domainAppName: parameterInfo.serviceName,
                inputType: parameterInfo.inputType,
                value: newValue,
            };

            const { data } = await axios.put(this.serverPath, body);
        }

    public deleteParameterSubscription =
        async (parameterName: string, eventName: string, serviceName: string) => {
            const params = new URLSearchParams([
                ["event-name", eventName],
                ["service-name", serviceName]
            ]);

            const { data } = await axios.delete(
                this.serverPath + `/${parameterName}`, {params: params}
            );
        }
}

const parameterSubscriptionStore = new ParameterSubscriptionStore();

export default parameterSubscriptionStore;