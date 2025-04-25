package zad1;


import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Info implements Drawable{

    private Player player;
    private Font customFont;

    public Info(Player player){
        this.player = player;

        try {
            this.customFont = Font.createFont(Font.TRUETYPE_FONT, new File("04B_19__.TTF"));
        } catch (FontFormatException | IOException e) {
            this.customFont = new Font("Arial", Font.BOLD, 14);
        }

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(customFont);

    }

    private void drawText(Graphics g){
//        g.setFont(new Font("Arial", Font.BOLD, 14));
        customFont = customFont.deriveFont(14f);
        g.setFont(customFont);
        g.setColor(new Color(27, 27, 28));

        g.drawChars("Ammo:".toCharArray(), 0, 5, 15, 425);
        g.drawChars("Level:".toCharArray(), 0, 6, 115, 425);
        g.drawChars("Points:".toCharArray(), 0, 7, 215, 425);
        g.drawChars("Kills:".toCharArray(), 0, 6, 315, 425);

//        g.setFont(new Font("Arial", Font.PLAIN, 18));
        customFont = customFont.deriveFont(18f);
        g.setFont(customFont);
        g.setColor(new Color(27, 27, 28));

        char[] ammo = (player.getAmmo() + "/" + player.getAllAmmo()).toCharArray();
        char[] level = (player.getLevel() + "/" + player.getAllLevels()).toCharArray();
        char[] points = (player.getPoints() + "/" + player.getAllPoints()).toCharArray();
        char[] kills = (player.getKills() + "").toCharArray();

        g.drawChars(ammo, 0, ammo.length, 15, 445);
        g.drawChars(level, 0, level.length, 115, 445);
        g.drawChars(points, 0, points.length, 215, 445);
        g.drawChars(kills, 0, kills.length, 315, 445);
    };

    @Override
    public void draw(Graphics g) {
        g.setColor(new Color(64, 66, 88));
        g.fillRect(0, 400, 400, 60);

        g.setColor(new Color(71, 78, 104));
        g.fillRect(10, 410, 380, 40);

        this.drawText(g);
    }
}
