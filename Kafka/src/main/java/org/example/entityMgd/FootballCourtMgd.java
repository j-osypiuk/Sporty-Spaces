package org.example.entityMgd;

import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonDiscriminator;
import org.bson.codecs.pojo.annotations.BsonProperty;

@BsonDiscriminator(key = "_clazz", value = "football")
public class FootballCourtMgd extends CourtMgd {

    @BsonProperty("goal_width")
    private double goalWidth;
    @BsonProperty("goal_length")
    private double goalLength;

    @BsonCreator
    public FootballCourtMgd(
            @BsonProperty("_id") int id,
            @BsonProperty("width") double width,
            @BsonProperty("length") double length,
            @BsonProperty("is_rented") int isRented,
            @BsonProperty("goal_width") double goalWidth,
            @BsonProperty("goal_length") double goalLength) {
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
}
