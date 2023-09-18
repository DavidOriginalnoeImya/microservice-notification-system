package edu.home.subscriptionservice.data.subscription;

import edu.home.subscriptionservice.data.event.Event;
import edu.home.subscriptionservice.data.user.User;
import jakarta.persistence.*;

@Entity
public class EventSubscription {

    @EmbeddedId
    @AttributeOverrides({
        @AttributeOverride(name = "firstEntityId", column = @Column(name = "user_id")),
        @AttributeOverride(name = "secondEntityId", column = @Column(name = "event_id"))
    })
    private Id id = new Id();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", insertable = false, updatable = false)
    private Event event;

    private boolean checked;

    public EventSubscription() {}

    public EventSubscription(Event event, User user) {
        this.id = new Id(user.getId(), event.getId());
        this.user = user;
        this.event = event;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String getEventName() {
        return event.getName();
    }
}
