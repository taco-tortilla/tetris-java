package mino;

import java.awt.Color;

public class MinoL extends Mino {

  public MinoL() {
    super.create(Color.orange);
  }

  @Override
  public void setXY(int x, int y) {
    /*
      L mino
      □
      □
      □ □

      Represented by an array
      b[1]
      b[0]
      b[2] b[3]
     */

    b[0].x = x;
    b[0].y = y;
    b[1].x = b[0].x;
    b[1].y = b[0].y - Block.SIZE;
    b[2].x = b[0].x;
    b[2].y = b[0].y + Block.SIZE;
    b[3].x = b[0].x + Block.SIZE;
    b[3].y = b[0].y + Block.SIZE;
  }

  @Override
  public void getRotation0() {
    /*
      □
      □
      □ □
     */

    tempB[0].x = b[0].x;
    tempB[0].y = b[0].y;
    tempB[1].x = b[0].x;
    tempB[1].y = b[0].y - Block.SIZE;
    tempB[2].x = b[0].x;
    tempB[2].y = b[0].y + Block.SIZE;
    tempB[3].x = b[0].x + Block.SIZE;
    tempB[3].y = b[0].y + Block.SIZE;

    updateXY(0);
  }

  @Override
  public void getRotation90() {
    /*
      □ □ □
      □
     */

    tempB[0].x = b[0].x;
    tempB[0].y = b[0].y;
    tempB[1].x = b[0].x + Block.SIZE;
    tempB[1].y = b[0].y;
    tempB[2].x = b[0].x - Block.SIZE;
    tempB[2].y = b[0].y;
    tempB[3].x = b[0].x - Block.SIZE;
    tempB[3].y = b[0].y + Block.SIZE;

    updateXY(90);
  }

  @Override
  public void getRotation180() {
    /*
      □ □
        □
        □
     */

    tempB[0].x = b[0].x;
    tempB[0].y = b[0].y;
    tempB[1].x = b[0].x;
    tempB[1].y = b[0].y + Block.SIZE;
    tempB[2].x = b[0].x;
    tempB[2].y = b[0].y - Block.SIZE;
    tempB[3].x = b[0].x - Block.SIZE;
    tempB[3].y = b[0].y - Block.SIZE;

    updateXY(180);
  }

  @Override
  public void getRotation270() {
    /*
          □
      □ □ □
     */

    tempB[0].x = b[0].x;
    tempB[0].y = b[0].y;
    tempB[1].x = b[0].x - Block.SIZE;
    tempB[1].y = b[0].y;
    tempB[2].x = b[0].x + Block.SIZE;
    tempB[2].y = b[0].y;
    tempB[3].x = b[0].x + Block.SIZE;
    tempB[3].y = b[0].y - Block.SIZE;

    updateXY(270);
  }

}
