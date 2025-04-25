package zad1;


import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Bullet implements Drawable {
    private int corrX;
    private int corrY;
    private final int width;
    private final int height;
    private Color color;
    private Player player;

    public Bullet(int corrX, int corrY, int width, int height, Player player) {
        this.corrX = corrX;
        this.corrY = corrY;
        this.width = width;
        this.height = height;
        this.color = new Color(0 ,0 ,0);
        this.player = player;
    }

    public void move(int corrX, int corrY){
        this.corrX += corrX;
        this.corrY += corrY;
    }

    public int getCorrY() {
        return corrY;
    }

    public void setCorrY(int corrY) {
        this.corrY = corrY;
    }

    public int getCorrX() {
        return corrX;
    }

    public void setCorrX(int corrX) {
        this.corrX = corrX;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(this.color);
        g.drawRect(corrX, corrY, width, height);
    }

    public void makeDamage(List<Drawable> drawableList) {
        List<Drawable> deadEnemies = new ArrayList<>();
        List<Drawable> bloodToAdd = new ArrayList<>();

        for(Drawable drawable: drawableList){
            if(drawable instanceof Enemy){
                Enemy enemy = (Enemy) drawable;

                int damage = (this.corrY >= 200) ? 2 : 1;
                boolean shoot = enemy.checkDamage(this.corrX, this.corrY, damage);

                if(shoot){
                    this.color = new Color(255, 0, 0);
                }

                if(enemy.getHealthLevel() <= 0){
                    deadEnemies.add(enemy);
                    bloodToAdd.add(new Blood(enemy.getCorrX(), enemy.getCorrY()));
                    player.buff();
                    player.kill();
                }
            }
        }

        drawableList.removeAll(deadEnemies);
        drawableList.addAll(bloodToAdd);
    }
}
