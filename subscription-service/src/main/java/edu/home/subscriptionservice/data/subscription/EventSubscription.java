package edu.home.subscriptionservice.data.subscription;

import edu.home.subscriptionservice.data.event.Event;
import edu.home.subscriptionservice.data.user.User;
import jakarta.persistence.*;

@Entity
public class EventSubscription {

    private static final String USER_ID_COLUMN = "user_id";

    private static final String EVENT_ID_COLUMN = "event_id";

    @EmbeddedId
    @AttributeOverrides({
        @AttributeOverride(name = Id.FIRST_ENTITY_ID, column = @Column(name = USER_ID_COLUMN)),
        @AttributeOverride(name = Id.SECOND_ENTITY_ID, column = @Column(name = EVENT_ID_COLUMN))
    })
    private Id id = new Id();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = USER_ID_COLUMN, insertable = false, updatable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = EVENT_ID_COLUMN, insertable = false, updatable = false)
    private Event event;

    protected EventSubscription() {}

    public EventSubscription(Event event, User user) {
        this.id = new Id(user.getId(), event.getId());
        this.user = user;
        this.event = event;
    }

    public String getEventName() {
        return event.getName();
    }

    public String getDomainAppName() {
        return event.getServiceName();
    }
}
