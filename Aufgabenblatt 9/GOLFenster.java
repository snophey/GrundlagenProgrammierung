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

import java.awt.MenuBar;
import java.awt.Menu;
import java.awt.MenuItem;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import java.io.PrintWriter;
import java.io.FileReader;
import java.io.BufferedReader;


class GOLFenster extends Component {
  int update_interval = 1000; // time between updates in ms.
  int cell_size = 20; // cell width/height in px

  Color DEAD_COLOR = Color.gray;
  Color ALIVE_COLOR = Color.green;
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

    initMenu();
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

  private void initMenu() {
    MenuBar mbar = new MenuBar();
    frame.setMenuBar(mbar);

    Menu file = new Menu("File");
      MenuItem save = new MenuItem("Save to filesystem...");
      MenuItem load = new MenuItem("Load from filesystem...");
      file.add(save);
      file.add(load);
      file.addSeparator();
      MenuItem quit = new MenuItem("Quit");
      quit.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent ae) {
          System.exit(0);
        }
      });
      file.add(quit);
      save.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent ae) {
          JFileChooser chooser = new JFileChooser();
          int status = chooser.showSaveDialog(frame);
          if(status == JFileChooser.APPROVE_OPTION) {
            String savepath = chooser.getSelectedFile().getPath();
            try {
              PrintWriter w = new PrintWriter(savepath, "ASCII");
              w.write(gol.toString());
              w.close();
            } catch(Exception ex) {
              JOptionPane.showMessageDialog(frame, "An error occured while trying to save the grid.", "Error", JOptionPane.ERROR_MESSAGE);
            }
          }
        }
      });
      load.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent ae) {
          JFileChooser chooser = new JFileChooser();
          int status = chooser.showOpenDialog(frame);
          if(status == JFileChooser.APPROVE_OPTION) {
            String path = chooser.getSelectedFile().getPath();
            try {
              BufferedReader reader = new BufferedReader(new FileReader(path));
              gol = new GameOfLife(reader);
              redraw();
            } catch(Exception ex) {
              JOptionPane.showMessageDialog(frame, "An error occured while trying to load the grid.", "Error", JOptionPane.ERROR_MESSAGE);
            }
          }
        }
      });

    Menu edit = new Menu("Edit");
      MenuItem randomize = new MenuItem("Randomize grid");
      MenuItem change_dead = new MenuItem("Recolor dead cells...");
      MenuItem change_alive = new MenuItem("Recolor living cells...");
      MenuItem change_interval = new MenuItem("Set update interval...");
      edit.add(randomize);
      edit.addSeparator();
      edit.add(change_alive);
      edit.add(change_dead);
      edit.addSeparator();
      edit.add(change_interval);
      randomize.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent ae) {
          gol.clear();
          gol.randomize();
          redraw();
        }
      });
      // this creates a new color picker dialog where the user can choose a
      // new color for the living cells
      change_alive.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent ae) {
          JColorChooser chooser = new JColorChooser(ALIVE_COLOR);
          JDialog col = JColorChooser.createDialog(frame, "Pick the new color", false, chooser,
          new ActionListener() { // if ok is pressed
            public void actionPerformed(ActionEvent e) {
              ALIVE_COLOR = chooser.getColor();
              redraw();
            }
          },
          new ActionListener() { // if cancel is pressed
            public void actionPerformed(ActionEvent e) {} // ...do nothing
          });
          col.setVisible(true);
        }
      });
      change_dead.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent ae) {
          JColorChooser chooser = new JColorChooser(DEAD_COLOR);
          JDialog col = JColorChooser.createDialog(frame, "Pick the new color", false, chooser,
          new ActionListener() { // if ok is pressed
            public void actionPerformed(ActionEvent e) {
              DEAD_COLOR = chooser.getColor();
              redraw();
            }
          },
          new ActionListener() { // if cancel is pressed
            public void actionPerformed(ActionEvent e) {} // ...do nothing
          });
          col.setVisible(true);
        }
      });
      change_interval.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent ae) {
          String str_interval = JOptionPane.showInputDialog(frame, "Amount of milliseconds between generations:\n(1000ms = 1s)");
          update_interval = Math.abs(Integer.parseInt(str_interval));
          updater.setDelay(update_interval);
        }
      });

    Menu help = new Menu("Help");
      MenuItem about = new MenuItem("About");
      help.add(about);
      about.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent ae) {
          JOptionPane.showMessageDialog(frame, "GOLFenster\nAdvanced visualization software for Conway's Game of Life\nCopyright (c) 2015 GETALIFE Ltd.");
        }
      });

    mbar.add(file);
    mbar.add(edit);
    mbar.add(help);
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
