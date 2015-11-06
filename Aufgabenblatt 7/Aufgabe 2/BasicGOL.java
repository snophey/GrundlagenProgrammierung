public class BasicGOL {
  public static void main(String[] args) {
    if(args.length < 1) {
      System.out.println("You must specify the amount of steps.");
      System.out.println("Example: `java BasicGOL 25` for 25 evolution steps.");
      return;
    }

    int steps = Integer.parseInt(args[0]);
    GameOfLife gol = new GameOfLife();

    for(int i = 0; i < steps; ++i) {
      System.out.println(gol.toString());
      gol.update();
    }
  }
}
