import ch.unibas.informatik.cs101.ImageWindow;

class Turtle {
  int x, y;
  int dir; // 1000: north, 1001: east, 1002: south: 1003: west
  boolean drawingActive;
  ImageWindow canvas;

  public Turtle(ImageWindow dest) {
    canvas = dest;
    x = dest.getImageWidth()/2;
    y = dest.getImageHeight()/2;
    drawingActive = false;
    dir = 1001;
  }

  public void toggleDrawing() {
    drawingActive = !drawingActive;
  }

  public void turn(int direction) {
    dir = 1000 + ((dir + direction) % 4);
  }

  public void move(int... params) {
    int[] moveArgs = {20, 0, 0, 0}; // step, r, g, b
    for(int i = 0; i < params.length; ++i) {
      moveArgs[i] = params[i];
    }

    int xdir = 0;
    int ydir = 0;
    switch(dir % 4) {
      case 0: xdir = 0; ydir = -1; break;
      case 1: xdir = 1; ydir = 0; break;
      case 2: xdir = 0; ydir = 1; break;
      case 3: xdir = -1; ydir = 0; break;
    }

    if(drawingActive)
      for(int i = 0; i <= moveArgs[0]; ++i) {
        canvas.setPixel(x+i*xdir, y+i*ydir, moveArgs[1],moveArgs[2],moveArgs[3]);
      }

    x += moveArgs[0]*xdir;
    y += moveArgs[0]*ydir;
  }
}

public class BasicTurtle {
  static void drawShape(Turtle t, int r, int g, int b) {
    t.move(20, r, g, b);
    t.turn(1);
    t.move(20, r, g, b);
    t.turn(-1);
    t.move(20, r, g, b);
    t.turn(1);
    t.move(20, r, g, b);
    t.turn(1);
    t.move(20, r, g, b);
    t.turn(-1);
    t.move(20, r, g, b);
    t.turn(1);
    t.move(20, r, g, b);
  }

  public static void main(String args[]) {
    //creates a new instance of the ImageWindow Class
    //with a viewable image area of 500x500 pixels
    ImageWindow w = new ImageWindow(500,500);

    //opens the corresponding window (makes it visible)
    w.openWindow();

    Turtle t = new Turtle(w);
    t.move(150);
    t.toggleDrawing();
    t.move(50, 255);
    t.turn(1);
    t.move(20, 0, 255);
    t.turn(-1);
    t.move();
    t.turn(1);
    t.move(10);
    t.turn(1);
    t.move(20, 0, 0, 255);
    t.turn(-1);
    t.move(20, 100, 100, 0);
    t.turn(1);
    t.move(20, 0, 150, 230);

    int[] reds = {255, 0, 150, 200};
    Turtle t2 = new Turtle(w);
    t2.toggleDrawing();
    for(int r : reds) {
      t2.turn(-1);
      drawShape(t2, r, 0, 0);
    }

    w.redraw();
  }
}
