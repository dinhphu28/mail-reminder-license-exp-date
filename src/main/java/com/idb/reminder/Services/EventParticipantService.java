package com.idb.reminder.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idb.reminder.Entities.EventParticipant;
import com.idb.reminder.Repositories.EventParticipantRepo;

@Service
public class EventParticipantService {
    @Autowired
    private EventParticipantRepo repo;

    public List<EventParticipant> retrieveAll() {
        return repo.findAll();
    }

    public List<EventParticipant> retrieveAllByIdEvent(String idEvent) {
        List<EventParticipant> eventParticipants = repo.findByIdEvent(idEvent);

        return eventParticipants;
    }
}