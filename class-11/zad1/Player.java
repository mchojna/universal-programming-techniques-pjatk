package zad1;


import java.awt.*;

public class Player extends Actor{

    private int points;
    private int allPoints;

    private int level;
    private int allLevels;

    private int ammo;
    private int allAmmo;

    private int kills;

    public Player(int corrX, int corrY, Color headColor, Color bodyColor) {
        super(corrX, corrY, headColor, bodyColor);
        super.rightHand.setColor(headColor);
        super.leftHand.setColor(headColor);

        this.points = 0;
        this.allPoints = 99;

        this.level = 0;
        this.allLevels = 99;

        this.ammo = 9;
        this.allAmmo = 99;

        this.kills = 0;
    }

    public void kill(){
        this.kills += 1;
    }

    public void buff(){
        if(51 + this.points >= 99){
            this.points += 51;
            this.level += this.points / 99;
            this.points -= 99;
            this.allAmmo += 29;
        } else {
            this.points += 51;
        }
    }

    public void reload(){
        int reload = 9 - this.ammo;
        if(this.allAmmo > reload && this.ammo < 9){
            this.ammo += reload;
            this.allAmmo -= reload;
        } else if (this.allAmmo > 0 && reload > 0) {
            this.ammo = this.allAmmo;
            this.allAmmo = 0;
        }
    }

    public int getKills(){
        return kills;
    }

    public void setKills(int kills){
        this.kills = kills;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getAllPoints() {
        return allPoints;
    }

    public void setAllPoints(int allPoints) {
        this.allPoints = allPoints;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getAllLevels() {
        return allLevels;
    }

    public void setAllLevels(int allLevels) {
        this.allLevels = allLevels;
    }

    public int getAmmo() {
        return ammo;
    }

    public void setAmmo(int ammo) {
        this.ammo = ammo;
    }

    public int getAllAmmo() {
        return allAmmo;
    }

    public void setAllAmmo(int allAmmo) {
        this.allAmmo = allAmmo;
    }

    public void drawGuns(Graphics g){
        g.setColor(Color.BLACK);
        g.fillRect(corrX + 33, corrY - 25, 4, 20);
        if(this.level >= 5){
            g.fillRect(corrX - 7, corrY - 25, 4, 20);
        }
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        this.drawGuns(g);
    }
}
