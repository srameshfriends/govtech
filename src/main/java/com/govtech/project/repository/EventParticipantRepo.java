package com.govtech.project.repository;

import com.govtech.project.entity.EventParticipant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventParticipantRepo extends JpaRepository<EventParticipant, Long> {
}
