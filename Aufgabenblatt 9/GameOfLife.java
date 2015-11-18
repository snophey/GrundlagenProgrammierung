import java.util.Random;
import java.util.Arrays;

public class GameOfLife {
  int size;
  boolean[][] grid;

  public GameOfLife() {
    size = 6;
    grid = new boolean[][]{
      {false,false,false,false,false,false},
      {false,true,false,true,false,false},
      {false,false,true,true,false,false},
      {false,false,true,false,false,false},
      {false,false,false,false,false,false},
      {false,false,false,false,false,false}
    };
  }

  public GameOfLife(int size) {
    this.size = size;
    grid = new boolean[this.size][this.size];

    Random rand = new Random();
    for(int x = 0; x < size; ++x)
      for(int y = 0; y < size; ++y)
        if(rand.nextInt(10)+1 <= 3)
          grid[x][y] = true;
  }

  boolean getCellAt(int x, int y) {
    if((x >= size || x < 0) || (y >= size || y < 0)) {
      System.out.println("Invalid index.");
      return false;
    }
    return grid[x][y];
  }

  private int inbounds(int n){
    if(n < 0)
      return size-1;
    else if(n >= size)
      return 0;
    else
      return n;
  }

  int nNeighborsAlive(int x, int y) {
    int alive = 0;

    for(int i = x-1; i <= x+1; ++i)
      for(int j = y-1; j <= y+1; ++j) {
        if(x == i && y == j)
          continue;

        if(getCellAt(inbounds(i),inbounds(j)))
          ++alive;
      }

    return alive;
  }

  void clear() {
    for(int x = 0; x < size; ++x) {
      for(int y = 0; y < size; ++y) {
        grid[x][y] = false;
      }
    }
  }

  void update() {
    boolean copy[][] = new boolean[size][size];

    for(int y = 0; y < size; ++y)
      copy[y] = Arrays.copyOf(grid[y],size);

    for(int x = 0; x < size; ++x) {
      for(int y = 0; y < size; ++y) {
        if(getCellAt(x,y) == false && nNeighborsAlive(x, y) == 3)
          copy[x][y] = true;
        else if(getCellAt(x,y) == true && (nNeighborsAlive(x, y) != 2 && nNeighborsAlive(x,y) != 3))
          copy[x][y] = false;
      }
    }

    grid = copy;
  }

  public String toString() {
    StringBuilder strb = new StringBuilder();
    for(int x = 0; x < size; ++x) {
      for(int y = 0; y < size; ++y) {
        strb.append(getCellAt(x,y)? '@' : '.');
      }
      strb.append("\n");
    }
    return strb.toString();
  }
}
