package mino;

import java.awt.Color;

public class MinoI extends Mino {

  public MinoI() {
    super.create(Color.CYAN);
  }

  @Override
  public void setXY(int x, int y) {
    /*
      I mino
      □ □ □ □

      Represented by an array
      b[1] b[0] b[2] b[3]
     */

    b[0].x = x;
    b[0].y = y;
    b[1].x = b[0].x - Block.SIZE;
    b[1].y = b[0].y;
    b[2].x = b[0].x + Block.SIZE;
    b[2].y = b[0].y;
    b[3].x = b[0].x + Block.SIZE * 2;
    b[3].y = b[0].y;

  }


  @Override
  public void getRotation0() {
    /*
      □ □ □ □
     */

    tempB[0].x = b[0].x;
    tempB[0].y = b[0].y;
    tempB[1].x = b[0].x - Block.SIZE;
    tempB[1].y = b[0].y;
    tempB[2].x = b[0].x + Block.SIZE;
    tempB[2].y = b[0].y;
    tempB[3].x = b[0].x + Block.SIZE * 2;
    tempB[3].y = b[0].y;

    updateXY(0);
  }

  @Override
  public void getRotation90() {
    /*
      □
      □
      □
      □
     */

    tempB[0].x = b[0].x;
    tempB[0].y = b[0].y;
    tempB[1].x = b[0].x;
    tempB[1].y = b[0].y - Block.SIZE;
    tempB[2].x = b[0].x;
    tempB[2].y = b[0].y + Block.SIZE;
    tempB[3].x = b[0].x;
    tempB[3].y = b[0].y + Block.SIZE * 2;

    updateXY(90);
  }

  @Override
  public void getRotation180() {
    /*
      □ □ □ □
     */

    tempB[0].x = b[0].x;
    tempB[0].y = b[0].y;
    tempB[1].x = b[0].x + Block.SIZE;
    tempB[1].y = b[0].y;
    tempB[2].x = b[0].x - Block.SIZE;
    tempB[2].y = b[0].y;
    tempB[3].x = b[0].x - Block.SIZE * 2;
    tempB[3].y = b[0].y;

    updateXY(180);
  }

  @Override
  public void getRotation270() {
    /*
        □
        □
        □
        □
     */

    tempB[0].x = b[0].x;
    tempB[0].y = b[0].y;
    tempB[1].x = b[0].x;
    tempB[1].y = b[0].y + Block.SIZE;
    tempB[2].x = b[0].x;
    tempB[2].y = b[0].y - Block.SIZE;
    tempB[3].x = b[0].x;
    tempB[3].y = b[0].y - Block.SIZE * 2;

    updateXY(270);
  }

}
