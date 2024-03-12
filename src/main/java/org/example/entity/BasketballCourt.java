package org.example.entity;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("basketball")
@Access(AccessType.FIELD)
public class BasketballCourt extends Court {

    private double basketHeight;
    private double basketRadius;

    public BasketballCourt(double width, double length, double basketHeight, double basketRadius) {
        super(width, length);
        this.basketHeight = basketHeight;
        this.basketRadius = basketRadius;
    }

    public BasketballCourt() {
    }

    public double getBasketHeight() {
        return basketHeight;
    }

    public double getBasketRadius() {
        return basketRadius;
    }
}
