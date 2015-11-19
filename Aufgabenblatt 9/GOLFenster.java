import java.awt.Component;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.Timer;
import java.awt.Button;
import javax.swing.BoxLayout;


class GOLFenster extends Component {
  int update_interval = 1000; // time between updates in ms.
  int cell_size = 20; // cell width/height in px

  static final Color DEAD_COLOR = Color.gray;
  static final Color ALIVE_COLOR = Color.green;
  static final Color GRID_COLOR = Color.black;

  JFrame frame;
  GameOfLife gol;
  Timer updater;

  public GOLFenster(GameOfLife game, int interval, int size_per_cell) {
    gol = game;
    update_interval = interval;
    cell_size = size_per_cell;

    frame = new JFrame("GOLFenster");
    frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
    frame.add(this);

    initButtons();

    frame.pack();
    frame.setVisible(true);

    // reviving/killing cells by clicking on them
    this.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();

        int cellx = x/cell_size;
        int celly = y/cell_size;

        gol.grid[cellx][celly] = !gol.getCellAt(cellx, celly);

        redraw();
      }
    });
    // this makes sure that the program exits when the user closes the window
    frame.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent we) {
        System.exit(0);
      }
    });
    // update code every update_interval milliseconds
    updater = new Timer(update_interval, new ActionListener() {
      public void actionPerformed(ActionEvent ae) {
        gol.update();
        redraw();
      }
    });
  }

  private void initButtons() {
    Button start = new Button("Start");
    start.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ae) {
        updater.start();
      }
    });
    frame.add(start);

    Button pause = new Button("Pause");
    pause.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ae) {
        updater.stop();
      }
    });
    frame.add(pause);

    Button clear = new Button("Clear");
    clear.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ae) {
        gol.clear();
        redraw();
      }
    });
    frame.add(clear);

    Button randomize = new Button("Randomize");
    randomize.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ae) {
        gol.clear();
        gol.randomize();
        redraw();
      }
    });
    frame.add(randomize);
  }

  private void drawCell(Graphics g, int x, int y, boolean alive) {
    if(alive == true)
      g.setColor(ALIVE_COLOR);
    else
      g.setColor(DEAD_COLOR);

    g.fillRect(x*cell_size, y*cell_size, cell_size, cell_size);
  }

  private void drawGrid(Graphics g) {
    g.setColor(GRID_COLOR);

    for(int i = 1; i < gol.size; ++i) {
      // draw horizontal line from left to right
      g.drawLine(0, i*cell_size, cell_size*gol.size-1, i*cell_size);
      // draw vertical line from top to bottom
      g.drawLine(i*cell_size, 0, i*cell_size, cell_size*gol.size-1);
    }
  }

  public void paint(Graphics g) {
    for(int x = 0; x < gol.size; ++x) {
      for(int y = 0; y < gol.size; ++y) {
        drawCell(g, x, y, gol.getCellAt(x,y));
      }
    }
    drawGrid(g);
  }

  public Dimension getPreferredSize() {
    int dim = gol.size*cell_size;
    return new Dimension(dim, dim);
  }

  public void redraw() {
    frame.repaint();
  }

  static public void main(String[] args) {
    if(args.length != 3) {
      System.out.println("Usage: java GOLFenster grid_size update_interval cell_size");
      System.out.println("grid_size: width/height of the grid where the simulation will take place.");
      System.out.println("update_interval: amount of time in ms between evolution steps.");
      System.out.println("cell_size: width/height of a single cell.");
      return;
    }

    GameOfLife game_of_life = new GameOfLife(Integer.parseInt(args[0]));
    int interval = Integer.parseInt(args[1]);
    int cell_size = Integer.parseInt(args[2]);
    GOLFenster golf = new GOLFenster(game_of_life, interval, cell_size);
  }
}
