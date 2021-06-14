package com.example.cdr.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "records")
public class Record {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Type(type = "pg-uuid")
    @Column(name = "uuid", columnDefinition = "uuid")
    private UUID uuid;
    @Column(name = "domain_name")
    private String domainName;
    @Column(name = "caller_name")
    private String callerName;
    @Column(name = "caller_number")
    private String callerNumber;
    @Column(name = "destination_number")
    private String destinationNumber;
    private String direction;
    @Column(name = "call_start")
    private String callStart;
    @Column(name = "ring_start")
    private String ringStart;
    @Column(name = "answer_start")
    private String answerStart;
    @Column(name = "call_end")
    private String callEnd;
    private Integer duration;
    @Type(type = "pg-uuid")
    @Column(name = "recording", columnDefinition = "uuid")
    private UUID recording;
    @Column(name = "click_to_call")
    private Boolean clickToCall;
    @Column(name = "click_to_call_data")
    private String clickToCallData;
    private String action;

    public Record() {
    }

    public Record(Long id, UUID uuid, String domainName, String callerName, String callerNumber,
                  String destinationNumber, String direction, String callStart, String ringStart,
                  String answerStart, String callEnd, Integer duration, UUID recording, Boolean clickToCall,
                  String clickToCallData, String action) {
        this.id = id;
        this.uuid = uuid;
        this.domainName = domainName;
        this.callerName = callerName;
        this.callerNumber = callerNumber;
        this.destinationNumber = destinationNumber;
        this.direction = direction;
        this.callStart = callStart;
        this.ringStart = ringStart;
        this.answerStart = answerStart;
        this.callEnd = callEnd;
        this.duration = duration;
        this.recording = recording;
        this.clickToCall = clickToCall;
        this.clickToCallData = clickToCallData;
        this.action = action;
    }

    public Long getId() {
        return id;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public String getCallerName() {
        return callerName;
    }

    public void setCallerName(String callerName) {
        this.callerName = callerName;
    }

    public String getCallerNumber() {
        return callerNumber;
    }

    public void setCallerNumber(String callerNumber) {
        this.callerNumber = callerNumber;
    }

    public String getDestinationNumber() {
        return destinationNumber;
    }

    public void setDestinationNumber(String destinationNumber) {
        this.destinationNumber = destinationNumber;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getCallStart() {
        return callStart;
    }

    public void setCallStart(String callStart) {
        this.callStart = callStart;
    }

    public String getRingStart() {
        return ringStart;
    }

    public void setRingStart(String ringStart) {
        this.ringStart = ringStart;
    }

    public String getAnswerStart() {
        return answerStart;
    }

    public void setAnswerStart(String answerStart) {
        this.answerStart = answerStart;
    }

    public String getCallEnd() {
        return callEnd;
    }

    public void setCallEnd(String callEnd) {
        this.callEnd = callEnd;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public UUID getRecording() {
        return recording;
    }

    public void setRecording(UUID recording) {
        this.recording = recording;
    }

    public Boolean isClickToCall() {
        return clickToCall;
    }

    public void setClickToCall(Boolean clickToCall) {
        this.clickToCall = clickToCall;
    }

    public String getClickToCallData() {
        return clickToCallData;
    }

    public void setClickToCallData(String clickToCallData) {
        this.clickToCallData = clickToCallData;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Record record = (Record) o;
        return duration == record.duration
                && clickToCall == record.clickToCall
                && id.equals(record.id)
                && uuid.equals(record.uuid)
                && domainName.equals(record.domainName)
                && Objects.equals(callerName, record.callerName)
                && callerNumber.equals(record.callerNumber)
                && destinationNumber.equals(record.destinationNumber)
                && direction.equals(record.direction)
                && callStart.equals(record.callStart)
                && ringStart.equals(record.ringStart)
                && answerStart.equals(record.answerStart)
                && callEnd.equals(record.callEnd)
                && Objects.equals(recording, record.recording)
                && Objects.equals(clickToCallData, record.clickToCallData)
                && Objects.equals(action, record.action);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, uuid, domainName, callerName, callerNumber,
                destinationNumber, direction, callStart, ringStart, answerStart,
                callEnd, duration, recording, clickToCall, clickToCallData, action);
    }

    @Override
    public String toString() {
        return "Record{" +
                "id=" + id +
                ", uuid=" + uuid +
                ", domain_name='" + domainName + '\'' +
                ", caller_name='" + callerName + '\'' +
                ", caller_number='" + callerNumber + '\'' +
                ", destination_number='" + destinationNumber + '\'' +
                ", direction='" + direction + '\'' +
                ", call_start='" + callStart + '\'' +
                ", ring_start='" + ringStart + '\'' +
                ", answer_start='" + answerStart + '\'' +
                ", call_end='" + callEnd + '\'' +
                ", duration=" + duration +
                ", recording=" + recording +
                ", click_to_call=" + clickToCall +
                ", click_to_call_data='" + clickToCallData + '\'' +
                ", action='" + action + '\'' +
                '}';
    }

    public String toJsonString() {
        return "{" +
                "\"id\":" + id + "," +
                "\"uuid\":\"" + uuid + "\"," +
                "\"domainName\":\"" + domainName + "\"," +
                "\"callerName\":\"" + callerName + "\"," +
                "\"callerNumber\":\"" + callerNumber + "\"," +
                "\"destinationNumber\":\"" + destinationNumber + "\"," +
                "\"direction\":\"" + direction + "\"," +
                "\"callStart\":\"" + callStart + "\"," +
                "\"ringStart\":\"" + ringStart + "\"," +
                "\"answerStart\":\"" + answerStart + "\"," +
                "\"callEnd\":\"" + callEnd + "\"," +
                "\"duration\":" + duration + "," +
                "\"recording\":\"" + recording + "\"," +
                "\"clickToCall\":" + clickToCall + "," +
                "\"clickToCallData\":\"" + clickToCallData + "\"," +
                "\"action\":\"" + action + "\"" +
                "}";
    }
}
