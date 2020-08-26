public class BooleanEvaluation {
  public static void main(String[] args) {
    System.out.println("1^0|0|1 : " + countEval("1^0|0|1", false));
    System.out.println("0&0&0&1^1|0 : " + countEval("0&0&0&1^1|0", true));
  }

  static int countEval(String expr, boolean target) {
    if (expr.length() == 1) {
      char num = expr.charAt(0);
      return num == '0' && !target || num == '1' && target ? 1 : 0;
    }

    int sum = 0;
    for (int i = 1; i < expr.length(); i += 2) {
      char operator = expr.charAt(i);
      String op1 = expr.substring(0, i);
      String op2 = expr.substring(i+1, expr.length());

      switch (operator) {
        case '^':
          if (target) {
            sum += countEval(op1, true) * countEval(op2, false);
            sum += countEval(op1, false) * countEval(op2, true);
          } else {
            sum += countEval(op1, true) * countEval(op2, true);
            sum += countEval(op1, false) * countEval(op2, false);
          }
          break;
        case '|':
          if (target) {
            sum += countEval(op1, true) * countEval(op2, true);
            sum += countEval(op1, false) * countEval(op2, true);
            sum += countEval(op1, true) * countEval(op2, false);
          } else {
            sum += countEval(op1, false) * countEval(op2, false);
          }
          break;
        case '&':
          if (target) {
            sum += countEval(op1, true) * countEval(op2, true);
          } else {
            sum += countEval(op1, false) * countEval(op2, false);
            sum += countEval(op1, false) * countEval(op2, true);
            sum += countEval(op1, true) * countEval(op2, false);
          }
          break;
        default:
          throw new IllegalArgumentException();
      }
    }
    return sum;
  }
}

