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

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "event_id", insertable = false, updatable = false)
    private Event event;

    private boolean checked;

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
