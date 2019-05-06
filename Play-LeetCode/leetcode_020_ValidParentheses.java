import java.util.Stack;

public class leetcode_020_ValidParentheses {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<Character>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            if ( c == '[' || c == '{' || c == '(' ) {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                
                char c1 = stack.peek();
                if ( ( c1 == '[' && c == ']' ) || ( c1 == '(' && c == ')' ) || ( c1 == '{' && c == '}' ) ) {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }
        
        return stack.isEmpty();
    }
}
