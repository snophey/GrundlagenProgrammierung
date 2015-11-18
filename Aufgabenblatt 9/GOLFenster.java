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

class GOLFenster extends Component {
  static int CELL_SIZE = 20; // cell width/height in px

  static final Color DEAD_COLOR = Color.gray;
  static final Color ALIVE_COLOR = Color.green;
  static final Color GRID_COLOR = Color.black;

  JFrame frame;
  GameOfLife gol;

  public GOLFenster(GameOfLife game) {
    gol = game;
    frame = new JFrame("GOLFenster");

    frame.setSize(getPreferredSize());
    frame.add(this);
    frame.pack();
    frame.setVisible(true);

    // reviving/killing cells by clicking on them
    this.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();

        int cellx = x/CELL_SIZE;
        int celly = y/CELL_SIZE;

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
  }

  private void drawCell(Graphics g, int x, int y, boolean alive) {
    if(alive == true)
      g.setColor(ALIVE_COLOR);
    else
      g.setColor(DEAD_COLOR);

    g.fillRect(x*CELL_SIZE, y*CELL_SIZE, CELL_SIZE, CELL_SIZE);
  }

  private void drawGrid(Graphics g) {
    g.setColor(GRID_COLOR);

    for(int i = 1; i < gol.size; ++i) {
      // draw horizontal line from left to right
      g.drawLine(0, i*CELL_SIZE, CELL_SIZE*gol.size-1, i*CELL_SIZE);
      // draw vertical line from top to bottom
      g.drawLine(i*CELL_SIZE, 0, i*CELL_SIZE, CELL_SIZE*gol.size-1);
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
    int dim = gol.size*CELL_SIZE;
    return new Dimension(dim, dim);
  }

  public void redraw() {
    frame.repaint();
  }

  static public void main(String[] args) {
    GameOfLife game_of_life = new GameOfLife(20);
    GOLFenster golf = new GOLFenster(game_of_life);
  }
}
