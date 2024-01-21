package com.govtech.project.service;


import com.govtech.project.dto.GatheringEventDto;
import com.govtech.project.entity.GatheringEvent;
import org.springframework.stereotype.Component;


@Component
public interface GatheringEventService {
    GatheringEventDto startEvent(GatheringEvent event);

    GatheringEventDto closeEvent(GatheringEventDto eventDto);
}
