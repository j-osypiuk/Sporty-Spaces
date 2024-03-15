package org.example.entity;

import com.datastax.oss.driver.api.mapper.annotations.CqlName;
import com.datastax.oss.driver.api.mapper.annotations.Entity;
import com.datastax.oss.driver.api.mapper.annotations.PartitionKey;
import org.example.names.ClientIds;

import java.util.Objects;


@Entity(defaultKeyspace = "rent_a_court")
@CqlName("clients")
public class Client {

    @PartitionKey
    @CqlName(ClientIds.ID)
    private int id;
    @CqlName(ClientIds.HAS_RENT)
    private boolean hasRent;
    @CqlName(ClientIds.FIRST_NAME)
    private String firstName;
    @CqlName(ClientIds.LAST_NAME)
    private String lastName;
    @CqlName(ClientIds.PERSONAL_ID)
    private String personalID;

    public Client(int id, boolean hasRent, String firstName, String lastName, String personalID) {
        this.id = id;
        this.hasRent = hasRent;
        this.firstName = firstName;
        this.lastName = lastName;
        this.personalID = personalID;
    }

    public Client() {
    }

    public int getId() {
        return id;
    }

    public boolean isHasRent() {
        return hasRent;
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

    public void setId(int id) {
        this.id = id;
    }

    public void setHasRent(boolean hasRent) {
        this.hasRent = hasRent;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPersonalID(String personalID) {
        this.personalID = personalID;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", hasRent=" + hasRent +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", personalID='" + personalID + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return id == client.id && hasRent == client.hasRent && Objects.equals(firstName, client.firstName) && Objects.equals(lastName, client.lastName) && Objects.equals(personalID, client.personalID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, hasRent, firstName, lastName, personalID);
    }
}
