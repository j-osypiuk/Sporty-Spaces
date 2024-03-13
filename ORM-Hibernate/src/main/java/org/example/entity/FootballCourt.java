package org.example.entity;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@Access(AccessType.FIELD)
@DiscriminatorValue("football")
public class FootballCourt extends Court {

    private double goalWidth;
    private double goalLength;


    public FootballCourt(double width, double length, double goalWidth, double goalLength) {
        super(width, length);
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
}
