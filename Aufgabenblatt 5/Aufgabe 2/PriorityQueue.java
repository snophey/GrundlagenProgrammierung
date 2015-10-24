public class PriorityQueue {
  static final int SIZE = 32;
  Element[] q = new Element[SIZE];
  int len = 0;

  // Insert an element into the queue according to its priority
  void put(Element x) {

    len++;
  }

  // Return the element with the highest priority from the queue
  Element get() {
    len--;
  }

  // Return the queue length
  int length() {
    return len;
  }

  // Print the queue contents
  public String toString() {
    StringBuilder strb = new StringBuilder();
    for(Element e : q) {
      strb.append(e.toString());
      strb.append("\n");
    }
    return strb.toString();
  }
}
