package com.govtech.project.repository;

import com.govtech.project.entity.GatheringEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GatheringEventRepo extends JpaRepository<GatheringEvent, Long> {
}
