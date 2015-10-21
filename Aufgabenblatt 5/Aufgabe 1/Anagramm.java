import java.util.Arrays;

public class Anagramm {

  static char[] stringToCharArray(String str) {
    char[] arr = new char[str.length()];

    for(int i = 0; i < str.length(); ++i) {
      arr[i] = str.charAt(i);
    }

    return arr;
  }

  static StringBuilder removeNonLetters(String str) {
    StringBuilder buf = new StringBuilder();

    for(int i = 0; i < str.length(); ++i) {
      char c = str.charAt(i);
      if(c >= 'a' && c <= 'z')
        buf.append(c);
    }

    return buf;
  }

  static boolean areAnagrams(String first, String second) {
    first = removeNonLetters(first.toLowerCase()).toString();
    second = removeNonLetters(second.toLowerCase()).toString();

    char[] first_arr = stringToCharArray(first);
    char[] second_arr = stringToCharArray(second);

    Arrays.sort(first_arr);
    Arrays.sort(second_arr);

    first = new String(first_arr);
    second = new String(second_arr);

    return first.equals(second);
  }

  static public void main(String[] args) {
    String first = args[0];
    String second = args[1];

    if(areAnagrams(first, second))
      System.out.println("Given strings are anagrams.");
    else
      System.out.println("These strings are not anagrams.");
  }
}
