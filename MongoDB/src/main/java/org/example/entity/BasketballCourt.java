package org.example.entity;

import java.util.Objects;

public class BasketballCourt extends Court {

    private double basketHeight;
    private double basketRadius;

    public BasketballCourt(int id,
                           double width,
                           double length,
                           double basketHeight,
                           double basketRadius) {
        super(id, width, length);
        this.basketHeight = basketHeight;
        this.basketRadius = basketRadius;
    }

    public BasketballCourt(int id,
                           double width,
                           double length,
                           int isRented,
                           double basketHeight,
                           double basketRadius) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BasketballCourt that = (BasketballCourt) o;
        return Double.compare(basketHeight, that.basketHeight) == 0 && Double.compare(basketRadius, that.basketRadius) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(basketHeight, basketRadius);
    }
}
