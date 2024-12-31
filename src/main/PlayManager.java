package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.Objects;
import java.util.Random;
import mino.Block;
import mino.Mino;
import mino.MinoI;
import mino.MinoJ;
import mino.MinoL;
import mino.MinoO;
import mino.MinoS;
import mino.MinoT;
import mino.MinoZ;

public class PlayManager {

  // Play area
  public static int leftX;
  public static int rightX;
  public static int topY;
  public static int bottomY;
  public static int dropInterval = 60;
  final int WIDTH = 360;
  final int HEIGHT = 600;
  final int MINO_START_X;
  final int MINO_START_Y;
  Mino currentMino;


  public PlayManager() {
    leftX = (GamePanel.WIDTH / 2) - (WIDTH / 2);
    rightX = leftX + WIDTH;
    topY = 50;
    bottomY = topY + HEIGHT;

    MINO_START_X = leftX + (WIDTH / 2) - Block.SIZE;
    MINO_START_Y = topY + Block.SIZE;

    currentMino = selectMino();
    currentMino.setXY(MINO_START_X, MINO_START_Y);
  }

  private Mino selectMino() {
    Mino mino = null;
    int i = new Random().nextInt(7);

    mino = switch (i) {
      case 0 -> new MinoI();
      case 1 -> new MinoJ();
      case 2 -> new MinoL();
      case 3 -> new MinoO();
      case 4 -> new MinoS();
      case 5 -> new MinoT();
      case 6 -> new MinoZ();
      default -> throw new IllegalStateException("Unexpected value: " + i);
    };
    return mino;
  }

  public void update() {
    currentMino.update();
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

    // Draw current mino
    if (Objects.nonNull(currentMino)) {
      currentMino.draw(g2);
    }

    // Draw Pause
    g2.setColor(Color.white);
    g2.setFont(g2.getFont().deriveFont(40f));
    if (KeyHandler.spacePressed) {
      x = leftX + 100;
      y = topY + 310;
      g2.drawString("PAUSED", x, y);
    }

  }
}
