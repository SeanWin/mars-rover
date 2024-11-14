package org.example;

public class PlateauSize {
    private int x;
    private int y;

    public PlateauSize(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    @Override
    public String toString() {
        return "PlateauSize{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
