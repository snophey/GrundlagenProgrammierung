import java.awt.Color;
import ch.unibas.informatik.cs101.ImageWindow;

public class Mandelbrot extends Thread {
  ColorPalette colPal;
  int start, end;
  int height;
  ImageWindow sourceWindow;

  Complex origin;
  double step;
  int max;


  public Mandelbrot(int start, int end, int _height, int pal, ImageWindow win,
                    Complex origin, double step, int max) {
    this.start = start;
    this.end = end;
    height = _height;
    sourceWindow = win;
    this.origin = origin;
    this.step = step;
    this.max = max;
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

  int fluchtgeschwindigkeit(Complex c, int max_iter) {
    for(int i = 1; i <= max_iter; ++i) {
      if(Math.sqrt(z(i,c).abs_sqr()) > 2.0) {
        return i;
      }
    }

    return max_iter;
  }

  void show_mandelbrot(Complex c_origin, double c_step, int max_iter) {
    /* Implementieren des Mandelbrot Algorithmus */
    for(int x = start; x < end; ++x) {
      for(int y = 0; y < height; ++y) {
        Complex c = new Complex(c_origin.real()+c_step*x, c_origin.imag()+c_step*y);
        int n = fluchtgeschwindigkeit(c, max_iter);
        Color col = colPal.getColor(n,c_origin);

        synchronized(this) {
          sourceWindow.setPixel(x, y, col.getRed(), col.getGreen(), col.getBlue());
          redraw();
        }
      }
    }

    synchronized(this) {
      Benchmark.finished++;
    }
  }

  public void run() {
    show_mandelbrot(origin, step, max);
  }

  public void redraw() {
    sourceWindow.redraw();
  }
}
