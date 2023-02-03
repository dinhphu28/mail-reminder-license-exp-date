package com.idb.reminder.Models.Telegram.Message;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MessageToSendModel {
    
    @JsonProperty("text")
    private String content;

    @JsonProperty("parse_mode")
    private String parseMode;

    @JsonProperty("disable_web_page_preview")
    private Boolean disableWebPagePreview;

    @JsonProperty("disable_notification")
    private Boolean disableNotification;

    @JsonProperty("reply_to_message_id")
    private String replyToMessageId;

    @JsonProperty("chat_id")
    private String chatId;

    // // @JsonProperty("text")
    // private String text;

    // // @JsonProperty("parse_mode")
    // private String parse_mode;

    // // @JsonProperty("disable_web_page_preview")
    // private Boolean disable_web_page_preview;

    // // @JsonProperty("disable_notification")
    // private Boolean disable_notification;

    // // @JsonProperty("reply_to_message_id")
    // private String reply_to_message_id;

    // // @JsonProperty("chat_id")
    // private String chat_id;
}
