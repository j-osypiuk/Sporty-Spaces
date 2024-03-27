package org.example.entityKafka;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class RentKfk {

    @JsonProperty("id")
    private int id;
    @JsonProperty("rental_name")
    private String rentalName;
    @JsonProperty("court_id")
    private int courtId;
    @JsonProperty("client_id")
    private int clientId;
    @JsonProperty("start_time")
    private Date startTime;
    @JsonProperty("end_time")
    private Date endTime;

    @JsonCreator
    public RentKfk(@JsonProperty("id") int id,
                    @JsonProperty("rental_name") String rentalName,
                    @JsonProperty("court_id") int courtId,
                    @JsonProperty("client_id") int clientId,
                    @JsonProperty("start_time") Date startTime,
                    @JsonProperty("end_time") Date endTime) {
        this.id = id;
        this.rentalName = rentalName;
        this.courtId = courtId;
        this.clientId = clientId;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCourtId() {
        return courtId;
    }

    public void setCourtId(int courtId) {
        this.courtId = courtId;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getRentalName() {
        return rentalName;
    }

    public void setRentalName(String rentalName) {
        this.rentalName = rentalName;
    }

    @Override
    public String toString() {
        return "RentKfk{" +
                "id=" + id +
                ", rentalName='" + rentalName + '\'' +
                ", courtId=" + courtId +
                ", clientId=" + clientId +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
