package com.govtech.project.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.util.Objects;

@Setter
@Getter
@ToString
@Entity(name = "event_participant")
@JsonRootName("event_participant")
public class EventParticipant extends BaseEntity {

    @ManyToOne
    @JsonProperty("gathering_event")
    @JoinColumn(name = "gathering_event")
    private GatheringEvent gatheringEvent;

    @ManyToOne
    @JsonProperty("restaurant")
    @JoinColumn(name = "restaurant")
    private Restaurant restaurant;

    @ManyToOne
    @JsonProperty("user_info")
    @JoinColumn(name = "user_info")
    private UserInfo userInfo;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        EventParticipant that = (EventParticipant) o;
        return Objects.equals(restaurant, that.restaurant) && Objects.equals(userInfo, that.userInfo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), restaurant, userInfo);
    }
}
