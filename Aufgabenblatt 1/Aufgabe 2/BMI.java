class BMI {
  public static void main(String[] arg) {
    double size = Integer.parseInt(arg[0]) / 100.0;
    int weight = Integer.parseInt(arg[1]);

    double bmi = weight/(size*size);

    System.out.println("Ihr BMI beträgt: " + bmi);
    if(bmi < 20)
      System.out.println("Sie haben einen BMI unter 20.");
    else if(bmi > 25)
      System.out.println("Sie haben einen BMI über 25.");
    else
      System.out.println("Sie haben einen BMI zwischen 20 und 25.");
  }
}
