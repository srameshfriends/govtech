package com.govtech.project.h2;

import com.govtech.project.entity.GatheringEvent;
import com.govtech.project.entity.UserInfo;
import com.govtech.project.repository.GatheringSessionRepo;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;

@Repository
public class GatheringSessionRepoImpl extends H2AbstractRepoImpl implements GatheringSessionRepo {
    private final H2Queries h2Queries;
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public GatheringSessionRepoImpl(H2Queries h2Queries) {
        this.h2Queries = h2Queries;
    }

    @Override
    public UserInfo findByGivenName(String givenName) {
        Query query = entityManager.createNativeQuery(h2Queries.getQuery("userByGivenName"));
        query.setParameter("givenName", givenName);
        return getSingleResult(query, UserInfo.class, h2Queries.getFields("userByGivenName"));
    }

    @Override
    public GatheringEvent findGatheringEvent(UserInfo initiator, LocalDate eventDate, LocalTime startTime) {
        Query query = entityManager.createNativeQuery(h2Queries.getQuery("gatheringByInitiator"));
        query.setParameter("initiator", initiator.getId());
        query.setParameter("eventDate", eventDate);
        query.setParameter("startTime", startTime);
        return getSingleResult(query, GatheringEvent.class, h2Queries.getFields("gatheringByInitiator"));
    }
}
