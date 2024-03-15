package org.example.entity;


import com.datastax.oss.driver.api.mapper.annotations.CqlName;
import com.datastax.oss.driver.api.mapper.annotations.Entity;
import com.datastax.oss.driver.api.mapper.annotations.PartitionKey;
import org.example.names.RentIds;

import java.time.LocalDate;
import java.util.Objects;

@Entity(defaultKeyspace = "rent_a_court")
@CqlName("rents")
public class Rent {

    @PartitionKey
    @CqlName(RentIds.ID)
    private int id;
    @CqlName(RentIds.COURT_ID)
    private int courtId;
    @CqlName(RentIds.CLIENT_ID)
    private int clientId;
    @CqlName(RentIds.START_TIME)
    private LocalDate startTime;
    @CqlName(RentIds.END_TIME)
    private LocalDate endTime;

    public Rent(int id, int courtId, int clientId, LocalDate startTime, LocalDate endTime) {
        this.id = id;
        this.courtId = courtId;
        this.clientId = clientId;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Rent() {
    }

    public int getId() {
        return id;
    }

    public int getCourtId() {
        return courtId;
    }

    public int getClientId() {
        return clientId;
    }

    public LocalDate getStartTime() {
        return startTime;
    }

    public LocalDate getEndTime() {
        return endTime;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCourtId(int courtId) {
        this.courtId = courtId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public void setStartTime(LocalDate startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(LocalDate endTime) {
        this.endTime = endTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rent rent = (Rent) o;
        return id == rent.id && courtId == rent.courtId && clientId == rent.clientId && Objects.equals(startTime, rent.startTime) && Objects.equals(endTime, rent.endTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, courtId, clientId, startTime, endTime);
    }

    @Override
    public String toString() {
        return "Rent{" +
                "id=" + id +
                ", courtId=" + courtId +
                ", clientId=" + clientId +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
