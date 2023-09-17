package edu.home.subscriptionservice.data.subscription;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class Id implements Serializable {

    private Long firstEntityId;

    private Long secondEntityId;

    public Id() {}

    public Id(Long firstEntityId, Long secondEntityId) {
        this.firstEntityId = firstEntityId;
        this.secondEntityId = secondEntityId;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Id id)) return false;

        return Objects.equals(this.firstEntityId, id.firstEntityId) &&
                Objects.equals(this.secondEntityId, id.secondEntityId);
    }

    @Override
    public int hashCode() {
        return firstEntityId.hashCode() + secondEntityId.hashCode();
    }
}
