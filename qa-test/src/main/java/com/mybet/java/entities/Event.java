package com.mybet.java.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "EVENTS")
public class Event {

    @Column(name = "ID")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "eventSeq")
    @SequenceGenerator(name = "eventSeq", sequenceName = "event_id_seq", allocationSize = 1)
    private Integer id;

    @Column(name = "HOME_PARTICIPANT_ID")
    private int homeParticipantId;

    @Column(name = "GUEST_PARTICIPANT_ID")
    private int guestParticipantId;

    @Column(name = "EVENT_DATE")
    private Timestamp eventDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getHomeParticipantId() {
        return homeParticipantId;
    }

    public void setHomeParticipantId(int homeParticipantId) {
        this.homeParticipantId = homeParticipantId;
    }

    public int getGuestParticipantId() {
        return guestParticipantId;
    }

    public void setGuestParticipantId(int guestParticipantId) {
        this.guestParticipantId = guestParticipantId;
    }

    public Timestamp getEventDate() {
        return eventDate;
    }

    public void setEventDate(Timestamp eventDate) {
        this.eventDate = eventDate;
    }
}
