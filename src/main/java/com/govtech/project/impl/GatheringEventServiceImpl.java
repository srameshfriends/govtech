package com.govtech.project.impl;

import com.govtech.project.dto.DtoUtils;
import com.govtech.project.dto.GatheringEventDto;
import com.govtech.project.entity.GatheringEvent;
import com.govtech.project.entity.UserInfo;
import com.govtech.project.repository.GatheringEventRepo;
import com.govtech.project.repository.GatheringSessionRepo;
import com.govtech.project.repository.UserInfoRepo;
import com.govtech.project.service.GatheringEventService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

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
        return DtoUtils.asGatheringEventDto(initiator, gatheringEvent);
    }

    public GatheringEventDto closeEvent(GatheringEventDto eventDto) {
        System.out.println("---------- END GATHERING EVENT -----------------");
        eventDto.setUserId(3874L);
        eventDto.setEventId(234L);
        return eventDto;
    }

    @Override
    public List<GatheringEventDto> search(PageRequest pageable) {
        return asGatheringEventDto(gatheringEventRepo.findAll());
    }

    private List<GatheringEventDto> asGatheringEventDto(List<GatheringEvent> eventPage) {
        List<GatheringEventDto> dtoList = new ArrayList<>();
        eventPage.forEach(new Consumer<GatheringEvent>() {
            @Override
            public void accept(GatheringEvent gathering) {
                gathering = gatheringEventRepo.getReferenceById(gathering.getId());
                System.out.println(" --------- +++ --------- ");
                System.out.println("getParticipants : " + gathering.getParticipants());
                System.out.println("getInitiator : " + gathering.getInitiator());
                System.out.println("getRev : " + gathering.getRev());
                System.out.println(" --- +++ ---");
                GatheringEventDto dto = DtoUtils.asGatheringEventDto(gathering.getInitiator(), gathering);
                dtoList.add(dto);

                System.out.println("getUserRev : " + dto.getUserRev());
                System.out.println("getActive : " + dto.getActive());
                System.out.println("getEventDate : " + dto.getEventDate());
                System.out.println("getStartTime : " + dto.getStartTime());
                System.out.println("getEndTime : " + dto.getEndTime());

                System.out.println("getEventId : " + dto.getEventId());
                System.out.println("getEventRev : " + dto.getEventRev());
                System.out.println("getRestaurantRev : " + dto.getRestaurantRev());
                System.out.println("getRestaurantId : " + dto.getRestaurantId());
                System.out.println("getRestaurant : " + dto.getRestaurant());
            }
        });
        return dtoList;
    }
}
