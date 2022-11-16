package com.idb.reminder.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.idb.reminder.Entities.Event;

@Repository
public interface EventRepo extends JpaRepository<Event, String> {
    
}
