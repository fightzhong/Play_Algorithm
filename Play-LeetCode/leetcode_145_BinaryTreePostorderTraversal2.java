import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class leetcode_145_BinaryTreePostorderTraversal2 {
    private static boolean printState = false; 
    private static boolean unprintState = true;
    public List<Integer> postorderTraversal(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        
        class State {
            boolean state;
            TreeNode node;

            public State(boolean state, TreeNode node) {
                this.state = state;
                this.node = node;
            }
        }
        
        Stack<State> stack = new Stack<State>();
        stack.push(new State(unprintState, root));
        
        while (!stack.isEmpty()) {
            State peek = stack.pop();
            boolean state = peek.state;
            TreeNode curNode = peek.node;
            
            if (state == printState) {
                list.add(curNode.val);
            } else {
                stack.push(new State(printState, curNode));
                
                if (curNode.right != null) {
                    stack.push(new State(unprintState, curNode.right));
                }
                
                if (curNode.left != null) {
                    stack.push(new State(unprintState, curNode.left));
                }
            }
        }
        
        return list;
    }
}
