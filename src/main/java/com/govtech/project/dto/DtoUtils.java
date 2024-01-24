package com.govtech.project.dto;

import com.govtech.project.entity.GatheringEvent;
import com.govtech.project.entity.UserInfo;

import java.time.LocalDateTime;

public abstract class DtoUtils {
    public static UserInfo asUserInfo(String givenName) {
        UserInfo userInfo = new UserInfo();
        userInfo.setGivenName(givenName);
        userInfo.setUpdatedBy(0L);
        userInfo.setCreatedBy(0L);
        userInfo.setUpdatedOn(LocalDateTime.now());
        userInfo.setCreatedOn(LocalDateTime.now());
        return userInfo;
    }

    public static GatheringEvent asGatheringEvent(GatheringEventDto dto) {
        GatheringEvent event = new GatheringEvent();
        event.setActive(true);
        event.setInitiator(asUserInfo(dto.getGivenName()));
        event.setEventDate(dto.getEventDate());
        event.setStartTime(dto.getStartTime());
        event.setEndTime(dto.getEndTime());
        //
        event.setUpdatedBy(0L);
        event.setCreatedBy(0L);
        event.setUpdatedOn(LocalDateTime.now());
        event.setCreatedOn(LocalDateTime.now());
        return event;
    }

    public static GatheringEventDto asGatheringEventDto(UserInfo userInfo, GatheringEvent event) {
        GatheringEventDto dto = new GatheringEventDto();
        dto.setUserId(userInfo.getId());
        dto.setUserRev(userInfo.getRev());
        dto.setGivenName(userInfo.getGivenName());
        dto.setEventDate(event.getEventDate());
        dto.setStartTime(event.getStartTime());
        dto.setEndTime(event.getEndTime());
        dto.setEventId(event.getId());
        dto.setEventRev(event.getRev());
        return dto;
    }
}
