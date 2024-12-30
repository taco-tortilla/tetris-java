package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class PlayManager {

  // Play area
  public static int leftX;
  public static int rightX;
  public static int topY;
  public static int bottomY;
  final int WIDTH = 360;
  final int HEIGHT = 600;

  public PlayManager() {
    leftX = (GamePanel.WIDTH / 2) - (WIDTH / 2);
    rightX = leftX + WIDTH;
    topY = 50;
    bottomY = topY + HEIGHT;
  }

  public void update() {

  }

  public void draw(Graphics2D g2) {

    // Draw play tetris frame
    g2.setColor(Color.white);
    g2.setStroke(new BasicStroke(4f));
    g2.drawRect(leftX - 4, topY - 4, WIDTH + 8, HEIGHT + 8);

    // Draw next mino frame
    int x = rightX + 100;
    int y = bottomY - 200;
    g2.drawRect(x, y, 200, 200);
    g2.setFont(new Font("Arial", Font.PLAIN, 20));
    g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
        RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
    g2.drawString("NEXT", x + 70, y + 30);

  }
}
