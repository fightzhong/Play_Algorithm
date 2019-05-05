import java.util.Stack;

public class leetcode_445_AddTwoNumbersII2 extends ListNode {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        
        // 将两个链表的数据压入栈
        Stack<ListNode> stack1 = new Stack<>();
        Stack<ListNode> stack2 = new Stack<>();
        
        while (l1 != null) {
            stack1.push(l1);
            l1 = l1.next;
        }
        
        while (l2 != null) {
            stack2.push(l2);
            l2 = l2.next;
        }
        
        int basis = 0;
        int sum = 0;
        ListNode result = null;
        while (!stack1.isEmpty() && !stack2.isEmpty()) {
            l1 = stack1.pop();
            l2 = stack2.pop();
            sum = l1.val + l2.val + basis;
            
            ListNode newNode = new ListNode(sum % 10);
            newNode.next = result;
            result = newNode;
            
            basis = sum / 10;
        }
        
        if (!stack1.isEmpty()) {
            while (!stack1.isEmpty()) {
                l1 = stack1.pop();
                sum = l1.val + basis;
                ListNode newNode = new ListNode(sum % 10);
                newNode.next = result;
                result = newNode;
                basis = sum / 10;
            }
        } else if (!stack2.isEmpty()) {
            while (!stack2.isEmpty()) {
                l2 = stack2.pop();
                sum = l2.val + basis;
                ListNode newNode = new ListNode(sum % 10);
                newNode.next = result;
                result = newNode;
                basis = sum / 10;
            }
        }
        
        if (basis != 0) {
            ListNode newNode = new ListNode(basis);
            newNode.next = result;
            result = newNode;
        } 
        
        return result;
    }
}
