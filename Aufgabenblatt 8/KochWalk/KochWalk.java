import ch.unibas.informatik.cs101.ImageWindow;
import ch.unibas.informatik.cs101.Walker;

public class KochWalk {
  static Walker w;
  static ImageWindow iw;
  static final int WIDTH = 600;
  static final int HEIGHT = 600;
  static int recursion_depth;
  static double step = 60.0;

  static void draw(int level) {
    if(level == 0) {
      w.move(step);
    } else {
      draw(level-1);
      w.turn(-60.0);
      draw(level-1);
      w.turn(120);
      draw(level-1);
      w.turn(-60);
      draw(level-1);
    }
  }

  static public void main(String[] args) {
    if(args.length < 1) {
      System.out.println("Usage: java KochWalk [recursion_depth]");
      System.out.println("Example: java KochWalk 3");
      return;
    }
    recursion_depth = Integer.parseInt(args[0]);

    iw = new ImageWindow(WIDTH, HEIGHT);
    iw.openWindow("java KochWalk " + args[0]);
    w = new Walker(iw);

    w.pressBallPen();
    w.setColor(255, 0, 255);
    w.setPos(150.0, 300.0);
    w.setDir(1.0, 0.0);

    step *= 1.0/(3.0*recursion_depth);

    draw(recursion_depth);
    w.turn(120);
    draw(recursion_depth);
    w.turn(120);
    draw(recursion_depth);
    iw.redraw();
  }
}
