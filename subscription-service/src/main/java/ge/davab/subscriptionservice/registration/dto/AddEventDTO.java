package ge.davab.subscriptionservice.registration.dto;

public class AddEventDTO extends AddEntityDTO {

    private String eventServiceName;

    public String getEventServiceName() {
        return eventServiceName;
    }

    public void setEventServiceName(String eventServiceName) {
        this.eventServiceName = eventServiceName;
    }

}
