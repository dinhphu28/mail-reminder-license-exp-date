package com.idb.reminder.Services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.idb.reminder.Entities.Event;
import com.idb.reminder.Entities.EventParticipant;
import com.idb.reminder.Utils.ListIntegerUtil;
import com.idb.reminder.Utils.Mail.MailSenderUtil;

import com.idb.reminder.Models.Telegram.Message.MessageToSendModel;
import com.idb.reminder.Utils.Telegram.TelegramMessageUtil;

@Service
public class NotificationSender {
    @Autowired
    private EventService eventService;

    @Autowired
    private EventParticipantService eventParticipantService;

    @Autowired
    private MailSenderUtil mailSenderUtil;

    @Value("${idb.properties.date-range}")
    private Integer dateRange;

    @Value("${idb.properties.warning-date-range}")
    private Integer warningDateRange;

    @Autowired
    private TelegramMessageUtil telegramMessageUtil;

    @Value("${idb.telegram.group.id}")
    private String groupId;

    public void sendNotificationMailToAll() {
        List<Event> events = eventService.retrieveAll();

        // LocalDateTime now = LocalDateTime.now();
        LocalDateTime now = LocalDate.now().atStartOfDay();

        ListIntegerUtil listIntegerUtil = new ListIntegerUtil();

        for (Event event : events) {

            if((now.isEqual(event.getDateExact().minus(dateRange, ChronoUnit.DAYS)) || now.isAfter(event.getDateExact().minus(dateRange, ChronoUnit.DAYS)))) {
                // List<EventParticipant> eventParticipants = eventParticipantService.retrieveAllByIdEvent(event.getId());

                // // Event event = eventService.retrieveById(id);

                // List<String> receiversLs = new ArrayList<String>();
                // List<String> ccReceiverLs = new ArrayList<String>();

                // for (EventParticipant eventParticipant : eventParticipants) {
                //     receiversLs.add(eventParticipant.getEmail());
                // }

                // String[] receivers = receiversLs.toArray(new String[0]);
                // String[] ccReceivers = ccReceiverLs.toArray(new String[0]);

                // String mailContent = event.getContent() + "\n" + "<h3 style='color:red;'> Expired date: " + event.getDateExact() + "</h3>";

                // try {
                //     if(eventParticipants.size() > 0) {
                //         mailSenderUtil.sendHtmlEmail(event.getTitle(), mailContent, receivers, ccReceivers);
                //     }
                // } catch (Exception e) {
                //     // TODO: handle exception
                //     e.printStackTrace();
                // }

                // New conditions

                if((now.isEqual(event.getDateExact().minus(warningDateRange, ChronoUnit.DAYS)) || now.isAfter(event.getDateExact().minus(warningDateRange, ChronoUnit.DAYS)))) {
                    // send notifcation - code here
                    List<EventParticipant> eventParticipants = eventParticipantService.retrieveAllByIdEvent(event.getId());

                    // Event event = eventService.retrieveById(id);

                    List<String> receiversLs = new ArrayList<String>();
                    List<String> ccReceiverLs = new ArrayList<String>();

                    for (EventParticipant eventParticipant : eventParticipants) {
                        receiversLs.add(eventParticipant.getEmail());
                    }

                    String[] receivers = receiversLs.toArray(new String[0]);
                    String[] ccReceivers = ccReceiverLs.toArray(new String[0]);

                    String mailContent = event.getContent() + "\n" + "<h3 style='color:red;'> Expired date: " + event.getDateExact() + "</h3>";

                    try {
                        if(eventParticipants.size() > 0) {
                            mailSenderUtil.sendHtmlEmail(event.getTitle(), mailContent, receivers, ccReceivers);
                        }
                    } catch (Exception e) {
                        // TODO: handle exception
                        e.printStackTrace();
                    }
                } else {
                    List<Integer> datesSendingNotices = listIntegerUtil.stringToListInteger(event.getDatesSendingNotice());

                    for (Integer subtractDateVal : datesSendingNotices) {
                        if(now.isEqual(event.getDateExact().minus(subtractDateVal, ChronoUnit.DAYS))) {
                            // send notifcation - code here
                            List<EventParticipant> eventParticipants = eventParticipantService.retrieveAllByIdEvent(event.getId());

                            // Event event = eventService.retrieveById(id);

                            List<String> receiversLs = new ArrayList<String>();
                            List<String> ccReceiverLs = new ArrayList<String>();

                            for (EventParticipant eventParticipant : eventParticipants) {
                                receiversLs.add(eventParticipant.getEmail());
                            }

                            String[] receivers = receiversLs.toArray(new String[0]);
                            String[] ccReceivers = ccReceiverLs.toArray(new String[0]);

                            String mailContent = event.getContent() + "\n" + "<h3 style='color:red;'> Expired date: " + event.getDateExact() + "</h3>";

                            try {
                                if(eventParticipants.size() > 0) {
                                    mailSenderUtil.sendHtmlEmail(event.getTitle(), mailContent, receivers, ccReceivers);
                                }
                            } catch (Exception e) {
                                // TODO: handle exception
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
    }

    public void sendNotificationTelegramToGroup() {
        List<Event> events = eventService.retrieveAll();

        // LocalDateTime now = LocalDateTime.now();
        LocalDateTime now = LocalDate.now().atStartOfDay();

        ListIntegerUtil listIntegerUtil = new ListIntegerUtil();

        for (Event event : events) {

            if((now.isEqual(event.getDateExact().minus(dateRange, ChronoUnit.DAYS)) || now.isAfter(event.getDateExact().minus(dateRange, ChronoUnit.DAYS)))) {
                
                // String messageContent = "<strong><i>" + event.getTitle() + "</i></strong>: " + "\n" + event.getContent() + "\n" + "<i>Expired date: " + event.getDateExact() + "</i>";

                // MessageToSendModel messageToSendModel = new MessageToSendModel(messageContent, "HTML", false, false, null, "-619128183");

                // int kk = telegramMessageUtil.sendMessage(messageToSendModel);

                if((now.isEqual(event.getDateExact().minus(warningDateRange, ChronoUnit.DAYS)) || now.isAfter(event.getDateExact().minus(warningDateRange, ChronoUnit.DAYS)))) {
                    // send notifcation - code here
                    List<EventParticipant> eventParticipants = eventParticipantService.retrieveAllByIdEvent(event.getId());

                    // Event event = eventService.retrieveById(id);

                    String messageContent = "<strong><i>" + event.getTitle() + "</i></strong>: " + "\n" + event.getContent() + "\n" + "<i>Expired date: " + event.getDateExact() + "</i>";

                    MessageToSendModel messageToSendModel = new MessageToSendModel(messageContent, "HTML", false, false, null, groupId);

                    int kk = telegramMessageUtil.sendMessage(messageToSendModel);
                } else {
                    List<Integer> datesSendingNotices = listIntegerUtil.stringToListInteger(event.getDatesSendingNotice());

                    for (Integer subtractDateVal : datesSendingNotices) {
                        if(now.isEqual(event.getDateExact().minus(subtractDateVal, ChronoUnit.DAYS))) {
                            // send notifcation - code here
                            String messageContent = "<strong><i>" + event.getTitle() + "</i></strong>: " + "\n" + event.getContent() + "\n" + "<i>Expired date: " + event.getDateExact() + "</i>";

                            MessageToSendModel messageToSendModel = new MessageToSendModel(messageContent, "HTML", false, false, null, groupId);

                            int kk = telegramMessageUtil.sendMessage(messageToSendModel);
                        }
                    }
                }
            }
        }
    }
}
 