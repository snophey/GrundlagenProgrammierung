class Leaf extends Node {
  String value;

  public Leaf(int key, String value) {
    super(key);
    this.value = value;
  }

  public String get(int key) {
    return this.key == key ? value : null;
  }

  public String toString() {
    return String.format("[%s -- %d]", value, key);
  }
}
