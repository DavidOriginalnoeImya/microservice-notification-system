import {makeAutoObservable} from "mobx";
import axios from "axios";
import getSubscriptionPath from "../utils/getSubscriptionPath";

interface IEventSubscription {
    eventName: string;
    eventService: string;
}

class EventSubscriptionStore {
    private serverPath = getSubscriptionPath("/api/event-subs");

    eventSubscriptions = new Set<string>();

    constructor() {
        makeAutoObservable(this);
    }

    public initEventSubscriptions = async (serviceName: string) => {
        const params = new URLSearchParams([
            ["service-name", serviceName]
        ]);

        const { data } = await axios.get<IEventSubscription[]>(
            this.serverPath, {params: params}
        );

        if (Array.isArray(data)) {
            this.setEventSubscriptions(data);
        }
    }

    public addEventSubscriptions = async (eventName: string, serviceName: string) => {
        const body = {eventName: eventName, serviceName: serviceName};

        const { data } = await axios.post(this.serverPath, body);
    }

    public delEventSubscription = async (eventName: string, serviceName: string) => {
        const params = new URLSearchParams([
            ["service-name", serviceName],
            ["event-name", eventName]
        ]);

        const { data } = await axios.delete(this.serverPath, {params: params});
    }

    private setEventSubscriptions(eventSubscriptions: IEventSubscription[]) {
        this.eventSubscriptions = new Set<string>(
            eventSubscriptions.map(
                eventSub => eventSub.eventName
            )
        );
    }


}

const eventSubscriptionStore = new EventSubscriptionStore();

export default eventSubscriptionStore;