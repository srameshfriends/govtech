package com.govtech.project.repository;

import com.govtech.project.entity.GatheringEvent;
import com.govtech.project.entity.UserInfo;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Component
public interface GatheringSessionRepo {

    UserInfo findByGivenName(String givenName);

    GatheringEvent findGatheringEvent(UserInfo userInfo, LocalDate eventDate, LocalTime startTime);
}
