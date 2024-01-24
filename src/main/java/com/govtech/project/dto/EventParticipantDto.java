package com.govtech.project.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalTime;

@Getter
@Setter
@ToString
@JsonRootName("event_participant")
public class EventParticipantDto {

    @JsonProperty("user_id")
    private Long userId;

    @NotBlank(message = "Participant user given name is mandatory.")
    @JsonProperty("user_name")
    private String userName;

    @JsonProperty("restaurant_id")
    private Long restaurantId;

    @JsonProperty("restaurant")
    private String restaurant;

    @JsonProperty("description")
    private String description;

    @JsonProperty("address")
    private String address;

    @JsonProperty("open_time")
    private LocalTime openTime;

    @JsonProperty("close_time")
    private LocalTime closeTime;

    @JsonProperty("remarks")
    private String remarks;
}
