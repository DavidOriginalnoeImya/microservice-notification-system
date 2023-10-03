import {makeAutoObservable} from "mobx";
import axios from "axios";
import getResourcePath from "../utils/ServerPathCreator";

export interface IService {
    name: string;
    caption: string;
}

class ServiceStore {

    services: IService[] = [];

    constructor() {
        makeAutoObservable(this);
        this.getServicesFromServer();
    }

    private async getServicesFromServer() {
        const {data} = await axios
            .get<IService[]>(getResourcePath("/api/services"));

        if (Array.isArray(data)) {
            this.setServices(data);
        }
    }

    public setServices(services: IService[]) {
        this.services = services;
    }

    public getServices() {
        return this.services;
    }

}

const serviceStore = new ServiceStore();

export default serviceStore;