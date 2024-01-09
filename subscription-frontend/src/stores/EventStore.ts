import {makeAutoObservable, observable} from "mobx";
import axios from "axios";
import getResourcePath from "../utils/getResourcePath";
import getSubscriptionPath from "../utils/getSubscriptionPath";
import {HttpStatus} from "../constants/HttpStatus";


export interface IEvent {
    name: string;
    caption: string;
    checked: boolean;
}

class EventStore {
    private subscriptionPath = getSubscriptionPath("/api/event-subs");

    events: IEvent[] = [];

    constructor() {
        makeAutoObservable(this);
    }

    public initEvents = async (serviceName: string) => {
        const params = new URLSearchParams([["service-name", serviceName]]);
        const events = (await axios.get<IEvent[]>(getResourcePath("/api/events"), {params: params})).data;

        if (Array.isArray(events)) {
            const { data } = await this.getEventSubscriptions(serviceName);
            const eventSubscriptions = new Set<string>(data.map(es => es.eventName));
            events.forEach((event) => event.checked = eventSubscriptions.has(event.name));
            this.setEvents(events);
        }
    }

    public addEventSubscriptions = (eventName: string, serviceName: string) => {
        const body = {eventName: eventName, serviceName: serviceName};

        axios.post(this.subscriptionPath, body)
            .then((response) => response.status === HttpStatus.CREATED)
            .then((checked) => this.updateEventSubscriptions(eventName, checked))
            .catch(() => alert("Не удалось подписаться на событие"));
    }

    public delEventSubscription = async (eventName: string, serviceName: string) => {
        const params = new URLSearchParams([
            ["service-name", serviceName],
            ["event-name", eventName]
        ]);

        axios.delete(this.subscriptionPath, {params: params})
            .then((response) => response.status === HttpStatus.OK)
            .then((checked) => this.updateEventSubscriptions(eventName, !checked))
            .catch(() => alert("Не удалось отписаться от события"));
    }

    public setEvents(events: IEvent[]) {
        this.events = events;
    }

    private updateEventSubscriptions = (eventName: string, checked: boolean) => {
        this.events.forEach((event) => {
            if (event.name === eventName) {
                event.checked = checked;
            }
        })
    }

    private getEventSubscriptions = (serviceName: string) => {
        const params = new URLSearchParams([
            ["service-name", serviceName]
        ]);

        return axios.get<{eventName: string}[]>(this.subscriptionPath, {params: params});
    }
}

const eventStore= new EventStore();

export default observable(eventStore);