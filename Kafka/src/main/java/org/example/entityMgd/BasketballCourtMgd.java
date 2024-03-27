package org.example.entityMgd;

import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonDiscriminator;
import org.bson.codecs.pojo.annotations.BsonProperty;

@BsonDiscriminator(key = "_clazz", value = "basketball")
public class BasketballCourtMgd extends CourtMgd {

    @BsonProperty("basket_height")
    private double basketHeight;
    @BsonProperty("basket_radius")
    private double basketRadius;

    @BsonCreator
    public BasketballCourtMgd(
            @BsonProperty("_id") int id,
            @BsonProperty("width") double width,
            @BsonProperty("length") double length,
            @BsonProperty("is_rented") int isRented,
            @BsonProperty("basket_height") double basketHeight,
            @BsonProperty("basket_radius") double basketRadius) {
        super(id, width, length, isRented);
        this.basketHeight = basketHeight;
        this.basketRadius = basketRadius;
    }

    public double getBasketHeight() {
        return basketHeight;
    }

    public void setBasketHeight(double basketHeight) {
        this.basketHeight = basketHeight;
    }

    public double getBasketRadius() {
        return basketRadius;
    }

    public void setBasketRadius(double basketRadius) {
        this.basketRadius = basketRadius;
    }
}
