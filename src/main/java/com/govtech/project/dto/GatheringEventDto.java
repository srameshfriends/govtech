package com.govtech.project.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.govtech.project.entity.BaseEntity;
import com.govtech.project.entity.GatheringEvent;
import com.govtech.project.entity.UserInfo;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@ToString
@JsonRootName("gathering_event")
public class GatheringEventDto {

    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("event_id")
    private Long eventId;

    @NotBlank(message = "Create event session user given name is mandatory.")
    @JsonProperty("given_name")
    private String givenName;

    @JsonProperty("event_date")
    private LocalDate eventDate;

    @JsonProperty("start_time")
    private LocalTime startTime;

    @JsonProperty("end_time")
    private LocalTime endTime;

    @JsonIgnore
    public void validate() {
        if(!StringUtils.hasText(givenName)) {
            throw new NullPointerException("Gathering event given name should not be empty.");
        }
    }

    public UserInfo asUserInfo() {
        UserInfo userInfo = new UserInfo();
        userInfo.setGivenName(givenName);
        userInfo.setUpdatedBy(0L);
        userInfo.setCreatedBy(0L);
        userInfo.setUpdatedOn(LocalDateTime.now());
        userInfo.setCreatedOn(LocalDateTime.now());
        return userInfo;
    }

    public GatheringEvent asGatheringEvent() {
        GatheringEvent event = new GatheringEvent();
        event.setActive(true);
        event.setInitiator(asUserInfo());
        event.setEventDate(eventDate);
        event.setStartTime(startTime);
        event.setEndTime(endTime);
        //
        event.setUpdatedBy(0L);
        event.setCreatedBy(0L);
        event.setUpdatedOn(LocalDateTime.now());
        event.setCreatedOn(LocalDateTime.now());
        return event;
    }

    public static GatheringEventDto instance(UserInfo userInfo, GatheringEvent event) {
        GatheringEventDto dto = new GatheringEventDto();
        dto.setUserId(userInfo.getId());
        dto.setGivenName(userInfo.getGivenName());
        dto.setEventDate(dto.getEventDate());
        dto.setStartTime(dto.getStartTime());
        dto.setEndTime(dto.getEndTime());
        dto.setEventId(event.getId());
        return dto;
    }
}
