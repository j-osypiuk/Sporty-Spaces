package org.example.entity;

import com.datastax.oss.driver.api.mapper.annotations.CqlName;
import com.datastax.oss.driver.api.mapper.annotations.Entity;
import com.datastax.oss.driver.api.mapper.annotations.PartitionKey;
import com.datastax.oss.driver.api.mapper.annotations.PropertyStrategy;
import org.example.names.CourtIds;

import java.util.Objects;

@Entity(defaultKeyspace = "rent_a_court")
@CqlName("courts")
@PropertyStrategy(mutable = false)
public class Court {

    @PartitionKey
    @CqlName(CourtIds.ID)
    private int id;
    @CqlName(CourtIds.WIDTH)
    private double width;
    @CqlName(CourtIds.LENGTH)
    private double length;
    @CqlName(CourtIds.IS_RENTED)
    private boolean isRented;
    @CqlName(CourtIds.DISCRIMINATOR)
    private String discriminator;

    public Court(int id, double width, double length, boolean isRented, String discriminator) {
        this.id = id;
        this.width = width;
        this.length = length;
        this.isRented = isRented;
        this.discriminator = discriminator;
    }

    public Court() {
    }

    public int getId() {
        return id;
    }

    public double getWidth() {
        return width;
    }

    public double getLength() {
        return length;
    }

    public boolean isRented() {
        return isRented;
    }

    public String getDiscriminator() {
        return discriminator;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public void setRented(boolean rented) {
        isRented = rented;
    }

    public void setDiscriminator(String discriminator) {
        this.discriminator = discriminator;
    }

    @Override
    public String toString() {
        return "Court{" +
                "id=" + id +
                ", width=" + width +
                ", length=" + length +
                ", isRented=" + isRented +
                ", discriminator='" + discriminator + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Court court = (Court) o;
        return id == court.id && Double.compare(width, court.width) == 0 && Double.compare(length, court.length) == 0 && isRented == court.isRented && Objects.equals(discriminator, court.discriminator);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, width, length, isRented, discriminator);
    }
}
