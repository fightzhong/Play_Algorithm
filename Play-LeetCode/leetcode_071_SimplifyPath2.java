import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 */
public class leetcode_071_SimplifyPath2 {
    public static void main(String[] args) {
        String[] str = new String[] {"/home/", "/../", "/a/./b/../../c/", "/a/../../b/../c//.//", "/home/../../..", "/a//b////c/d//././/.."};
        
        System.out.println(new leetcode_071_SimplifyPath2().simplifyPath(str[5]));
    }
    
    public String simplifyPath(String path) {
        List<String> paths = Arrays.asList(path.split("/"));
        Stack<String> stack = new Stack<>();
        
        for (String str: paths) {
            if (str.equals(".") || str.equals("")) {
                continue;
            } else if (str.equals("..")) {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else {
                stack.push(str);
            }
        }
        
        String str = "";
        int size = stack.size();
        while (!stack.isEmpty()) {
            if (size == stack.size()) {
                str = stack.pop() + str;
            } else {
                str = stack.pop() + "/" + str;
            }
        }
        
        
        return "/" + str;
    }
}
