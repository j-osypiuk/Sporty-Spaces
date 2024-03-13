package org.example.entity;

import java.util.List;

public class Client{

    private int id;
    private String firstName;
    private String lastName;
    private String personalID;
    private int hasRent;
    private List<Rent> rents;

    public Client(int id,
                  String firstName,
                  String lastName,
                  String personalID) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.personalID = personalID;
    }

    public Client(int id,
                  String firstName,
                  String lastName,
                  String personalID,
                  int hasRent) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.personalID = personalID;
        this.hasRent = hasRent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPersonalID() {
        return personalID;
    }

    public void setPersonalID(String personalID) {
        this.personalID = personalID;
    }

    public int getHasRent() {
        return hasRent;
    }

    public void setHasRent(int hasRent) {
        this.hasRent = hasRent;
    }

    public List<Rent> getRents() {
        return rents;
    }

    public void setRents(List<Rent> rents) {
        this.rents = rents;
    }
}
