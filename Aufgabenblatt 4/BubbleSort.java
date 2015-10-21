import ch.unibas.informatik.cs101.ImageWindow;

class BubbleSort {
  static final int WIDTH = 500;
  static final int HEIGHT = 500;
  static final int COLUMN_STRETCH_FACTOR = 3;

  /**
   * Sorts characters and draws progress on canvas.
   **/
  public static void visualizedSort(char[] characters, ImageWindow canvas) {
    boolean swapped = false;
    for(int i = 0; i < characters.length-1; i++) {
      //if(Character.toLowerCase(characters[i]) > Character.toLowerCase(characters[i+1])) {
      if((characters[i]) > (characters[i+1])) {
        char temp = characters[i];
        characters[i] = characters[i+1];
        characters[i+1] = temp;
        swapped = true;
      }
    }

    displayArray(characters, canvas);

    if(swapped == true)
      visualizedSort(characters, canvas);
  }

  /**
   * Takes coordinates of the lower left and upper right points of the rectangle
   * using given rgb color.
   **/
  public static void drawRect(int llx, int lly, int urx, int ury,
                              int r, int g, int b, ImageWindow canvas) {
    int width = urx - llx;
    int height = lly - ury;
    for(int y = lly; y > lly-height; --y) {
      for(int x = llx; x <= llx+width; ++x) {
        canvas.setPixel(x, y, r, g, b);
      }
    }
  }

  /**
   * Visualizes an array's content in form of a column chart.
   **/
  public static void displayArray(char[] characters, ImageWindow canvas) {
    canvas.clearImage();
    int colWidth = WIDTH/characters.length;

    for(int i = 0; i < characters.length; ++i) {
      //int val = (int)Character.toLowerCase(characters[i]);
      int val = (int)characters[i];
      int height = val * COLUMN_STRETCH_FACTOR;
      int x = i * colWidth;
      int y = HEIGHT;
      int red = val;
      drawRect(x, y, x+colWidth, HEIGHT-height, red, 0, 255-red, canvas);
    }

    canvas.redraw();
    canvas.pause(10);
  }

  public static void displayArrayContent(char[] characters) {
    for(int i = 0; i < characters.length; ++i) {
      System.out.format("[%d]: '%c' (%d)\n", i, characters[i], (int)characters[i]);
    }
  }

  public static void main(String[] args) {
    if (args.length != 1) {
      System.out.println("Bitte rufen Sie das Programm mit einem Eingabewert auf");
      System.out.println("  java BubbleSort 'dies ist ein text'");
      System.exit(-1);
    }
    char[] characters = args[0].toCharArray();

    ImageWindow window = new ImageWindow(WIDTH, HEIGHT);
    window.openWindow("visualizedSort", 100, 100);

    visualizedSort(characters, window);
    //displayArrayContent(characters);
  }
}
