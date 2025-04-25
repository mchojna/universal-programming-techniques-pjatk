package zad1;


import javax.swing.*;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class GamePanel extends JPanel {

    final List<Drawable> drawableList;
    Player player;

    Timer timer = new Timer(16, (e) -> repaint());

    public GamePanel(List<Drawable> drawableList, Player player){
        this.drawableList = drawableList;
        this.player = player;
        setPreferredSize(new Dimension(400, 460));

        InputMap im = getInputMap();
        ActionMap am = getActionMap();

        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0), "onA");
        am.put("onA", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(player.checkLeftBoundaries(30)) player.move(-10, 0);
                repaint();
            }
        });

        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0), "onD");
        am.put("onD", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(player.checkRightBoundaries(360)) player.move(10, 0);
                repaint();
            }
        });

        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_C, 0), "onC");
        am.put("onC", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Drawable> elementsToRemove = new ArrayList<>();
                for(Drawable drawable: drawableList){
                    if(drawable instanceof Bullet || drawable instanceof Blood){
                        elementsToRemove.add(drawable);
                    }
                }
                drawableList.removeAll(elementsToRemove);
                repaint();
            }
        });

        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_L, 0), "onL");
        am.put("onL", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(player.getAmmo() > 0){
                    singleShoot(player, drawableList, 35);
                }
            }
        });

        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_K, 0), "onK");
        am.put("onK", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(player.getAmmo() >= 2 && player.getLevel() >= 5){
                    doubleShoot(player, drawableList, 35, -5);
                }
            }
        });

        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_J, 0), "onJ");
        am.put("onJ", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(player.getAmmo() > 0 && player.getLevel() >= 5){
                    singleShoot(player, drawableList, -5);
                }
            }
        });

        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_R, 0), "onR");
        am.put("onR", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                player.reload();
                repaint();
            }
        });

        timer.start();
    }

    private void singleShoot(Player player, List<Drawable> drawableList, int offsetX){
        player.setAmmo(player.getAmmo() - 1);
        Bullet bullet = new Bullet(player.getCorrX() + offsetX, player.getCorrY() - 15, 1, 2, player);
        drawableList.add(bullet);
        new Thread(() -> {
            while(bullet.getCorrY() > 10){
                try {
                    bullet.move(0, -1);
                    synchronized (drawableList){
                        bullet.makeDamage(drawableList);
                    }
                    Thread.sleep(1);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
                repaint();
            }
        }).start();
    }

    private void doubleShoot(Player player, List<Drawable> drawableList, int rightOffsetX, int leftOffsetX){
        this.singleShoot(player, drawableList, rightOffsetX);
        this.singleShoot(player, drawableList, leftOffsetX);
    }

    @Override
    protected void paintComponent(Graphics g) {

        synchronized (this.drawableList){
            for(Drawable drawable: this.drawableList){
                drawable.draw(g);
            }
        }
    }
}
