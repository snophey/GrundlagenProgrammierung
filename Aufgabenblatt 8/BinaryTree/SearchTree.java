class SearchTree {
  Node root;

  public SearchTree() {
    root = null;
  }

  public String toString() {
    if(root == null)
      return "[empty tree]";

    return root.toString();
  }

  public void insert(int key, String value) {
    if(root == null) {
      root = new Leaf(key, value);
    } else if(root instanceof Leaf) {
      Leaf temp = (Leaf)root;
      root = new InnerNode(temp.key);
      root.append(temp.key, temp.value);
      root.append(key, value);
    } else {
      root.append(key, value);
    }
  }

  public String search(int key) {
    return root.get(key);
  }
}
