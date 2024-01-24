package com.govtech.project.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@ToString
@JsonRootName("gathering_event")
public class GatheringEventDto {

    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("user_rev")
    private Integer userRev;

    @JsonProperty("event_id")
    private Long eventId;

    @JsonProperty("event_rev")
    private Integer eventRev;

    @JsonProperty("restaurant_id")
    private Long restaurantId;

    @JsonProperty("restaurant_rev")
    private Integer restaurantRev;

    @JsonProperty("restaurant")
    private String restaurant;

    @JsonProperty("active")
    private Boolean active;

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
}
