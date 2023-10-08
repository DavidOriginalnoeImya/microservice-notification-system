import axios from "axios";
import getSubscriptionPath from "../utils/getSubscriptionPath";

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
}

const parameterSubscriptionStore = new ParameterSubscriptionStore();

export default parameterSubscriptionStore;