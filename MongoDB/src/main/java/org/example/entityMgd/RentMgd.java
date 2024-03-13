package org.example.entityMgd;

import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonProperty;

import java.util.Date;

public class RentMgd extends AbstractEntityMgd{

    @BsonProperty("court")
    private CourtMgd court;
    @BsonProperty("client")
    private ClientMgd client;
    @BsonProperty("start_time")
    private Date startTime;
    @BsonProperty("end_time")
    private Date endTime;

    @BsonCreator
    public RentMgd(
            @BsonProperty("_id") int id,
            @BsonProperty("court") CourtMgd court,
            @BsonProperty("client") ClientMgd client,
            @BsonProperty("start_time") Date startTime,
            @BsonProperty("end_time") Date endTime)  {
        super(id);
        this.court = court;
        this.client = client;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public CourtMgd getCourt() {
        return court;
    }

    public void setCourt(CourtMgd court) {
        this.court = court;
    }

    public ClientMgd getClient() {
        return client;
    }

    public void setClient(ClientMgd client) {
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
