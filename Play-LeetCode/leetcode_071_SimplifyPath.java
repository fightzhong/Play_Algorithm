import java.util.Stack;

/**
      特殊情况: 
          /..../ ->  /..../
          /....  ->  /....
          /../   ->  /
          /.     ->  /
 
 */
public class leetcode_071_SimplifyPath {
    public static void main(String[] args) {
        String[] str = new String[] {"/home/", "/../", "/a/./b/../../c/", "/a/../../b/../c//.//", "/home/../../..", "/a//b////c/d//././/.."};
        
        System.out.println(new leetcode_071_SimplifyPath().simplifyPath(str[5]));
    }
    
    public String simplifyPath(String path) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < path.length(); i++) {
            char c = path.charAt(i);
            if (c == '/') {
                if (stack.isEmpty()) {
                    stack.push(c);
                } else if (stack.peek() == '.') {
                    
                    int pointLen = 0;
                    while (!stack.isEmpty() && stack.peek() == '.') {
                        stack.pop();
                        pointLen ++;
                    }
                    if (pointLen == 1) {
                      continue;  
                    } else if (pointLen == 2) {
                        if (stack.size() > 1) {
                            stack.pop();
                        }
                        
                        while (stack.size() > 1 && stack.peek() != '/') {
                            stack.pop();
                        }
                    } else {
                        for (int j = 0; j < pointLen; j++) {
                            stack.push('.');
                        }
                    }
                } else if (stack.peek() != '/') {
                    stack.push(c);
                }
            }  else {
                stack.push(c);
            }
        }
        
        if (stack.size() > 0 && stack.peek() == '.') {
            int pointLen = 0;
            while (!stack.isEmpty() && stack.peek() == '.') {
                stack.pop();
                pointLen ++;
            }
            
            if (pointLen == 2) {
                if (stack.size() > 1) {
                    stack.pop();
                }
                
                while (stack.size() > 1 && stack.peek() != '/') {
                    stack.pop();
                }
            } else if (pointLen > 2){
                for (int j = 0; j < pointLen; j++) {
                    stack.push('.');
                }
            }
        }
        
        if (!stack.isEmpty() && stack.peek() == '/') {
            stack.pop();
        }
        
        String str = "";
        while (stack.size() > 1) {
            str = stack.pop() + str;
        }
        
        return "/" + str;
    }
}
