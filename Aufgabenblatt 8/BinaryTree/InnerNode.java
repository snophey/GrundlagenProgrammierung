class InnerNode extends Node {
  Node left_child;
  Node right_child;

  public InnerNode(int key) {
    super(key);
    left_child = null;
    right_child = null;
  }

  public String toString() {
    String left = left_child == null? "[null]" : left_child.toString();
    String right = right_child == null? "[null]" : right_child.toString();
    return String.format("(%d)\n\tl: %s\n\tr: %s", key, left, right);
  }

  public String get(int key) {
    if(key > this.key && right_child != null)
      return right_child.get(key);
    else if(key <= this.key && left_child != null)
      return left_child.get(key);
    else
      return null;
  }

  public void append(int key, String value) {
    if(key > this.key) { // if the new element belongs to the right side of this node
      if(right_child == null) {  // simple case: right branch is free
        right_child = new Leaf(key, value); // ...assign the new element to it
      } else { // complex case: right branch is already occupied
        // two possible cases here:
        if(right_child instanceof InnerNode) { // right_child is an InnerNode
          // The new element is obviously no longer our problem.
          right_child.append(key, value); // Let right_child do the appending.
        } else { // here things get ugly
          /* We need to somehow insert a new InnerNode between the
            right_child-Leaf and its parent.
          */
          InnerNode new_node = new InnerNode(right_child.key);
          // new_node gets two new branches:
          // one holds the right_child-Leaf's data
          new_node.append(right_child.key, ((Leaf)right_child).value);
          // ...while the other one holds the new Leaf represented by key and value.
          new_node.append(key, value);
          // now that new data is in place and old data is safe: insert new node into the tree
          right_child = new_node;
        }
      }
    } else { // perform operations analogous to the ones above, but with left_child
      if(left_child == null) {
        left_child = new Leaf(key, value);
      } else {
        if(left_child instanceof InnerNode) {
          left_child.append(key, value);
        } else {
          // one small difference here: we use the new key instead of left_child.key
          // because left_child.key may actually equal this.key since left branches are always smaller OR *equal*.
          // And no two subsequent nodes may have the same key value.
          InnerNode new_node = new InnerNode(key);
          new_node.append(left_child.key, ((Leaf)left_child).value);
          new_node.append(key, value);
          left_child = new_node;
        }
      }
    } // if(key > this.key)
  } // public void append
}
