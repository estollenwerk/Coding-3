import java.util.*;

public class Main {

    public static void main(String[] args) {
        String[] tests = {
            "3 + 4 * 2 / (1 - 5)^2^3",  // mix of operators and parentheses
            "10 + 2 * 6",               // should be 22
            "100 * (2 + 12)",           // should be 1400
            "100 * (2 + 12) / 14",      // should be 100
            "5 - 3 - 1",                // check left-associativity
            "2 ^ 3 ^ 2"                 // check right-associativity (2^(3^2) = 512)
        };

        for (String expr : tests) {
            try {
                List<String> postfix = infixToPostfix(expr);
                double result = evalPostfix(postfix);
                System.out.println(expr + " = " + result);
            } catch (Exception e) {
                System.out.println(expr + " -> Error: " + e.getMessage());
            }
        }
    }

    // Convert infix expression to postfix using Shunting Yard
    public static List<String> infixToPostfix(String expr) {
        List<String> output = new ArrayList<>();
        Stack<String> operators = new Stack<>();

        StringTokenizer tokenizer = new StringTokenizer(expr, "+-*/^() ", true);

        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken().trim();
            if (token.isEmpty()) continue;

            if (isNumber(token)) {
                output.add(token);
            } else if (isOperator(token)) {
                while (!operators.isEmpty() && isOperator(operators.peek())
                        && ((isLeftAssociative(token) && precedence(token) <= precedence(operators.peek()))
                        || (!isLeftAssociative(token) && precedence(token) < precedence(operators.peek())))) {
                    output.add(operators.pop());
                }
                operators.push(token);
            } else if (token.equals("(")) {
                operators.push(token);
            } else if (token.equals(")")) {
                while (!operators.isEmpty() && !operators.peek().equals("(")) {
                    output.add(operators.pop());
                }
                if (operators.isEmpty() || !operators.peek().equals("(")) {
                    throw new IllegalArgumentException("Mismatched parentheses");
                }
                operators.pop(); // remove "("
            } else {
                throw new IllegalArgumentException("Invalid token: " + token);
            }
        }

        while (!operators.isEmpty()) {
            String op = operators.pop();
            if (op.equals("(") || op.equals(")")) {
                throw new IllegalArgumentException("Mismatched parentheses");
            }
            output.add(op);
        }

        return output;
    }

    // Evaluate postfix expression
    public static double evalPostfix(List<String> postfix) {
        Stack<Double> stack = new Stack<>();

        for (String token : postfix) {
            if (isNumber(token)) {
                stack.push(Double.parseDouble(token));
            } else if (isOperator(token)) {
                if (stack.size() < 2) throw new IllegalArgumentException("Invalid expression");
                double b = stack.pop();
                double a = stack.pop();
                switch (token) {
                    case "+": stack.push(a + b); break;
                    case "-": stack.push(a - b); break;
                    case "*": stack.push(a * b); break;
                    case "/": stack.push(a / b); break;
                    case "^": stack.push(Math.pow(a, b)); break;
                }
            }
        }

        if (stack.size() != 1) throw new IllegalArgumentException("Invalid postfix evaluation");
        return stack.pop();
    }

    // Helpers
    private static boolean isNumber(String s) {
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean isOperator(String s) {
        return "+-*/^".contains(s);
    }

    private static int precedence(String op) {
        switch (op) {
            case "+": case "-": return 1;
            case "*": case "/": return 2;
            case "^": return 3;
            default: return -1;
        }
    }

    private static boolean isLeftAssociative(String op) {
        return !op.equals("^"); // ^ is right-associative
    }
}
