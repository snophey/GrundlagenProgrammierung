public class Test {
  public static void main( String[] args ) {
      System.out.println("Start test of PriorityQueue...");
      PriorityQueue pq = new PriorityQueue();

      assert pq.length() == 0: "pq.length() should be 0 at the beginning";

      Element e1 = new Element("Hello", 10);
      Element e2 = new Element("World", 7);
      Element e3 = new Element("Roses", 9);
      Element e4 = new Element("are red", 25);
      Element e5 = new Element("Violets", 4);

      pq.put(e1);
      pq.put(e2);
      pq.put(e3);
      pq.put(e4);
      pq.put(e5);

      assert pq.length() == 5: "pq.length() should be 5 now";

      assert pq.get().getData().equals("are red"): "pq.get() should return 'are red'";
      assert pq.get().getData().equals("Hello"): "pq.get() should be 'Hello' now";
  }
}
