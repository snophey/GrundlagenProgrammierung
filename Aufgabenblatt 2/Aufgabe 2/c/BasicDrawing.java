import ch.unibas.informatik.cs101.ImageWindow;

public class BasicDrawing {

  static void drawRect(ImageWindow canvas, int posx, int posy, int dims, int r, int g, int b) {
    for(int y = 0; y <= dims; ++y)
        for(int x = 0; x <= dims; ++x)
          canvas.setPixel(posx+x, posy+y, r, g, b);
  }

  public static void main(String args[]) {
    //creates a new instance of the ImageWindow Class
    //with a viewable image area of 500x500 pixels
    ImageWindow w = new ImageWindow(500,500);

    //opens the corresponding window (makes it visible)
    w.openWindow();

    int squareDims = 20;
    //colors the pixel at position 255,100 in the image
    //with the rgb color 254 (red), 0 (green), 0 (blue)
    for(int y = 0; y <= 500 / squareDims; ++y) {
      for(int x = 0; x <= 500 / squareDims; ++x) {
        int r, g, b;

        if((x+y) % 2 == 0)
          r = g = b = 0;
        else
          r = g = b = 255;

        drawRect(w, squareDims*x, squareDims*y, squareDims, r,g,b);
      }
    }

    //redraws the image on the screen so all changes
    //become visible
    w.redraw();
  }
}
