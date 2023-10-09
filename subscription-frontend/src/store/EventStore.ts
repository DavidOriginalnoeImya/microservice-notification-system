import {makeAutoObservable, observable} from "mobx";
import axios from "axios";
import getResourcePath from "../utils/ServerPathCreator";
import getSubscriptionPath from "../utils/getSubscriptionPath";


export interface IEvent {
    name: string;
    caption: string;
}

interface IEventSubscription {
    eventName: string;
    eventService: string;
}

class EventStore {
    private subscriptionPath = getSubscriptionPath("/api/event-subs");

    events: IEvent[] = [];

    currentEventName = "";

    eventSubscriptions = new Set<string>();

    constructor() {
        makeAutoObservable(this);
    }

    public initEvents = async (serviceName: string) => {
        const params = new URLSearchParams([["service-name", serviceName]]);

        const {data} = await axios.get<IEvent[]>(
            getResourcePath("/api/events"),
            { params }
        );

        if (Array.isArray(data)) {
            this.setEvents(data);
        }

        await this.initEventSubscriptions(serviceName);
    }

    public addEventSubscriptions = async (eventName: string, serviceName: string) => {
        const body = {eventName: eventName, serviceName: serviceName};

        const { data } = await axios.post(this.subscriptionPath, body);
    }

    public delEventSubscription = async (eventName: string, serviceName: string) => {
        const params = new URLSearchParams([
            ["service-name", serviceName],
            ["event-name", eventName]
        ]);

        const { data } = await axios.delete(this.subscriptionPath, {params: params});
    }

    private initEventSubscriptions = async (serviceName: string) => {
        const params = new URLSearchParams([
            ["service-name", serviceName]
        ]);

        const { data } = await axios.get<IEventSubscription[]>(
            this.subscriptionPath, {params: params}
        );

        if (Array.isArray(data)) {
            this.setEventSubscriptions(data);
        }
    }

    public setEvents(events: IEvent[]) {
        this.events = events;
    }

    public getEvents() {
        return this.events;
    }

    public setCurrentEventName = (eventName: string) => {
        this.currentEventName = eventName;
    }

    private setEventSubscriptions(eventSubscriptions: IEventSubscription[]) {
        this.eventSubscriptions = new Set<string>(
            eventSubscriptions.map(
                eventSub => eventSub.eventName
            )
        );
    }
}

const eventStore= new EventStore();

export default observable(eventStore);