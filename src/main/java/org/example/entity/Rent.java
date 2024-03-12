package org.example.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Rent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn
    private Court court;
    @ManyToOne
    @JoinColumn
    private Client client;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public Rent(Court court, Client client, LocalDateTime startTime) {
        this.court = court;
        this.client = client;
        this.startTime = startTime;
    }

    public Rent() {
    }

    public int getId() {
        return id;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Court getCourt() {
        return court;
    }

    public Client getClient() {
        return client;
    }
}
