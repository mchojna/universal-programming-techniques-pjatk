package zad1;


import java.awt.*;

public class Blood implements Drawable{

    Element outerBloodPaddle;
    Element innerBloodPaddle;

    public Blood(int corrX, int corrY){
        this.outerBloodPaddle = new Element(corrX, corrY, 20, 20, new Color(180, 50, 50));
        this.innerBloodPaddle = new Element(corrX + 5, corrY + 5, 10, 10, new Color(255, 0, 0));
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(this.outerBloodPaddle.getColor());
        g.fillRect(this.outerBloodPaddle.getCorrX(), this.outerBloodPaddle.getCorrY(), this.outerBloodPaddle.getWidth() , this.outerBloodPaddle.getHeight());
        g.setColor(this.innerBloodPaddle.getColor());
        g.fillRect(this.innerBloodPaddle.getCorrX(), this.innerBloodPaddle.getCorrY(), this.innerBloodPaddle.getWidth() , this.innerBloodPaddle.getHeight());
    }
}
