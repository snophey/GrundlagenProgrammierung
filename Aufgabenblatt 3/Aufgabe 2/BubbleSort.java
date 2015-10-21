class BubbleSort {
  /**
   * Sortiert das Eingabearray und aendert das Array in place
   **/
  public static void sort(char[] characters) {
    boolean swapped = false;
    for(int i = 0; i < characters.length-1; i++) {
      if((characters[i]) > (characters[i+1])) {
        char temp = characters[i];
        characters[i] = characters[i+1];
        characters[i+1] = temp;
        swapped = true;
      }
    }

    if(swapped == true)
      sort(characters);
  }

  /**
   * Schreibt das Array auf die Ausgabekonsole
   **/
  public static void displayArray(char[] characters) {
    for(int i = 0; i < characters.length; ++i) {
      System.out.format("[%d]: '%c'\n", i, characters[i]);
    }
  }

  /**
   * Die Hauptfunktion liest das Character Array und ruft die Sortierfunktion
   * und die Ausgabefunktion auf
   **/
  public static void main(String[] args) {
    if (args.length != 1) {
      System.out.println("Bitte rufen Sie das Programm mit einem Eingabewert auf");
      System.out.println("  java BubbleSort 'dies ist ein text'");
      System.exit(-1);
    }
    char[] characters = args[0].toCharArray();

    sort(characters);

    displayArray(characters);
  }
}
