package zad1;


import java.awt.*;

public class Background implements Drawable{

    private void createWindows(Graphics g, int x){
        int start = 40;
        for(int i = 0; i < 5; i++){
            g.setColor(new Color(19, 62, 135));
            g.fillRect(x, start + (20 * i), 10 ,40);

            g.setColor(new Color(19, 82, 185));
            g.fillRect(x + 3, start + (20 * i) + 3, 4 ,34);

            start += 50;
        }
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(new Color(62, 50, 50));
        g.fillRect(0, 0, 400 ,400);

        g.setColor(new Color(80, 60, 60));
        g.fillRect(10, 10, 380 ,380);

        g.setColor(new Color(80, 60, 60));
        g.fillRect(180, 380, 50 ,20);

        this.createWindows(g, 0);
        this.createWindows(g, 390);
    }
}
