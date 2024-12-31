package mino;

import java.awt.Color;
import java.awt.Graphics2D;
import main.KeyHandler;
import main.PlayManager;

public class Mino {

  public Block[] b = new Block[4];
  public Block[] tempB = new Block[4];
  public int rotation = 0; // 4 directions (0 | 90 | 180 | 270)
  public boolean active = true;
  public boolean deactivating;
  int autoDropCounter = 0;
  boolean leftCollision, rightCollision, bottomCollision;
  int deactivateCounter = 0;

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

    checkRotationCollision();

    if (leftCollision || rightCollision || bottomCollision) {
      return;
    }

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

  public void checkMovementCollision() {
    leftCollision = false;
    rightCollision = false;
    bottomCollision = false;

    // Check stack block collision
    checkStackBlocksCollision();

    // Left collision
    for (Block block : b) {
      if (block.x == PlayManager.leftX) {
        leftCollision = true;
        break;
      }
    }

    // Right collision
    for (Block block : b) {
      if (block.x + Block.SIZE == PlayManager.rightX) {
        rightCollision = true;
        break;
      }
    }

    // Bottom collision
    for (Block block : b) {
      if (block.y + Block.SIZE == PlayManager.bottomY) {
        bottomCollision = true;
        break;
      }
    }

  }

  public void checkRotationCollision() {
    leftCollision = false;
    rightCollision = false;
    bottomCollision = false;

    // Check stack block collision
    checkStackBlocksCollision();

    // Left collision
    for (Block block : tempB) {
      if (block.x < PlayManager.leftX) {
        leftCollision = true;
        break;
      }
    }

    // Right collision
    for (Block block : tempB) {
      if (block.x + Block.SIZE > PlayManager.rightX) {
        rightCollision = true;
        break;
      }
    }

    // Bottom collision
    for (Block block : tempB) {
      if (block.y + Block.SIZE > PlayManager.bottomY) {
        bottomCollision = true;
        break;
      }
    }
  }

  public void checkStackBlocksCollision() {
    for (Block stackBlock : PlayManager.stackBlocks) {
      int targetX = stackBlock.x;
      int targetY = stackBlock.y;

      // Check bottom
      for (Block block : b) {
        if (block.y + Block.SIZE == targetY && block.x == targetX) {
          bottomCollision = true;
          break;
        }
        if (block.x - Block.SIZE == targetX && block.y == targetY) {
          leftCollision = true;
          break;
        }
        if (block.x + Block.SIZE == targetX && block.y == targetY) {
          rightCollision = true;
          break;
        }
      }

    }

  }

  public void update() {

    if (deactivating) {
      deactivating();
    }

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

    checkMovementCollision();

    if (KeyHandler.downPressed) {
      KeyHandler.downPressed = false;

      if (bottomCollision) {
        return;
      }

      for (Block block : b) {
        block.y += Block.SIZE;
      }

      autoDropCounter = 0;
    }

    if (KeyHandler.rightPressed) {
      KeyHandler.rightPressed = false;

      if (rightCollision) {
        return;
      }

      for (Block block : b) {
        block.x += Block.SIZE;
      }

      autoDropCounter = 0;
    }

    if (KeyHandler.leftPressed) {
      KeyHandler.leftPressed = false;

      if (leftCollision) {
        return;
      }

      for (Block block : b) {
        block.x -= Block.SIZE;
      }

      autoDropCounter = 0;

    }

    if (bottomCollision) {
      deactivating = true;
      return;
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

  private void deactivating() {
    deactivateCounter++;
    if (deactivateCounter == 30) {
      deactivateCounter = 0;
      checkMovementCollision();

      if (bottomCollision) {
        active = false;
      }
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
