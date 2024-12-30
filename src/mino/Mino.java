package mino;

import java.awt.Color;
import java.awt.Graphics2D;
import main.KeyHandler;
import main.PlayManager;

public class Mino {

  public Block[] b = new Block[4];
  public Block[] tempB = new Block[4];
  public int rotation = 0; // 4 directions (0 | 90 | 180 | 270)
  int autoDropCounter = 0;

  public void create(Color c) {
    b[0] = new Block(c);
    b[1] = new Block(c);
    b[2] = new Block(c);
    b[3] = new Block(c);
    tempB[0] = new Block(c);
    tempB[1] = new Block(c);
    tempB[2] = new Block(c);
    tempB[3] = new Block(c);
  }

  public void setXY(int x, int y) {
  }

  public void updateXY(int rotation) {
    this.rotation = rotation;

    b[0].x = tempB[0].x;
    b[0].y = tempB[0].y;
    b[1].x = tempB[1].x;
    b[1].y = tempB[1].y;
    b[2].x = tempB[2].x;
    b[2].y = tempB[2].y;
    b[3].x = tempB[3].x;
    b[3].y = tempB[3].y;

  }

  public void getRotation0() {
  }

  public void getRotation90() {
  }

  public void getRotation180() {
  }

  public void getRotation270() {
  }


  public void update() {

    if (KeyHandler.upPressed) {
      switch (rotation) {
        case 0:
          getRotation90();
          break;
        case 90:
          getRotation180();
          break;
        case 180:
          getRotation270();
          break;
        case 270:
          getRotation0();
          break;
      }

      KeyHandler.upPressed = false;
    }

    if (KeyHandler.downPressed) {
      b[0].y += Block.SIZE;
      b[1].y += Block.SIZE;
      b[2].y += Block.SIZE;
      b[3].y += Block.SIZE;

      autoDropCounter = 0;

      KeyHandler.downPressed = false;
    }

    if (KeyHandler.rightPressed) {
      b[0].x += Block.SIZE;
      b[1].x += Block.SIZE;
      b[2].x += Block.SIZE;
      b[3].x += Block.SIZE;

      autoDropCounter = 0;

      KeyHandler.rightPressed = false;
    }

    if (KeyHandler.leftPressed) {
      b[0].x -= Block.SIZE;
      b[1].x -= Block.SIZE;
      b[2].x -= Block.SIZE;
      b[3].x -= Block.SIZE;

      autoDropCounter = 0;

      KeyHandler.leftPressed = false;
    }

    autoDropCounter++;
    if (autoDropCounter == PlayManager.dropInterval) {
      b[0].y += Block.SIZE;
      b[1].y += Block.SIZE;
      b[2].y += Block.SIZE;
      b[3].y += Block.SIZE;
      autoDropCounter = 0;
    }

  }

  public void draw(Graphics2D g2) {

    int MARGIN = 2;
    g2.setColor(b[0].c);
    g2.fillRect(b[0].x + MARGIN, b[0].y + MARGIN, Block.SIZE - (MARGIN * 2),
        Block.SIZE - (MARGIN * 2));
    g2.fillRect(b[1].x + MARGIN, b[1].y + MARGIN, Block.SIZE - (MARGIN * 2),
        Block.SIZE - (MARGIN * 2));
    g2.fillRect(b[2].x + MARGIN, b[2].y + MARGIN, Block.SIZE - (MARGIN * 2),
        Block.SIZE - (MARGIN * 2));
    g2.fillRect(b[3].x + MARGIN, b[3].y + MARGIN, Block.SIZE - (MARGIN * 2),
        Block.SIZE - (MARGIN * 2));
  }

}
