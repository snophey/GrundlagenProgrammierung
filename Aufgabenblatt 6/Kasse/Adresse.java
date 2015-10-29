import java.util.ArrayList;

public class Adresse {
  private ArrayList<String> data;

  public Adresse(String... params) {
    data = new ArrayList<String>();

    for(String str : params) {
      data.add(str);
    }
  }

  public ArrayList<String> getData() {
    return data;
  }
}
