package org.example.entity;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@Access(AccessType.FIELD)
@DiscriminatorValue("volleyball")
public class VolleyballCourt extends Court {

    private double netLength;
    private double netWidth;

    public VolleyballCourt(double width, double length, double netLength, double netWidth) {
        super(width, length);
        this.netLength = netLength;
        this.netWidth = netWidth;
    }

    public VolleyballCourt() {
    }

    public double getNetLength() {
        return netLength;
    }

    public double getNetWidth() {
        return netWidth;
    }
}
