package org.example.entity;

import java.util.Objects;

public class VolleyballCourt extends Court {

    private double netLength;
    private double netWidth;

    public VolleyballCourt(int id,
                           double width,
                           double length,
                           double netLength,
                           double netWidth) {
        super(id, width, length);
        this.netLength = netLength;
        this.netWidth = netWidth;
    }

    public VolleyballCourt(int id,
                           double width,
                           double length,
                           int isRented,
                           double netLength,
                           double netWidth) {
        super(id, width, length);
        this.netLength = netLength;
        this.netWidth = netWidth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VolleyballCourt that = (VolleyballCourt) o;
        return Double.compare(netLength, that.netLength) == 0 && Double.compare(netWidth, that.netWidth) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(netLength, netWidth);
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
