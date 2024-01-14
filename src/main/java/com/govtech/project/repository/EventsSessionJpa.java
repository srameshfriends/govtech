package com.govtech.project.repository;

import com.govtech.project.entity.EventsSession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventsSessionJpa extends JpaRepository<EventsSession, Long> {
}