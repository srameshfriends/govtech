package com.govtech.project.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;

@Setter
@Getter
@ToString
@Entity(name = "restaurant")
@JsonRootName("restaurant")
    public class Restaurant extends BaseEntity {

    @JsonProperty("name")
    @Column(name = "name")
    private String name;

    @JsonProperty("description")
    @Column(name = "description")
    private String description;

    @JsonProperty("open_time")
    @Column(name = "open_time")
    @Temporal(value = TemporalType.TIME)
    private LocalTime openTime;

    @JsonProperty("close_time")
    @Column(name = "close_time")
    @Temporal(value = TemporalType.TIME)
    private LocalTime closeTime;

}
