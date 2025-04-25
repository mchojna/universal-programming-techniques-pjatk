package zad1;


import java.awt.*;
import java.util.List;
import java.util.Objects;

public class Enemy extends Actor{

    private static int globalIndex = 0;
    private int index;

    private int healthLevel;
    private int topLeftX;
    private int topLeftY;
    private int bottomRightX;
    private int bottomRightY;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Enemy enemy = (Enemy) o;
        return index == enemy.index;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(index);
    }

    public Enemy(int corrX, int corrY, Color headColor, Color bodyColor) {
        super(corrX, corrY, headColor, bodyColor);
        super.rightHand.setCorrY(corrY + 5);
        super.leftHand.setCorrY(corrY + 5);
        super.rightHand.setHeight(10);
        super.leftHand.setHeight(10);

        this.index = globalIndex++;
//        this.healthLevel = (int)(80 + Math.random() * 50);
        this.healthLevel = 100;

        topLeftX = leftHand.getCorrX();
        topLeftY = body.getCorrY();
        bottomRightX = rightHand.getCorrX() + rightHand.getWidth();
        bottomRightY = body.getCorrY() + body.getHeight();
    }

    public static Enemy createEnemy(List<Drawable> drawableList){
        int x = (int)(Math.random() * 300 + 20);
        int y = (int)(Math.random() * 200 + 20);

        Color bodyColor = new Color(
                (int) (Math.random() * 255),
                (int) (Math.random() * 255),
                (int) (Math.random() * 255)
        );
        Color headColor = new Color(
                (int) (Math.random() * 255),
                (int) (Math.random() * 255),
                (int) (Math.random() * 255)
        );

        return new Enemy(x, y, headColor, bodyColor);
    }

    public boolean checkDamage(int x, int y, int damage){
        if(x >= this.topLeftX && x <= this.bottomRightX && y >= topLeftY && y <= bottomRightY){
            healthLevel -= damage;
            return true;
        }

        return false;
    }

    public boolean isAlive(){
        return this.healthLevel > 0;
    }

    public int getHealthLevel() {
        return healthLevel;
    }

    public void setHealthLevel(int healthLevel) {
        this.healthLevel = healthLevel;
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);

        g.setColor(Color.BLACK);
        g.fillRect(this.body.getCorrX(), this.body.getCorrY() - 20, this.body.getWidth(),10);
        g.setColor(Color.RED);

        int healthLevelWidth = (int)(((this.body.getWidth() - 4) * this.healthLevel) / 100.0);
        g.fillRect(this.body.getCorrX() + 2, this.body.getCorrY() - 18, healthLevelWidth, 10 - 4);
    }
}
