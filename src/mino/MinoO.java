package mino;

import java.awt.Color;

public class MinoO extends Mino {

  public MinoO() {
    super.create(Color.yellow);
  }

  @Override
  public void setXY(int x, int y) {
    /*
      O mino
      □ □
      □ □

      Represented by an array
      b[0] b[1]
      b[2] b[3]
     */

    b[0].x = x;
    b[0].y = y;
    b[1].x = b[0].x + Block.SIZE;
    b[1].y = b[0].y;
    b[2].x = b[0].x;
    b[2].y = b[0].y + Block.SIZE;
    b[3].x = b[0].x + Block.SIZE;
    b[3].y = b[0].y + Block.SIZE;

  }

}
