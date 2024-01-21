package com.govtech.project.impl;


import com.govtech.project.dto.GatheringEventDto;
import com.govtech.project.entity.GatheringEvent;
import com.govtech.project.entity.UserInfo;
import com.govtech.project.repository.GatheringEventRepo;
import com.govtech.project.repository.GatheringSessionRepo;
import com.govtech.project.repository.UserInfoRepo;
import com.govtech.project.service.GatheringEventService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class GatheringEventServiceImpl extends AbstractServiceImpl implements GatheringEventService {
    private final UserInfoRepo userInfoRepo;
    private final GatheringEventRepo gatheringEventRepo;

    private final GatheringSessionRepo gatheringSessionRepo;

    @Autowired
    public GatheringEventServiceImpl(UserInfoRepo userInfoRepo,  GatheringEventRepo gatheringEventRepo, GatheringSessionRepo gatheringSessionRepo) {
       this.userInfoRepo = userInfoRepo;
       this.gatheringEventRepo = gatheringEventRepo;
       this.gatheringSessionRepo = gatheringSessionRepo;
    }

    /*
    * Validate session initiator
    * In
    * */
    public GatheringEventDto startEvent(GatheringEvent gatheringEvent) {
        UserInfo initiator = gatheringEvent.getInitiator();
        if(initiator == null) {
            throw new NullPointerException("Create event session user should not be empty.");
        }
        UserInfo oldUser = gatheringSessionRepo.findByGivenName(initiator.getGivenName());
        setBaseFields(oldUser, initiator);
        initiator = userInfoRepo.save(initiator);
        gatheringEvent.setInitiator(initiator);
        //
        GatheringEvent oldEvent =  gatheringSessionRepo.findGatheringEvent(
                initiator, gatheringEvent.getEventDate(), gatheringEvent.getStartTime());
        if(oldEvent != null) {
            oldEvent = gatheringEventRepo.getReferenceById(oldEvent.getId());
            if(oldEvent.getActive() != null && !oldEvent.getActive()) {
                throw new RuntimeException("Gathering session has been closed, would not be modified.");
            }
        }
        setBaseFields(oldEvent, gatheringEvent);
        gatheringEvent = gatheringEventRepo.save(gatheringEvent);
        return GatheringEventDto.instance(initiator, gatheringEvent);
    }

    public GatheringEventDto closeEvent(GatheringEventDto eventDto) {
        System.out.println("---------- END GATHERING EVENT -----------------");
        eventDto.setUserId(3874L);
        eventDto.setEventId(234L);
        return eventDto;
    }
}
