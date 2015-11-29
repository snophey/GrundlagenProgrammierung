import ch.unibas.informatik.cs101.ImageWindow;

public class Benchmark {
  static public int finished = 0;

  static public void main(String[] args) {
    if(args.length != 3) {
      System.out.println("Usage: java Mandelbrot PALETTE FACTOR THREADS");
      System.out.println("PALETTE: either 0 or 1");
      System.out.println("FACTOR: determines screen width and height.");
      System.out.println("THREADS: amount of threads to be used for computations.");
      System.out.println("e.g. if FACTOR is 5 then screen width/height will be 5*64.");
      System.out.println("Example: java Mandelbrot 1 5");
      return;
    }

    int palette = Integer.parseInt(args[0]);
    double fact = Double.parseDouble(args[1]);
    int n_threads = Integer.parseInt(args[2]);
    int width = (int) Math.round(64 * fact);
    int height = (int) Math.round(48 * fact);

    ImageWindow sourceWindow = new ImageWindow(width, height);
    sourceWindow.openWindow("mandelbrot",0,0);

    Mandelbrot[] threads = new Mandelbrot[n_threads];
    int width_per_thread = width/n_threads; // width of the region that is to be processed by a single thread.
    long starttime = System.currentTimeMillis();
    for(int i = 0; i < n_threads; ++i) {
      int start = i*width_per_thread;
      int end = start+width_per_thread;
      threads[i] = new Mandelbrot(start, end, height, palette, sourceWindow,
                          new Complex(-2.5, -1.3), 0.05/fact, 1000);
      threads[i].start();
    }

    for(int i = 0; i < n_threads; ++i) {
      try {
        threads[i].join();
      } catch(Exception ex) {}
    }

    double endtime = (System.currentTimeMillis()-starttime)/1000.0;
    System.out.format("%d,%.3f\n", n_threads, endtime);
    System.exit(0);
  }
}
