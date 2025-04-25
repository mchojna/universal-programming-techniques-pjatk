/**
 *
 *  @author Chojna Michał S29758
 *
 */

package zad1;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {

  JFrame appFrame;
  JPanel appPanel;
  JFrame gameFrame;
  JPanel gamePanel;
  Player player;
  Background background;
  Info info;
  List<Drawable> drawableList;

  ExecutorService executorService;
  List<Future<String>> futureList;

  public static void main(String[] args){
    new Main();
  }

  public Main(){
    this.background = new Background();
    this.player = new Player(190, 360, new Color(185, 180, 199), new Color(71, 102, 59));
    this.info = new Info(this.player);

    this.drawableList = Collections.synchronizedList(new ArrayList<>());

    this.drawableList.add(this.background);
    this.drawableList.add(this.info);
    this.drawableList.add(this.player);

    this.executorService = Executors.newCachedThreadPool();
    this.futureList = Collections.synchronizedList(new ArrayList<>());

    SwingUtilities.invokeLater(this::createApp);
    SwingUtilities.invokeLater(this::createGame);
  }

  private void createApp(){
    this.appFrame = new JFrame();
    this.appFrame.setTitle("App");
    this.appFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.appFrame.setLocation(0, 0);
    this.appFrame.setResizable(false);
    this.appFrame.setVisible(true);

    this.appPanel = new AppPanel(executorService, futureList, drawableList);
    this.appFrame.add(appPanel);
    this.appFrame.pack();

    this.appFrame.setAlwaysOnTop(true);
  }

  private void createGame(){
    this.gameFrame = new JFrame();
    this.gameFrame.setTitle("Game");
    this.gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.gameFrame.setLocation(450, 0);
    this.gameFrame.setResizable(false);
    this.gameFrame.setVisible(true);

    this.gamePanel = new GamePanel(this.drawableList, this.player);
    this.gameFrame.add(gamePanel);
    this.gameFrame.pack();
    this.gameFrame.setAlwaysOnTop(true);
  }
}
