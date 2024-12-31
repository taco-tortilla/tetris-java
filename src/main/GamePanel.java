package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Objects;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {

  public static final int WIDTH = 1280;
  public static final int HEIGHT = 720;
  final int FPS = 60;
  Thread gameThread;
  PlayManager pm;

  public GamePanel() {
    this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
    this.setBackground(Color.DARK_GRAY);
    this.setLayout(null);

    // KeyListener
    this.addKeyListener(new KeyHandler());
    this.setFocusable(true);

    pm = new PlayManager();
  }

  public void start() {
    gameThread = new Thread(this);
    gameThread.start();
  }


  @Override
  public void run() {

    // Game loop
    double drawInterval = (double) 1000000000 / FPS;
    double delta = 0;
    long lastTime = System.nanoTime();
    long currentTime;

    while (Objects.nonNull(gameThread)) {
      currentTime = System.nanoTime();

      delta += (currentTime - lastTime) / drawInterval;
      lastTime = currentTime;

      if (delta >= 1) {
        update();
        repaint();
        delta--;
      }
    }

  }

  private void update() {
    if (KeyHandler.spacePressed) {
      return;
    }
    pm.update();
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    Graphics2D g2 = (Graphics2D) g;
    pm.draw(g2);
  }
}
