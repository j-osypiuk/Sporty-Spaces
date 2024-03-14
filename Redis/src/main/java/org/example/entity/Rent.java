package org.example.entity;

import java.util.Date;

public class Rent {

    private int id;
    private Court court;
    private Client client;
    private Date startTime;
    private Date endTime;

    public Rent(int id,
                Court court,
                Client client,
                Date startTime,
                Date endTime) {
        this.id = id;
        this.court = court;
        this.client = client;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Court getCourt() {
        return court;
    }

    public void setCourt(Court court) {
        this.court = court;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
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
}
