import {makeAutoObservable} from "mobx";

export interface IService {
    name: string;
    caption: string;
}

class ServiceStore {

    services: IService[] = [
        {name: "News", caption: "Новости"},
        {name: "Calendar", caption: "Календарь"},
    ];

    constructor() {
        makeAutoObservable(this);
    }
}

const serviceStore = new ServiceStore();

export default serviceStore;