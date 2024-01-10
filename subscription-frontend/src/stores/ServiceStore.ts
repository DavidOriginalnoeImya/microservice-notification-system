import {makeAutoObservable} from "mobx";
import axios from "axios";
import getResourcePath from "../utils/getResourcePath";

export interface IService {
    name: string;
    caption: string;
}

class ServiceStore {

    services: IService[] = [];

    currentServiceName: string = "";

    constructor() {
        makeAutoObservable(this);
        this.getServicesFromServer();
    }

    private async getServicesFromServer() {
        try {
            const { data } = await axios.get<IService[]>(getResourcePath("/api/services"));

            if (Array.isArray(data)) {
                this.setServices(data);
            }
        } catch (e) {
            alert("Service loading error");
        }
    }

    public setServices(services: IService[]) {
        this.services = services;
    }

    public getServices() {
        return this.services;
    }

    public setCurrentServiceName(serviceName: string) {
        this.currentServiceName = serviceName;
    }

    public getCurrentServiceName() {
        return this.currentServiceName;
    }
}

const serviceStore = new ServiceStore();

export default serviceStore;