import java.util.Random;

public class GirlsRatio {
  static Random gen = new Random();
  static double ratio() {
    double b = 0d, g = 0d;
    do {
      if (gen.nextInt(2) == 0) 
        b++;
      else
        g++;
    } while (g < 1);
    return g/(b+g);
  }

  public static void main(String[] args) {
    double ratios = 0d;
    int families = 1_000_000;
    for (int i = 0; i < families; i++)
      ratios += ratio();
    System.out.println(ratios/families);
  }
}

