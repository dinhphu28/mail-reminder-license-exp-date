package com.idb.reminder.Utils.Telegram;

import java.net.URI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.http.ResponseEntity;

import com.idb.reminder.Models.Telegram.Message.MessageToSendModel;


import reactor.core.publisher.Mono;

@Component
public class TelegramMessageUtil {

    @Value("${idb.telegram.bot.token}")
    private String botToken;

    @Value("${idb.telegram.group.id}")
    private String groupId;

    public int sendMessage(MessageToSendModel message) {

        WebClient webClient = WebClient.create("https://api.telegram.org/bot" + botToken);
        
        Mono<ResponseEntity<Void>> entityMono =  webClient.post()
            .uri("/sendMessage")
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .body(Mono.just(message), MessageToSendModel.class)
            .retrieve()
            .toBodilessEntity();

        HttpStatus statusCode = entityMono.block().getStatusCode();

        return statusCode.value();
    }
}
