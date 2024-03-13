package org.example.entity;

public abstract class Court {

    private int id;
    private double width;
    private double length;
    private int isRented;


    public Court(int id,
                 double width,
                 double length) {
        this.id = id;
        this.width = width;
        this.length = length;
    }

    public Court(int id,
                 double width,
                 double length,
                 int isRented) {
        this.id = id;
        this.width = width;
        this.length = length;
        this.isRented = isRented;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
