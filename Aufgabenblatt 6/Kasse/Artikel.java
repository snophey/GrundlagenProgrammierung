public class Artikel {
  String name;
  int amount;
  double price;

  public Artikel(String name, int amount, double price) {
    this.name = name;
    this.amount = amount;
    this.price = price;
  }

  public double getTotalPrice() {
    return amount*price;
  }
}
