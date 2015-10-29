import java.util.Random;

/**
 * A simple GenPool.
 *
 * The genpool holds multiple genoms. It has a method to check if
 * a specific genom exists and offers a function to mutate all genoms
 * once.
 */
public class GenPool {
  Genom[] genoms;

  /**
   * Generate n genoms with a random length in the interval 1 - len.
   *
   * @param n Number of genoms in the genpool.
   * @param len Maximal length of a genom when initializing. (Minimal length is 1.)
   */
  GenPool( int n, int len ) {
    genoms = new Genom[n];
    randomize(len);
  }

  /**
   * Randomly fills the genpool.
   *
   * @param len Maximal length of a genom when initializing. (Minimal length is 1.)
   */
  private void randomize(int len) {
    Random rnd = new Random();
    for(int i = 0; i < genoms.length; ++i) {
      genoms[i] = new Genom(rnd.nextInt(len)+1);
    }
  }

  /**
   * Checks if a specific genom exists in the genpool.
   * @param other Genom to search for.
   * @return Returns true if the genom other is found in the genpool.
   */
  public boolean contains(Genom other) {
    for(Genom g : genoms) {
      if(g.isEqual(other))
        return true;
    }

    return false;
  }

  /**
   * Performs one mutation for every genom in the pool.
   */
  public void mutate() {
    Random rnd = new Random();
    for(Genom g : genoms) {
      int mutation_type = rnd.nextInt(3);
      
      if(mutation_type == 0)
    	  g.pointMutation();
      else if(mutation_type == 1 && g.toString().length() > 1)
    	  g.deletion();
      else
    	  g.insertion();
    }
  }

  /**
   * Returns all gens from the pool on a single line.
   */
  public String toString() {
    StringBuilder strb = new StringBuilder();
    for(Genom g : genoms) {
      strb.append(g.toString());
      strb.append(" ");
    }
    return strb.toString();
  }
}
