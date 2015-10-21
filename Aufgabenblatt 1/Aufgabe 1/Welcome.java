class Welcome {
  /*
   Ich bin ein Blockkommentar.
  */
  public static void main(String[] arg) {
    // alternativ: System.out.println("Welcome " + arg[0] + "!");
    System.out.format("Welcome %s!\n", arg[0]);
  }
}
