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
    events: IEvent[] = [];

    constructor() {
        makeAutoObservable(this);
    }

    public initEvents = async (serviceName: string) => {
        const urlParams = new URLSearchParams([["service-name", serviceName]]);

        try {
            const { data } = await axios.get<IEvent[]>(getResourcePath("/api/events"), {params: urlParams});

            if (Array.isArray(data)) {
                this.setEvents(data);
            }
        } catch (e) {
            alert("Event loading error");
        }
    }

    public addEventSubscription = async (eventName: string, serviceName: string) => {
        const requestBody = { eventName: eventName, serviceName: serviceName };

        try {
            const response = await axios.post(getResourcePath("/api/events"), requestBody);

            if (response.status === HttpStatus.CREATED) {
                this.setEventChecked(eventName);
            }
        } catch (e) {
            alert("Event subscription error");
        }
    }

    public delEventSubscription = async (eventName: string, serviceName: string) => {
        const urlParams = new URLSearchParams([
            ["service-name", serviceName],
            ["event-name", eventName]
        ]);

        try {
            const response = await axios.delete(getResourcePath("/api/events"), {params: urlParams});

            if (response.status === HttpStatus.OK) {
                this.setEventChecked(eventName, false);
            }
        } catch (e) {
            alert("Event unsubscription error");
        }
    }

    public setEvents(events: IEvent[]) {
        this.events = events;
    }

    private setEventChecked = (eventName: string, checked = true) => {
        this.events.forEach((event) => {
            if (event.name === eventName) {
                event.checked = checked;
            }
        })
    }
}

const eventStore= new EventStore();

export default observable(eventStore);