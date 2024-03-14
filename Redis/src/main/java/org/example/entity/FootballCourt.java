package org.example.entity;

import jakarta.json.bind.annotation.JsonbCreator;

import java.util.Objects;

public class FootballCourt extends Court {

    private double goalWidth;
    private double goalLength;

    public FootballCourt(int id,
                         double width,
                         double length,
                         double goalWidth,
                         double goalLength) {
        super(id, width, length);
        this.goalWidth = goalWidth;
        this.goalLength = goalLength;
    }

    @JsonbCreator
    public FootballCourt(int id,
                         double width,
                         double length,
                         int isRented,
                         double goalWidth,
                         double goalLength) {
        super(id, width, length, isRented);
        this.goalWidth = goalWidth;
        this.goalLength = goalLength;
    }

    public double getGoalWidth() {
        return goalWidth;
    }

    public void setGoalWidth(double goalWidth) {
        this.goalWidth = goalWidth;
    }

    public double getGoalLength() {
        return goalLength;
    }

    public void setGoalLength(double goalLength) {
        this.goalLength = goalLength;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FootballCourt that = (FootballCourt) o;
        return Double.compare(goalWidth, that.goalWidth) == 0 && Double.compare(goalLength, that.goalLength) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(goalWidth, goalLength);
    }
}
