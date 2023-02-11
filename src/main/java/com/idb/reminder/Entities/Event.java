package com.idb.reminder.Entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "app_fd_su_ex_event")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Event {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id")
    private String id;

    @Column(name = "datecreated")
    private LocalDateTime dateCreated;

    @Column(name = "datemodified")
    private LocalDateTime dateModified;

    @Column(name = "createdby")
    private String createdBy;

    @Column(name = "createdbyname")
    private String createdByName;

    @Column(name = "modifiedby")
    private String modifiedBy;

    @Column(name = "modifiedbyname")
    private String modifiedByName;

    @Column(name = "c_title")
    private String title;

    @Column(name = "c_content")
    private String content;

    @Column(name = "c_dateexact")
    private LocalDateTime dateExact;

    @Column(name = "c_datebef")
    private LocalDateTime dateBef;

    @Column(name = "c_dates_sending_notice")
    private String datesSendingNotice;
}
