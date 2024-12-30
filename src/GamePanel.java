import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {

  public static final int WIDTH = 1280;
  public static final int HEIGHT = 720;
  final int FPS = 60;
  Thread gameThread;

  public GamePanel() {
    this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
    this.setBackground(Color.DARK_GRAY);
    this.setLayout(null);
  }

  public void Init() {
    gameThread = new Thread(this);
    gameThread.start();
  }


  @Override
  public void run() {
    System.out.println("test");

    // Game loop
    double drawInterval = (double) 1000000000 / FPS;
    double delta = 0;
    long lastTime = System.nanoTime();
    long currentTime;

    while (gameThread != null) {
      currentTime = System.nanoTime();

      delta += (currentTime - lastTime) / drawInterval;
      lastTime = currentTime;

      if (delta >= 1) {
        update();
        repaint();
        delta--;
      }
    }

  }

  private void update() {

  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);

  }
}
