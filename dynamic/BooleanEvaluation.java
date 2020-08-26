import java.util.Map;
import java.util.HashMap;

public class BooleanEvaluation {
  public static void main(String[] args) {
    System.out.println("1^0|0|1 : " + countEval("1^0|0|1", false));
    System.out.println("0&0&0&1^1|0 : " + countEval("0&0&0&1^1|0", true));
    System.out.println("1^0|0|1|1^0|0|1 : " + countEval("1^0|0|1|1^0|0|1", false));
    System.out.println("Cache count: " + cacheCount);
    System.out.println("Calls count: " + callsCount);
  }

  static int countEval(String expr, boolean target) {
    Map<Boolean, Map<String, Integer>> cache = new HashMap<>();
    return countEval(expr, target, cache);
  }

  static int callsCount = 0;
  static int cacheCount = 0;
  static int countEval(String expr, boolean target, Map<Boolean, Map<String, Integer>> cache) {
    callsCount++;
    if (cache.containsKey(target) && cache.get(target).containsKey(expr)) {
      cacheCount++;
      return cache.get(target).get(expr);
    }
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
            sum += countEval(op1, true, cache) * countEval(op2, false, cache);
            sum += countEval(op1, false, cache) * countEval(op2, true, cache);
          } else {
            sum += countEval(op1, true, cache) * countEval(op2, true, cache);
            sum += countEval(op1, false, cache) * countEval(op2, false, cache);
          }
          break;
        case '|':
          if (target) {
            sum += countEval(op1, true, cache) * countEval(op2, true, cache);
            sum += countEval(op1, false, cache) * countEval(op2, true, cache);
            sum += countEval(op1, true, cache) * countEval(op2, false, cache);
          } else {
            sum += countEval(op1, false, cache) * countEval(op2, false, cache);
          }
          break;
        case '&':
          if (target) {
            sum += countEval(op1, true, cache) * countEval(op2, true, cache);
          } else {
            sum += countEval(op1, false, cache) * countEval(op2, false, cache);
            sum += countEval(op1, false, cache) * countEval(op2, true, cache);
            sum += countEval(op1, true, cache) * countEval(op2, false, cache);
          }
          break;
        default:
          throw new IllegalArgumentException();
      }
    }

    cache.computeIfAbsent(target, k -> new HashMap<>()).put(expr, sum);
    return cache.get(target).get(expr);
  }
}

