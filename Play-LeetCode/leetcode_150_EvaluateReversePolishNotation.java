import java.util.Stack;

public class leetcode_150_EvaluateReversePolishNotation {
    public static void main(String[] args) {
        String[] str = new String[] {"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"};
        new leetcode_150_EvaluateReversePolishNotation().evalRPN(str);
    }
    
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<Integer>();
        int result = Integer.parseInt(tokens[0]);
        for (String str: tokens) {
            if (str.equals("+") || str.equals("-")  || str.equals("*")  || str.equals("/")) {
                int num1 = stack.pop();
                int num2 = stack.pop();
                if (str.equals("+")) {
                    result = num1 + num2;
                } else if (str.equals("-")) {
                    result = num1 - num2;
                } else if (str.equals("*")) {
                    result = num1 * num2;
                } else {
                    result = num1 / num2;
                }
                stack.push(result);
            } else {
                stack.push(Integer.parseInt(str));
            }
        }
        
        return result;
    }
}
