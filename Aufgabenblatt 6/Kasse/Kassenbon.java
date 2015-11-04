import java.util.ArrayList;

public class Kassenbon {
  static final int WIDTH = 30;
  Adresse addr;
  ArrayList<Artikel> artikel;

  public Kassenbon(Adresse addr) {
    artikel = new ArrayList<Artikel>();
    this.addr = addr;
  }

  // repeats str n times
  private String repeatStr(String str, int n) {
    return new String(new char[n]).replace("\0", str);
  }

  private String center(String source, String destination) {
    StringBuilder strb = new StringBuilder(destination);
    int start = destination.length()/2-source.length()/2;
    int end = start+source.length();
    strb.replace(start, end, source);
    return strb.toString();
  }

  String buildHead() {
    StringBuilder strb = new StringBuilder();
    strb.append("|"+repeatStr("=", WIDTH-2)+"|\n");

    strb.append(center(addr.event, "|"+repeatStr(" ", WIDTH-2)+"|\n"));
    strb.append(center(addr.host, "|"+repeatStr(" ", WIDTH-2)+"|\n"));
    strb.append(center(addr.street+" "+addr.nr, "|"+repeatStr(" ", WIDTH-2)+"|\n"));
    strb.append(center(addr.postal+" "+addr.city, "|"+repeatStr(" ", WIDTH-2)+"|\n"));


    strb.append("|"+repeatStr("=", WIDTH-2)+"|\n\n");

    return strb.toString();
  } // buildHead

  String buildPurchaseInfo(Artikel art) {
    StringBuilder strb = new StringBuilder();
    String amount_x_price = String.format("%s x  %.2f      ", art.amount, art.price);
    amount_x_price = String.format("%"+WIDTH+"s", amount_x_price);
    strb.append(amount_x_price);
    strb.replace(0, art.name.length()-1, art.name);
    strb.append("\n");
    strb.append(String.format("%"+WIDTH+".2f", art.getTotalPrice()));
    return strb.toString();
  } // buildPurchaseInfo

  double getTotalPrice() {
    double price = 0;
    for(Artikel art : artikel) {
      price += art.getTotalPrice();
    }

    return price;
  }

  String buildTotal() {
    StringBuilder strb = new StringBuilder(repeatStr("-", WIDTH));
    strb.append("\n");
    strb.append(String.format("Total %"+(WIDTH-6)+".2f", getTotalPrice()));
    strb.append("\n"+repeatStr("=", WIDTH));
    return strb.toString();
  }

  public void add(Artikel art) {
    artikel.add(art);
  }

  public void print() {
    System.out.println(buildHead());
    for(Artikel art : artikel) {
      System.out.println(buildPurchaseInfo(art));
    }
    System.out.println("\n"+buildTotal());
  }

} // Kassenbon
