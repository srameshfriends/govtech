package com.govtech.project.service;

import com.govtech.project.dto.GatheringEventDto;
import com.govtech.project.entity.GatheringEvent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public interface GatheringEventService {
    GatheringEventDto startEvent(GatheringEvent event);

    GatheringEventDto closeEvent(GatheringEventDto eventDto);

    List<GatheringEventDto> search(PageRequest pageRequest);
}
