package org.example.entity;

import com.datastax.oss.driver.api.mapper.annotations.CqlName;
import com.datastax.oss.driver.api.mapper.annotations.Entity;
import org.example.names.CourtIds;

import java.util.Objects;

@Entity(defaultKeyspace = "rent_a_court")
@CqlName("courts")
public class FootballCourt extends Court {

    @CqlName(CourtIds.GOAL_WIDTH)
    private double goalWidth;
    @CqlName(CourtIds.GOAL_LENGTH)
    private double goalLength;


    public FootballCourt(int id, double goalWidth, double goalLength, double width, double length, boolean rented, String discriminator) {
        super(id, width, length, rented, CourtIds.FOOTBALL);
        this.goalWidth = goalWidth;
        this.goalLength = goalLength;
    }

    public FootballCourt() {
    }

    public double getGoalWidth() {
        return goalWidth;
    }

    public double getGoalLength() {
        return goalLength;
    }

    public void setGoalWidth(double goalWidth) {
        this.goalWidth = goalWidth;
    }

    public void setGoalLength(double goalLength) {
        this.goalLength = goalLength;
    }

    @Override
    public String toString() {
        return "FootballCourt{" +
                "id=" + super.getId() +
                ", width=" + super.getWidth() +
                ", length=" + super.getLength() +
                ", isRented=" + super.isRented() +
                ", discriminator='" + super.getDiscriminator() +
                ", goalWidth=" + goalWidth +
                ", goalLength=" + goalLength +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        FootballCourt that = (FootballCourt) o;
        return Double.compare(goalWidth, that.goalWidth) == 0 && Double.compare(goalLength, that.goalLength) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), goalWidth, goalLength);
    }
}
