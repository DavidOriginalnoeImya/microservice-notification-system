package edu.home.registrationservice.dto.service;

public class AddServiceDTO {

    private String serviceName;

    private String serviceCaption;

    private String serviceAddress;

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceAddress() {
        return serviceAddress;
    }

    public void setServiceAddress(String serviceAddress) {
        this.serviceAddress = serviceAddress;
    }

    public String getServiceCaption() {
        return serviceCaption;
    }

    public void setServiceCaption(String serviceCaption) {
        this.serviceCaption = serviceCaption;
    }
}
