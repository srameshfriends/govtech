package com.govtech.project.controls;

import com.govtech.project.dto.DtoUtils;
import com.govtech.project.dto.GatheringEventDto;
import com.govtech.project.service.GatheringEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class GatheringEventController {

    private final GatheringEventService gatheringEventService;

    @Autowired
    public GatheringEventController(GatheringEventService gatheringEventService) {
        this.gatheringEventService = gatheringEventService;
    }

    @PostMapping("/start")
    public @ResponseBody GatheringEventDto startEvent(@RequestBody GatheringEventDto dto) {
        dto.validate();
        return gatheringEventService.startEvent(DtoUtils.asGatheringEvent(dto));
    }

    @PutMapping("/close")
    public @ResponseBody GatheringEventDto closeEvent(@RequestBody GatheringEventDto eventDto) {
        return gatheringEventService.closeEvent(eventDto);
    }

    @GetMapping("/search")
    public @ResponseBody List<GatheringEventDto> search() {
        PageRequest request = PageRequest.of(1, 1, Sort.Direction.ASC, "eventDate");
        return gatheringEventService.search(request);
    }
}
