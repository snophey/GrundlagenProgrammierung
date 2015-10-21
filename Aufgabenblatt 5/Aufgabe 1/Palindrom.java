public class Palindrom {

  static StringBuilder removeNonLetters(String str) {
    StringBuilder buf = new StringBuilder();

    for(int i = 0; i < str.length(); ++i) {
      char c = str.charAt(i);
      if(c >= 'a' && c <= 'z')
        buf.append(c);
    }

    return buf;
  }

  static boolean isPalindrome(String input) {
    String reverse = removeNonLetters(input.toLowerCase()).reverse().toString();
    String normal = removeNonLetters(input.toLowerCase()).toString();
    return reverse.equals(normal);
  }

  static public void main(String[] args) {
    String text = args[0];

    if(isPalindrome(text))
      System.out.println("Given string is a palindrome.");
    else
      System.out.println("This string is not a palindrome.");
  }
}
