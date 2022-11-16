package com.idb.reminder.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idb.reminder.Entities.Event;
import com.idb.reminder.Repositories.EventRepo;

@Service
public class EventService {
    @Autowired
    private EventRepo repo;

    public List<Event> retrieveAll() {
        return repo.findAll();
    }

    public Event retrieveById(String id) {
        Event sth = null;

        try {
            sth = repo.findById(id).get();
        } catch (Exception e) {
            // TODO: handle exception
        }

        return sth;
    }

    public Event createOne(Event event) {
        event.setId(null);

        Event tmp = null;

        try {
            tmp = repo.save(event);
        } catch (Exception e) {
            // TODO: handle exception
        }

        return tmp;
    }

    public Event updateOne(Event event) {
        Event tmp = null;

        try {
            repo.findById(event.getId());

            tmp = repo.save(event);
        } catch (Exception e) {
            // TODO: handle exception
        }

        return tmp;
    }

    public Boolean deleteOne(String id) {
        Boolean isSuccess = false;

        try {
            repo.deleteById(id);

            isSuccess = true;
        } catch (Exception e) {
            // TODO: handle exception
        }

        return isSuccess;
    }
}
