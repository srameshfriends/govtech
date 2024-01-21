package com.govtech.project.controls;

import com.govtech.project.dto.GatheringEventDto;
import com.govtech.project.service.GatheringEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/event")
public class GatheringEventController {

    private final GatheringEventService gatheringEventService;

    @Autowired
    public GatheringEventController(GatheringEventService gatheringEventService) {
        this.gatheringEventService = gatheringEventService;
    }

    @PostMapping("/start")
    public @ResponseBody GatheringEventDto startEvent(@RequestBody GatheringEventDto event) {
        event.validate();
        return gatheringEventService.startEvent(event.asGatheringEvent());
    }

    @PutMapping("/close")
    public @ResponseBody GatheringEventDto closeEvent(@RequestBody GatheringEventDto eventDto) {
        return gatheringEventService.closeEvent(eventDto);
    }
}
