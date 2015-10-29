import java.util.Random;

/**
 * Class representing a simple model for a genom.
 *
 * A genom consists of a linear sequence of amino acid represented
 * as characters. The characters used are 'A', 'C', 'G' and 'T'.
 *
 */
public class Genom {
  static final char[] ACIDS = {'A', 'C', 'G', 'T'};
  static Random randgen = new Random();

  // TODO define members
  char[] genom;

  /**
   * Constructs a genom of length len.
   *
   * @param len The length of the sequence.
   */
  Genom(int len) {
    genom = new char[len];
    randomize();
  }

  /**
   * Returns the sequence as a String.
   */
  public String toString() {
      return new String(genom);
  }

  /**
   * @param other Genom to compare with.
   * @return Returns true only if the two genoms have the same length and a fully identical sequence.
   */
  public boolean isEqual(Genom other) {
    return toString().equals(other.toString());
  }

  /**
   * Fill the sequence with a random pattern.
   */
  private void randomize() {
    for(int i = 0; i < genom.length; ++i) {
      int acid_index = randgen.nextInt(4);
      genom[i] = ACIDS[acid_index];
    }
  }

  /**
   * Translates the number 0-3 into the amio acid characters A, C, G, T.
   *
   * @param c Integer representing amino acid.
   * @return Character for amino acid.
   */
  private char getChar(int c) {
    return ACIDS[c];
  }

  /**
   * Sets a random amino acid at a random position.
   */
  public void pointMutation() {
    int index = randgen.nextInt(genom.length);
    int acid = randgen.nextInt(4);

    // make sure that the random amino acid is different from the original one
    while(ACIDS[acid] == genom[index])
      acid = randgen.nextInt(4);

    genom[index] = ACIDS[acid];
  }

  /**
   * Insert a random amino acid at a random position.
   * This could also be before the first amino acid or after the last one.
   */
  public void insertion() {
    int acid = ACIDS[randgen.nextInt(4)];
    int index = randgen.nextInt(genom.length+1);

    StringBuilder strb = new StringBuilder(new String(genom));
    strb.insert(index, acid);
    genom = strb.toString().toCharArray();
  }

  /**
   * Removes a random amino acid.
   */
  public void deletion() {
    int index = randgen.nextInt(genom.length);
    StringBuilder strb = new StringBuilder(new String(genom));
    strb.deleteCharAt(index);
    genom = strb.toString().toCharArray();
  }

}
