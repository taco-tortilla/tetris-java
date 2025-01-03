package mino;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Block extends Rectangle {

  public static final int SIZE = 30;
  public int x, y;
  public Color c;

  public Block(Color c) {
    this.c = c;
  }

  public void draw(Graphics2D g2) {
    int MARGIN = 2;
    g2.setColor(c);
    g2.fillRect(x + MARGIN, y + MARGIN, SIZE - (MARGIN * 2), SIZE - (MARGIN * 2));
  }

}
