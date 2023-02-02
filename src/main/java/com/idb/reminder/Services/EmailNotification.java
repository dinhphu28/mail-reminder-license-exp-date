package com.idb.reminder.Services;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.idb.reminder.Entities.Event;
import com.idb.reminder.Entities.EventParticipant;
import com.idb.reminder.Utils.Mail.MailSenderUtil;

@Service
public class EmailNotification {
    @Autowired
    private EventService eventService;

    @Autowired
    private EventParticipantService eventParticipantService;

    @Autowired
    private MailSenderUtil mailSenderUtil;

    @Value("${idb.properties.date-range}")
    private Integer dateRange;

    public void sendNotificationMailToAll() {
        List<Event> events = eventService.retrieveAll();

        LocalDateTime now = LocalDateTime.now();

        for (Event event : events) {

            if((now.isEqual(event.getDateExact().minus(dateRange, ChronoUnit.DAYS)) || now.isAfter(event.getDateExact().minus(dateRange, ChronoUnit.DAYS)))) {
                List<EventParticipant> eventParticipants = eventParticipantService.retrieveAllByIdEvent(event.getId());

                // Event event = eventService.retrieveById(id);

                List<String> receiversLs = new ArrayList<String>();
                List<String> ccReceiverLs = new ArrayList<String>();

                for (EventParticipant eventParticipant : eventParticipants) {
                    receiversLs.add(eventParticipant.getEmail());
                }

                String[] receivers = receiversLs.toArray(new String[0]);
                String[] ccReceivers = ccReceiverLs.toArray(new String[0]);

                String mailContent = event.getContent() + "\n" + "<h3 style='color:red;'> Expired day: " + event.getDateExact() + "</h3>";

                try {
                    mailSenderUtil.sendHtmlEmail(event.getTitle(), mailContent, receivers, ccReceivers);
                } catch (Exception e) {
                    // TODO: handle exception

                }
            }
        }
    }
}
 