import java.util.Scanner;
import java.util.Stack;

public class lispPostFix {

    public static void main(String[] args) throws Exception {
        Stack<String> mainStack = new Stack<>();
        Stack<String> invertedStack = new Stack<>();
        // TODO: Make a method with non-spaced input and return spaced one.
        // Input still require every character to be spaced
        // Can have a method to put whitespace between every character
        System.out.println("Enter the Lisp Expression: ");
        Scanner expressionScanner = new Scanner(System.in);
        String input = expressionScanner.nextLine();
        Scanner sc = new Scanner(input);


        while (sc.hasNext()) {
            var ch = sc.next();
            // If char != ) push it into stack
            if (ch.charAt(0) != ')') {
                mainStack.push(ch);
            }
            // If char == ) then begin to invert the bracket section portion
            else if (ch.charAt(0) == ')') {
                while (!mainStack.peek().equals("(")) {
                    invertedStack.push(mainStack.pop());
                }
                // Popping the opening bracket out
                mainStack.pop();
                //  Calculate and push the inner bracket result back into mainStack
                try{
                    mainStack.push(calculateInnerBracket(invertedStack));
                }
                catch (IllegalArgumentException e){
                    e.printStackTrace();
                    System.exit(200);
                }

            }
        }
        sc.close();;

        System.out.println("Printing Stack 1 " + mainStack);
        System.out.println("Printing Stack 2 " + invertedStack);
    }

    public static String calculateInnerBracket(Stack<String> input) throws Exception {
        System.out.println(input);

        double result = 0; // Where result is stored before being returned
        var operator = input.pop();
        switch (operator) {
            case "+":
                if (!(input.size() < 2)) { // If operand is a more than two
                    while (!input.isEmpty()) {
                        result = Double.parseDouble(input.pop()) + result;
                    }
                }  else if (input.size() == 0){ // If  no operand given
                    result = 0;
                } else { // If only one operand present
                    result = Double.parseDouble(input.pop());
                }
                break;
            case "-":
                if (!(input.size() < 2)) { // If there's more than one operand
                    result = Double.parseDouble(input.pop());
                    while (!input.isEmpty()) {
                        result = result - (Double.parseDouble(input.pop()));
                    }
                } else if (input.size() == 1) { // Only one operand given
                    result = Double.parseDouble(input.pop());
                    result = 0 - result;
                } else{
                    throw (new IllegalArgumentException("Require at least one operand for subtraction operation"));
                }

                break;
            case "*":
                if (!input.isEmpty()) { // If stack not empty
                    result = Double.parseDouble(input.pop());
                    while (!input.isEmpty()) {
                        result = result * Double.parseDouble(input.pop());
                    }
                } else { // Empty stack do this
                    result = 1;
                }
                break;
            case "/":
                if (!(input.size() < 2)) { // If more than one operand
                    result = Double.parseDouble(input.pop());

                    while (!input.isEmpty()) {
                        result = result / Double.parseDouble(input.pop());
                    }
                } else if (input.size() == 0){ // No operand
                    throw new IllegalArgumentException("Require at least one operand for division operation");
                }
                else { // Have one operand
                    result = 1 / Double.parseDouble(input.pop());
                }
        }
        return String.valueOf(result);
    }
}