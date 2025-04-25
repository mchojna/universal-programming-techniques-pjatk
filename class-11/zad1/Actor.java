package zad1;


import java.awt.*;

public abstract class Actor implements Drawable {
    protected int corrX;
    protected int corrY;

    protected Element body;
    protected Element head;
    protected Element rightHand;
    protected Element leftHand;

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

    public Actor(int corrX, int corrY, Color headColor, Color bodyColor){
        this.corrX = corrX;
        this.corrY = corrY;

        this.body = new Element(this.corrX, this.corrY, 30, 20, bodyColor);
        this.head = new Element(this.corrX + 10, this.corrY + 5, 10, 10, headColor);
        this.rightHand = new Element(this.corrX + 30, this.corrY - 15, 10, 30, bodyColor);
        this.leftHand = new Element(this.corrX - 10, this.corrY - 15, 10, 30, bodyColor);
    }

    public void move(int corrX, int corrY){
        this.body.move(corrX, corrY);
        this.head.move(corrX, corrY);
        this.rightHand.move(corrX, corrY);
        this.leftHand.move(corrX, corrY);

        this.corrX += corrX;
        this.corrY += corrY;
    }

    public boolean checkLeftBoundaries(int corrX){
        return this.leftHand.getCorrX() >= corrX;
    }

    public boolean checkRightBoundaries(int corrX){
        return this.rightHand.getCorrX() <= corrX;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(body.getColor());
        g.fillRect(body.getCorrX(), body.getCorrY(), body.getWidth(), body.getHeight());

        g.setColor(head.getColor());
        g.fillRect(head.getCorrX(), head.getCorrY(), head.getWidth(), head.getHeight());

        g.setColor(rightHand.getColor());
        g.fillRect(rightHand.getCorrX(), rightHand.getCorrY(), rightHand.getWidth(), rightHand.getHeight());
        g.setColor(leftHand.getColor());
        g.fillRect(leftHand.getCorrX(), leftHand.getCorrY(), leftHand.getWidth(), leftHand.getHeight());
    }
}
