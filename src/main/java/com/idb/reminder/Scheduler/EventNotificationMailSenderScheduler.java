package com.idb.reminder.Scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.idb.reminder.Services.NotificationSender;

@Component
public class EventNotificationMailSenderScheduler {
    @Autowired
    private NotificationSender emailNotification;

    @Scheduled(cron = "${idb.properties.schedule.cron-expression}")
    public void sendEmailNotification(){
        emailNotification.sendNotificationMailToAll();
    }

    @Scheduled(cron = "${idb.properties.schedule.cron-expression}")
    public void sendTelegramMessageNotification(){
        emailNotification.sendNotificationTelegramToGroup();;
    }
}
