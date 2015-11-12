public class TestTree {
  static public void main(String[] args) {
    SearchTree st = new SearchTree();

    st.insert(3920, "Zermatt");
    st.insert(3215, "Gempenach");
    st.insert(4000, "Basel");
    st.insert(4312, "Magden");
    st.insert(7436, "Medels");
    st.insert(3800, "Interlaken");

    assert st.search(3800).equals("Interlaken");
    assert st.search(4312).equals("Magden");
    assert st.search(3920).equals("Zermatt");
    assert st.search(4000).equals("Basel");
    assert st.search(7436).equals("Medels");
    assert st.search(3215).equals("Gempenach");
    assert st.search(79596) == null;

    System.out.println(st.toString());
  }
}
