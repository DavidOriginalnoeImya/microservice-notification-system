import {makeAutoObservable, observable} from "mobx";


export interface IEvent {
    name: String;
    caption: String;
    // serviceName: String;
}

class EventStore {
    events: IEvent[] = [
        {name: "Event1", caption: "Событие 1"},
        {name: "Event2", caption: "Событие 2"},
        {name: "Event3", caption: "Событие 3"},
    ];

    constructor() {
        makeAutoObservable(this);
    }

    public getEvents() {
        return this.events;
    }
}

const eventStore= new EventStore();

export default observable(eventStore);