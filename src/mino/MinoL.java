package mino;

import java.awt.Color;

public class MinoL extends Mino {

  public MinoL() {
    create(Color.orange);
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
      b[3] b[4]
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

}
