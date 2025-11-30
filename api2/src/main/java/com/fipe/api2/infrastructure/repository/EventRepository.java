package com.fipe.api2.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fipe.api2.domain.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    
}
