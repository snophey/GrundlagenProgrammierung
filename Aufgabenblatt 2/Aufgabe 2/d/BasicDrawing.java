import ch.unibas.informatik.cs101.ImageWindow;

public class BasicDrawing {

  static void drawCircle(ImageWindow canvas, int posx, int posy, int radius) {
    for(int y = -radius; y <= radius; ++y)
      for(int x = -radius; x <= radius; ++x)
        if(x*x+y*y < radius*radius)
          canvas.setPixel(posx+x, posy+y, 0, 255, 0);
        else if(x*x+y*y == radius*radius)
          canvas.setPixel(posx+x, posy+y, 255, 0, 0);
  }

  public static void main(String args[]) {
    //creates a new instance of the ImageWindow Class
    //with a viewable image area of 500x500 pixels
    ImageWindow w = new ImageWindow(500,500);

    //opens the corresponding window (makes it visible)
    w.openWindow();

    int radius = 100;
    drawCircle(w, 0, 0, radius);


    //redraws the image on the screen so all changes
    //become visible
    w.redraw();
  }
}
