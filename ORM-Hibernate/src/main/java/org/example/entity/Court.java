package org.example.entity;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
@Access(AccessType.FIELD)
public abstract class Court {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double width;
    private double length;
    @Version
    private int version;
    private boolean isRented;

    public Court(double width, double length) {
        this.width = width;
        this.length = length;
    }

    public Court() {
    }

    public int getId() {
        return id;
    }

    public boolean isRented() {
        return isRented;
    }

    public void setRented(boolean rented) {
        isRented = rented;
    }
    public double getWidth() {
        return width;
    }
    public double getLength() {
        return length;
    }
}
