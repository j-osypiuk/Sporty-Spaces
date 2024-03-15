package org.example.entity;

import com.datastax.oss.driver.api.mapper.annotations.CqlName;
import com.datastax.oss.driver.api.mapper.annotations.Entity;
import org.example.names.CourtIds;

import java.util.Objects;

@Entity(defaultKeyspace = "rent_a_court")
@CqlName("courts")
public class BasketballCourt extends Court {

    @CqlName(CourtIds.BASKET_HEIGHT)
    private double basketHeight;
    @CqlName(CourtIds.BASKET_RADIUS)
    private double basketRadius;

    public BasketballCourt(int id, double basketHeight, double basketRadius, double width, double length, boolean rented, String discriminator) {
        super(id, width, length, rented, CourtIds.BASKETBALL);
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

    public void setBasketHeight(double basketHeight) {
        this.basketHeight = basketHeight;
    }

    public void setBasketRadius(double basketRadius) {
        this.basketRadius = basketRadius;
    }

    @Override
    public String toString() {
        return "BasketballCourt{" +
                "id=" + super.getId() +
                ", width=" + super.getWidth() +
                ", length=" + super.getLength() +
                ", isRented=" + super.isRented() +
                ", discriminator='" + super.getDiscriminator() +
                ", basketHeight=" + basketHeight +
                ", basketRadius=" + basketRadius +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        BasketballCourt that = (BasketballCourt) o;
        return Double.compare(basketHeight, that.basketHeight) == 0 && Double.compare(basketRadius, that.basketRadius) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), basketHeight, basketRadius);
    }
}
