package com.idb.reminder.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.idb.reminder.Entities.EventParticipant;

@Repository
public interface EventParticipantRepo extends JpaRepository<EventParticipant, String> {
    List<EventParticipant> findByIdEvent(String idEvent);

    Long deleteByIdEvent(String idEvent);
}
