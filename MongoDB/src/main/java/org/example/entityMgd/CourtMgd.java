package org.example.entityMgd;

import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonDiscriminator;
import org.bson.codecs.pojo.annotations.BsonProperty;

@BsonDiscriminator(key = "_clazz")
public abstract class CourtMgd extends AbstractEntityMgd {

    @BsonProperty("width")
    private double width;
    @BsonProperty("length")
    private double length;
    @BsonProperty("is_rented")
    private int isRented;

    @BsonCreator
    public CourtMgd(
            @BsonProperty("_id") int id,
            @BsonProperty("width") double width,
            @BsonProperty("length") double length,
            @BsonProperty("is_rented") int isRented
    ) {
        super(id);
        this.width = width;
        this.length = length;
        this.isRented = isRented;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public int getIsRented() {
        return isRented;
    }

    public void setIsRented(int isRented) {
        this.isRented = isRented;
    }
}
