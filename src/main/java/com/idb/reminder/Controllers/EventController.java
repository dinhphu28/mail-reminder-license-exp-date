package com.idb.reminder.Controllers;

import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.idb.reminder.Entities.Event;
import com.idb.reminder.Entities.EventParticipant;
import com.idb.reminder.Services.NotificationSender;
import com.idb.reminder.Services.EventParticipantService;
import com.idb.reminder.Services.EventService;
import com.idb.reminder.Utils.ListIntegerUtil;
import com.idb.reminder.Utils.Mail.MailSenderUtil;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/events")
public class EventController {
    @Autowired
    private EventService eventService;

    @Autowired
    private EventParticipantService eventParticipantService;

    @Autowired
    private MailSenderUtil mailSenderUtil;

    @Autowired
    private NotificationSender emailNotification;

    @GetMapping(
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> retrieveAll() {
        ResponseEntity<Object> entity;

        String resTmp = "";

        List<Event> events = eventService.retrieveAll();

        for (Event event : events) {
            if(event.getId().equals("411141d3-a34d-44c7-be80-2a5db50f9463")) {
                resTmp = event.getDatesSendingNotice();
                break;
            }
        }

        ListIntegerUtil listIntegerUtil = new ListIntegerUtil();

        List<Integer> resIntegers = listIntegerUtil.stringToListInteger(resTmp);

        // entity = new ResponseEntity<>(resIntegers, HttpStatus.OK);
        entity = new ResponseEntity<>(events, HttpStatus.OK);

        return entity;
    }

    @GetMapping(
        value = "/{id}/participants",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> retrieveAllEventParticipantsByEventId(@PathVariable("id") String id) throws MessagingException {
        ResponseEntity<Object> entity;

        List<EventParticipant> eventParticipants = eventParticipantService.retrieveAllByIdEvent(id);

        Event event = eventService.retrieveById(id);

        List<String> receiversLs = new ArrayList<String>();
        List<String> ccReceiverLs = new ArrayList<String>();

        for (EventParticipant eventParticipant : eventParticipants) {
            receiversLs.add(eventParticipant.getEmail());
        }

        String[] receivers = receiversLs.toArray(new String[0]);
        String[] ccReceivers = ccReceiverLs.toArray(new String[0]);

        try {
            mailSenderUtil.sendHtmlEmail(event.getTitle(), event.getContent(), receivers, ccReceivers);
        } catch (Exception e) {
            // TODO: handle exception

        }

        // mailSenderUtil.sendHtmlEmail(event.getTitle(), event.getContent(), receivers, ccReceivers);

        entity = new ResponseEntity<>(eventParticipants, HttpStatus.OK);

        return entity;
    }

    @GetMapping(
        value = "/vvv",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> sendNotificationMailToAll() {
        ResponseEntity<Object> entity;

        emailNotification.sendNotificationMailToAll();

        entity = new ResponseEntity<>("{ \"Notice\": \"Success\" }", HttpStatus.OK);

        return entity;
    }
}
