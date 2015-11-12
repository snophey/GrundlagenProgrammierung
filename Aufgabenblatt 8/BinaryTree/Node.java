abstract class Node {
  int key;

  public Node(int key) {
    this.key = key;
  }

  // returns the value associated with the key if
  // the node itself or one of its child-nodes has it
  // otherwise - null
  public abstract String get(int key);

  public void append(int key, String value) {};
}
