package edu.home.subscriptionservice.dto;

public class EventDTO {

    private String eventName;

    private String eventCaption;

    private boolean checked;

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventCaption() {
        return eventCaption;
    }

    public void setEventCaption(String eventCaption) {
        this.eventCaption = eventCaption;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
