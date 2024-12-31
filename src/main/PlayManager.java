package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.Arrays;
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
  public static ArrayList<Block> stackBlocks = new ArrayList<>();
  final int WIDTH = 360;
  final int HEIGHT = 600;
  final int MINO_START_X;
  final int MINO_START_Y;
  final int NEXTMINO_X;
  final int NEXTMINO_Y;
  final int MAX_ROW = 12;
  Mino currentMino;
  Mino nextMino;
  boolean effectCounterOn;
  int effectCounter;
  ArrayList<Integer> effectsY = new ArrayList<>();
  boolean gameOver;


  public PlayManager() {
    leftX = (GamePanel.WIDTH / 2) - (WIDTH / 2);
    rightX = leftX + WIDTH;
    topY = 50;
    bottomY = topY + HEIGHT;

    MINO_START_X = leftX + (WIDTH / 2) - Block.SIZE;
    MINO_START_Y = topY + Block.SIZE;

    NEXTMINO_X = rightX + 180;
    NEXTMINO_Y = topY + 90;

    currentMino = selectMino();
    currentMino.setXY(MINO_START_X, MINO_START_Y);
    nextMino = selectMino();
    nextMino.setXY(NEXTMINO_X, NEXTMINO_Y);
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
    if (!currentMino.active) {
      stackBlocks.addAll(Arrays.asList(currentMino.b));

      if (currentMino.b[0].x == MINO_START_X && currentMino.b[0].y == MINO_START_Y) {
        gameOver = true;
      }

      currentMino.deactivating = false;

      // Replace the currentMino
      currentMino = nextMino;
      currentMino.setXY(MINO_START_X, MINO_START_Y);
      nextMino = selectMino();
      nextMino.setXY(NEXTMINO_X, NEXTMINO_Y);

      checkDelete();

      return;
    }
    currentMino.update();
  }

  public void checkDelete() {
    int x = leftX;
    int y = topY;
    int blockCount = 0;

    while (x < rightX && y < bottomY) {
      for (Block stackBlock : stackBlocks) {
        if (stackBlock.x == x && stackBlock.y == y) {
          blockCount++;
        }
      }

      x += Block.SIZE;

      if (x == rightX) {
        if (blockCount == MAX_ROW) {

          effectCounterOn = true;
          effectsY.add(y);

          for (int i = stackBlocks.size() - 1; i > -1; i--) {
            if (stackBlocks.get(i).y == y) {
              stackBlocks.remove(i);
            }
          }
          for (Block stackBlock : stackBlocks) {
            if (stackBlock.y < y) {
              stackBlock.y += Block.SIZE;
            }
          }
        }
        blockCount = 0;
        x = leftX;
        y += Block.SIZE;
      }
    }

  }

  public void draw(Graphics2D g2) {

    // Draw play tetris frame
    g2.setColor(Color.white);
    g2.setStroke(new BasicStroke(4f));
    g2.drawRect(leftX - 4, topY - 4, WIDTH + 8, HEIGHT + 8);

    // Draw next mino frame
    int x = rightX + 100;
    int y = topY - 4;
    g2.drawRect(x, y, 200, 200);
    g2.setFont(new Font("Arial", Font.PLAIN, 20));
    g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
        RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
    g2.drawString("NEXT", x + 70, y + 30);

    // Draw current mino
    if (Objects.nonNull(currentMino)) {
      currentMino.draw(g2);
    }

    // Draw next mino
    nextMino.draw(g2);

    // Draw stack mino
    for (Block block : stackBlocks) {
      block.draw(g2);
    }

    // Draw delete effect
    if (effectCounterOn) {
      effectCounter++;
      g2.setColor(Color.red);
      for (Integer effectY : effectsY) {
        g2.fillRect(leftX, effectY, WIDTH, Block.SIZE);
      }

      if (effectCounter == 10) {
        effectCounterOn = false;
        effectCounter = 0;
        effectsY.clear();
      }

    }

    // Draw Pause
    g2.setColor(Color.white);
    g2.setFont(g2.getFont().deriveFont(40f));
    if (gameOver) {
      x = leftX + 60;
      y = topY + 310;
      g2.drawString("GAME OVER", x, y);
      return;
    }

    if (KeyHandler.spacePressed) {
      x = leftX + 100;
      y = topY + 310;
      g2.drawString("PAUSED", x, y);
    }

  }
}
