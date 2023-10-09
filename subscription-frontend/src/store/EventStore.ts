import {makeAutoObservable, observable} from "mobx";
import axios from "axios";
import getResourcePath from "../utils/ServerPathCreator";


export interface IEvent {
    name: string;
    caption: string;
}

class EventStore {
    events: IEvent[] = [];

    currentEventName = "";

    constructor() {
        makeAutoObservable(this);
    }

    public getEventsFromServer = async (serviceName: string) => {
        const params = new URLSearchParams([["service-name", serviceName]]);

        const {data} = await axios.get<IEvent[]>(
            getResourcePath("/api/events"),
            { params }
        );

        if (Array.isArray(data)) {
            this.setEvents(data);
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
}

const eventStore= new EventStore();

export default observable(eventStore);