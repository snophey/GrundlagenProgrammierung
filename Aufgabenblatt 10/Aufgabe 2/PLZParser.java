import java.io.File;
import java.io.FileReader;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Vector;
import java.util.regex.Pattern;

public class PLZParser {
  static HashMap<String,Vector<String>> data = new HashMap();
  static String filename = "plz.txt";

  static void parseData(String raw_data) {
    Scanner parser = null;
    try {
      parser = new Scanner(raw_data);
    } catch(Exception ex) {
      System.err.println("FATAL ERROR: Could not create parser.");
      System.exit(1);
    }

    parser.next(); parser.next();
    String postal = parser.next();
    String area = parser.findInLine("[A-Za-z]+.*$");

    if(!data.containsKey(postal)) {
      data.put(postal, new Vector<String>());
    }

    data.get(postal).add(area);
  }

  static void readFile() {
    java.io.BufferedReader fr = null;
    try {
      fr = new java.io.BufferedReader(new FileReader(new File(filename)));
    } catch(java.io.FileNotFoundException ex) {
      System.err.println("FATAL ERROR: File not found.");
      System.exit(1);
    }

    try {
      String line;
      while((line = fr.readLine()) != null)
        parseData(line);
    } catch(java.io.IOException ex) {
      System.err.println(ex.toString());
    }
  }

  static void handleInput() {
    Pattern validator = Pattern.compile("^[0-9]{4}$|^q$");
    Scanner reader = new Scanner(System.in);
    String input = "";

    while (true) {
      System.out.print("> ");
      input = reader.nextLine().trim().toLowerCase();

      if (!validator.matcher(input).matches()) {
        System.out.println("Invalid input.");
        continue;
      }

      if (input.equals("q"))
        break;

      if (!data.containsKey(input)) {
        System.out.println("Key not found.");
        continue;
      } else {
        Vector<String> container = data.get(input);
        for (String s : container)
          System.out.println(s);
      }
    }
  }

  static public void main(String[] args) {
    readFile();
    handleInput();
  }
}
