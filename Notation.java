public class Notation {

  private static NotationQueue<String> nQueue;
  private static NotationStack<String> nStack;
  private final static String OPS = "+-*/";

   
  private static String stackTop() {
    try {
      return nStack.top();
    } catch (StackUnderflowException e) {
      e.getMessage();
    }
    return null;
  }

  private static boolean stackPush(String c) {
    try {
      return nStack.push(c);
    } catch (StackOverflowException e) {
      e.getMessage();
    }
    return false;
  }

   
  private static boolean enqueue(String c) {
    try {
      return nQueue.enqueue(c);
    } catch (QueueOverflowException e) {
      e.getMessage();
    }
    return false;
  }

  private static String applyOperator(String first, String second, char operator)
	      throws InvalidNotationFormatException {
	    double a = Double.parseDouble(first);
	    double b = Double.parseDouble(second);
	    switch (operator) {
	      case '+':
	        return Double.toString(a + b);
	      case '-':
	        return Double.toString(a - b);
	      case '*':
	        return Double.toString(a * b);
	      case '/':
	        if (b == 0)
	          throw new InvalidNotationFormatException();
	        return Double.toString(a / b);
	    }
	    return null;
	  }
  
  private static String stackPop() {
	    try {
	      return nStack.pop();
	    } catch (StackUnderflowException e) {
	      e.getMessage();
	    }
	    return null;
	  }

  
  private static String dequeue() {
    try {
      return nQueue.dequeue();
    } catch (QueueUnderflowException e) {
      e.getMessage();
    }
    return null;
  }

   
  private static int calculatePrec(char c) {
    if (c == '*' || c == '/') {
      return 1;
    } else if (c == '+' || c == '-') {
      return 0;
    }
    return -1;
  }

  public static String convertInfixToPostfix(String complexInfix)
      throws InvalidNotationFormatException {
    nQueue = new NotationQueue<String>();
    nStack = new NotationStack<String>();

    for (int i = 0; i < complexInfix.length(); i++) {
      char c = complexInfix.charAt(i);
      if (c == ' ') {
        continue;
      } else if (Character.isDigit(c)) {
        enqueue(Character.toString(c));
      } else if (c == '(') {
        stackPush(Character.toString(c));
      } else if (OPS.indexOf(c) >= 0) {
        while (!nStack.isEmpty() && calculatePrec(stackTop().charAt(0)) >= calculatePrec(c)) {
          enqueue(stackPop());
        }
        stackPush(Character.toString(c));
      } else if (c == ')') {
        char top = stackPop().charAt(0);
        while (top != '(') {
          enqueue(Character.toString(top));
          if (nStack.isEmpty()) {
            throw new InvalidNotationFormatException();
          } else {
            top = stackPop().charAt(0);
          }
        }
      }
    }
    while (!nStack.isEmpty()) {
      enqueue(stackPop());
    }
    return nQueue.toString();
  }

   
  public static String convertPostfixToInfix(String complexPostfix)
      throws InvalidNotationFormatException {
    nStack = new NotationStack<String>();
    for (int i = 0; i < complexPostfix.length(); i++) {
      char co = complexPostfix.charAt(i);
      if (co == ' ') {
        continue;
      } else if (Character.isDigit(co)) {
        stackPush(Character.toString(co));
      } else if (OPS.indexOf(co) >= 0) {
        String a = stackPop().toString(), b, tmp;
        if (nStack.isEmpty()) {
          throw new InvalidNotationFormatException();
        } else {
          b = stackPop().toString();
          tmp = '(' + b + co + a + ')';
          stackPush(tmp);
        }
      }
    }
    if (nStack.size() != 1) {
      throw new InvalidNotationFormatException();
    }
    return stackPop();
  }

   
  public static double evaluatePostfixExpression(String complexPostfix)
      throws InvalidNotationFormatException {
    nStack = new NotationStack<String>();
    for (int i = 0; i < complexPostfix.length(); i++) {
      char cur = complexPostfix.charAt(i);
      if (cur == ' ') {
        continue;
      } else if (Character.isDigit(cur) || cur == '(') {
        stackPush(Character.toString(cur));
      } else if (OPS.indexOf(cur) >= 0) {
        String a = stackPop().toString(), b;
        String result;
        if (nStack.isEmpty()) {
          throw new InvalidNotationFormatException();
        } else {
          b = stackPop().toString();
          result = applyOperator(b, a, cur);
          stackPush(result);
        }
      }
    }
    if (nStack.size() != 1) {
      throw new InvalidNotationFormatException();
    }
    return Double.parseDouble(stackPop());
  }

  
  public static double evaluateInfixExpression(String infixExpr) throws InvalidNotationFormatException {
    String postfixExpression = convertInfixToPostfix(infixExpr);
    return evaluatePostfixExpression(postfixExpression);
  }

}