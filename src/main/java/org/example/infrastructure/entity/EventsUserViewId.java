package org.example.infrastructure.entity;

import java.io.Serializable;
import java.util.Objects;

public class EventsUserViewId implements Serializable {
    private Long user;
    private Long event;

    public EventsUserViewId() {}

    public EventsUserViewId(Long user, Long event) {
        this.user = user;
        this.event = event;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventsUserViewId that = (EventsUserViewId) o;
        return Objects.equals(user, that.user) && Objects.equals(event, that.event);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, event);
    }
}
