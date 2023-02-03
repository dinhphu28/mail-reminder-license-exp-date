package com.idb.reminder.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.idb.reminder.Models.Telegram.Message.MessageToSendModel;
import com.idb.reminder.Utils.Telegram.TelegramMessageUtil;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/tests")
public class TestController {
    @Autowired
    private TelegramMessageUtil telegramMessageUtil;

    @GetMapping(
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> sendTelegramMessage() {
        ResponseEntity<Object> entity;

        MessageToSendModel messageToSendModel = new MessageToSendModel("Hello Professor X", "HTML", false, false, null, "-619128183");

        int kk = telegramMessageUtil.sendMessage(messageToSendModel);

        entity = new ResponseEntity<>("{\"Notice\": " + kk + "}", HttpStatus.OK);

        return entity;
    }
}
