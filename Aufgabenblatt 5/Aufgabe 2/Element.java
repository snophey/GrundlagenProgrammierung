public class Element {
  private int priority;
  private String data;

  public String getData() {
    return data;
  }

  public int getPriority() {
    return priority;
  }

  public Element(String data, int priority) {
    this.priority = priority;
    this.data = data;
  }

  public String toString() {
    return data + " (" + priority + ")";
  }
}
