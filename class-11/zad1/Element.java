package zad1;


import java.awt.*;

public class Element {
    private int corrX;
    private int corrY;
    private int width;
    private int height;
    private Color color;

    public Element (int corrX, int corrY, int width, int height, Color color){
        this.corrX = corrX;
        this.corrY = corrY;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    public int getCorrX() {
        return corrX;
    }

    public void setCorrX(int corrX) {
        this.corrX = corrX;
    }

    public int getCorrY() {
        return corrY;
    }

    public void setCorrY(int corrY) {
        this.corrY = corrY;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void move(int corrX, int corrY){
        this.corrX += corrX;
        this.corrY += corrY;
    }
}
