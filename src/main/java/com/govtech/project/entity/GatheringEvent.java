package com.govtech.project.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;
import java.util.Set;

@Setter
@Getter
@Entity(name = "gathering_event")
public class GatheringEvent extends BaseEntity {

    @JsonProperty("restaurant")
    @JoinColumn(name = "restaurant")
    @ManyToOne
    private Restaurant restaurant;

    @JsonProperty("initiator")
    @JoinColumn(name = "initiator")
    @ManyToOne
    private UserInfo initiator;

    @JsonProperty("event_date")
    @Column(name = "event_date")
    @Temporal(value = TemporalType.DATE)
    private LocalDate eventDate;

    @JsonProperty("start_time")
    @Column(name = "start_time")
    @Temporal(value = TemporalType.TIME)
    private LocalTime startTime;

    @JsonProperty("end_time")
    @Column(name = "end_time")
    @Temporal(value = TemporalType.TIME)
    private LocalTime endTime;

    @JsonProperty("active")
    @JoinColumn(name = "active")
    private Boolean active;

    @JsonProperty("participants")
    @OneToMany(mappedBy = "gatheringEvent",
            orphanRemoval = true,
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<EventParticipant> participants;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        GatheringEvent that = (GatheringEvent) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }

    @Override
    public String toString() {
        return "GatheringEvent{" +
                "restaurant=" + restaurant +
                ", initiator=" + initiator +
                ", eventDate=" + eventDate +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", active=" + active +
                ", participants=" + participants +
                '}';
    }
}
