package org.example.entityMgd;

import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonProperty;

public class ClientMgd extends AbstractEntityMgd {

    @BsonProperty("first_name")
    private String firstName;
    @BsonProperty("last_name")
    private String lastName;
    @BsonProperty("personal_ID")
    private String personalID;
    @BsonProperty("has_rent")
    private int hasRent;

    @BsonCreator
    public ClientMgd(
            @BsonProperty("_id") int id,
            @BsonProperty("first_name") String firstName,
            @BsonProperty("last_name") String lastName,
            @BsonProperty("personal_ID") String personalID,
            @BsonProperty("has_rent") int hasRent)
    {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.personalID = personalID;
        this.hasRent = hasRent;
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
}