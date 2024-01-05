package ge.davab.subscriptionservice.registration.dto;

public class DomainAppDTO {

    private String serviceName;

    private String serviceCaption;


    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceCaption() {
        return serviceCaption;
    }

    public void setServiceCaption(String serviceCaption) {
        this.serviceCaption = serviceCaption;
    }
}
