package org.example.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String lastName;
    private String personalID;
    @Version
    private int version;
    private boolean hasRent;
    @OneToMany(mappedBy = "client")
    private List<Rent> rents;

    public Client(String firstName, String lastName, String personalID) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.personalID = personalID;
    }

    public Client() {
    }

    public boolean isHasRent() {
        return hasRent;
    }
    public void setHasRent(boolean hasRent) {
        this.hasRent = hasRent;
    }
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPersonalID() {
        return personalID;
    }

    public int getId() {
        return id;
    }
}
