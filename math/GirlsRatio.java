import java.util.Random;

public class GirlsRatio {
  static Random gen = new Random();

  static int[] family() {
    int b = 0, g = 0;
    while (g == 0) {
      if (gen.nextBoolean())
        b++;
      else
        g++;
    }
    return new int[] {b, g};
  }

  public static void main(String[] args) {
    double ratios = 0d;
    int families = 1_000_000_000;
    int boys = 0, girls = 0;
    double familyRatio = 0d;
    for (int i = 0; i < families; i++) {
      int[] res = family();
      boys += res[0];
      girls += res[1];
      familyRatio += res[1]/(double)(res[0]+res[1]);
    }
    System.out.println("Average family ratio: " + familyRatio/families);
    System.out.println("Boys: " + boys);
    System.out.println("Girls: " + girls);
    System.out.println("Global Ratio g/(b+g): " + girls/(double)(boys+girls));
  }
}

