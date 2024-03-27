package org.example.entityMgd;

import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonDiscriminator;
import org.bson.codecs.pojo.annotations.BsonProperty;

@BsonDiscriminator(key = "_clazz", value = "volleyball")
public class VolleyballCourtMgd extends CourtMgd {

    @BsonProperty("net_length")
    private double netLength;
    @BsonProperty("net_width")
    private double netWidth;

    @BsonCreator
    public VolleyballCourtMgd(
            @BsonProperty("_id") int id,
            @BsonProperty("width") double width,
            @BsonProperty("length") double length,
            @BsonProperty("is_rented") int isRented,
            @BsonProperty("net_length") double netLength,
            @BsonProperty("net_width") double netWidth) {
        super(id, width, length, isRented);
        this.netLength = netLength;
        this.netWidth = netWidth;
    }

    public double getNetLength() {
        return netLength;
    }

    public void setNetLength(double netLength) {
        this.netLength = netLength;
    }

    public double getNetWidth() {
        return netWidth;
    }

    public void setNetWidth(double netWidth) {
        this.netWidth = netWidth;
    }


}
