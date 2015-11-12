import ch.unibas.informatik.cs101.ImageWindow;
import java.awt.Color;

public class Mandelbrot {

  ColorPalette colPal;
  int width;
  int height;
  ImageWindow sourceWindow;


  public static void main(String[] args) {
    if(args.length != 2) {
      System.out.println("Usage: java Mandelbrot PALETTE FACTOR");
      System.out.println("PALETTE: either 0 or 1");
      System.out.println("FACTOR: determines screen width and height.");
      System.out.println("e.g. if FACTOR is 5 then screen width/height will be 5*64.");
      System.out.println("Example: java Mandelbrot 1 5");
      return;
    }

    int palette = Integer.parseInt(args[0]);
    double fact = Double.parseDouble(args[1]);
    int width = (int) Math.round(64 * fact);
    int height = (int) Math.round(48 * fact);

    Mandelbrot m1 = new Mandelbrot( width, height, palette);
    Mandelbrot m2 = new Mandelbrot( width, height, palette);


    long starttime = System.currentTimeMillis();
    m1.show_mandelbrot(new Complex(-2.5, -1.3), 0.05/fact, 1000);
    m2.show_mandelbrot(new Complex(-0.755, -0.1), 0.0002/fact, 1000);
    System.out.println("Without inplace: " + (System.currentTimeMillis()-starttime));

    m1.reset();
    m2.reset();

    starttime = System.currentTimeMillis();
    m1.show_mandelbrot_inplace(new Complex(-2.5, -1.3), 0.05/fact, 255);
    m2.show_mandelbrot_inplace(new Complex(-0.755, -0.1), 0.0002/fact, 1000);
    System.out.println("With inplace: " + (System.currentTimeMillis()-starttime));
  }


  public Mandelbrot(int _width, int _height, int pal) {
    width = _width;
    height = _height;
    sourceWindow= new ImageWindow(width,height);
    sourceWindow.openWindow("mandelbrot",0,0);
    colPal = new ColorPalette(pal);
  }

  Complex z(int n, Complex c) {
    if(n == 0)
      return new Complex(0, 0);
    else
      return z(n-1, c).sqr().add(c);
  }

  public void reset() {
    sourceWindow.clearImage();
  }

  Complex z_inplace(int n, Complex c) {
    if(n == 0)
      return new Complex(0, 0);
    else
      return z(n-1, c).sqr_inplace().add_inplace(c);
  }

  int fluchtgeschwindigkeit(Complex c, int max_iter) {
    for(int i = 1; i <= max_iter; ++i) {
      if(Math.sqrt(z(i,c).abs_sqr()) > 2.0) {
        return i;
      }
    }

    return max_iter;
  }

  int fluchtgeschwindigkeit_inplace(Complex c, int max_iter) {
    for(int i = 1; i <= max_iter; ++i) {
      if(Math.sqrt(z_inplace(i,c).abs_sqr()) > 2.0) {
        return i;
      }
    }

    return max_iter;
  }

  void show_mandelbrot(Complex c_origin, double c_step, int max_iter) {
    /* Implementieren des Mandelbrot Algorithmus */
    for(int x = 0; x < width; ++x) {
      for(int y = 0; y < height; ++y) {
        Complex c = new Complex(c_origin.real()+c_step*x, c_origin.imag()+c_step*y);
        int n = fluchtgeschwindigkeit(c, max_iter);
        Color col = colPal.getColor(n,c_origin);
        sourceWindow.setPixel(x, y, col.getRed(), col.getGreen(), col.getBlue());

        redraw();
      }
    }
  }

  void show_mandelbrot_inplace(Complex c_origin, double c_step, int max_iter) {
    /* Implementieren des Mandelbrot Algorithmus */
    for(int x = 0; x < width; ++x) {
      for(int y = 0; y < height; ++y) {
        Complex c = new Complex(c_origin.real()+c_step*x, c_origin.imag()+c_step*y);
        int n = fluchtgeschwindigkeit_inplace(c, max_iter);
        Color col = colPal.getColor(n,c_origin);
        sourceWindow.setPixel(x, y, col.getRed(), col.getGreen(), col.getBlue());

        redraw();
      }
    }
  }

  public void redraw() {
    sourceWindow.redraw();
  }

}
