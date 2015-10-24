public class PriorityQueue {
  static final int SIZE = 32;
  Element[] q = new Element[SIZE];
  int len = 0;

  // Insert an element into the queue according to its priority
  void put(Element x) {
    if(len == SIZE) {
      System.out.println("overflow");
      return;
    }

    if(len == 0) {
      q[0] = x;
      len++;
      return;
    }


    // index of the first element with higher priority
    int hp_index = -1;
    for(int i = 0; i < len; ++i) {
      if(q[i].getPriority() > x.getPriority()) {
        hp_index = i;
        break;
      }
    }
    len++;

    if(hp_index == -1) {
      q[len-1] = x;
    } else {
      rshift(x, hp_index);
    }
  }

  // Inserts new_elem at dest and shifts all elements that follow to the right
  private void rshift(Element new_elem, int dest) {
    Element old_elem = q[dest];
    q[dest] = new_elem;

    if(old_elem != null)
      rshift(old_elem, dest+1);
  }

  // Return the element with the highest priority from the queue
  Element get() {
    if(len == 0) {
      System.out.println("underflow");
      return null;
    }

    Element elem = q[len-1];
    q[--len] = null;
    return elem;
  }

  // Return the queue length
  int length() {
    return len;
  }

  // Print the queue contents
  public String toString() {
    StringBuilder strb = new StringBuilder("");
    for(int i = 0; i < len; ++i) {
      strb.append(q[i].toString());
      strb.append("\n");
    }
    return strb.toString();
  }
}
