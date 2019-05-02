import java.util.Stack;

/**
      Description:
          Given a linked list and a value x, partition it such that all nodes 
          less than x come before nodes greater than or equal to x.
     
          You should preserve the original relative order of the nodes in each of the two partitions.
          
     Example:
        Input: head = 1->4->3->2->5->2, x = 3
        Output: 1->2->2->4->3->5     
         
 */
public class leetcode_086_PartitionList {
    public static void main(String[] args) {
        int[] arr = new int[] {2,1};
        int x = 2;
        
        ListNode head = generateList(arr);
        
        head = partition(head, x);
        
        System.out.println(printList(head));
    }
    
    public static ListNode partition(ListNode head, int x) {
        ListNode curNode = head;
        Stack<ListNode> bigStack = new Stack<ListNode>();
        Stack<ListNode> littleStack = new Stack<ListNode>();
        
        while (curNode != null) {
            if (curNode.val >= x) {
                bigStack.push(curNode);
            } else {
                littleStack.push(curNode);
            }
            curNode = curNode.next;
        }
        
        head = null;
        while (!bigStack.isEmpty()) {
            ListNode node = bigStack.pop();
            node.next = head;
            head = node;
        }
        
        while (!littleStack.isEmpty()) {
            ListNode node = littleStack.pop();
            node.next = head;
            head = node;
        }
        
        return head;
    }
    
    private static class ListNode {
        int val;
        ListNode next;
        public ListNode (int x) {
            val = x;
        }
    }
    
    public static ListNode generateList (int[] arr) {
        ListNode head = null;

        for (int i = arr.length - 1; i >= 0; i--) {
            ListNode node = new ListNode(arr[i]);
            node.next = head;
            head = node;
        }
        
        return head;
    }
    
    public static String printList (ListNode head) {
        StringBuilder str = new StringBuilder();
        
        while (head != null) {
            str.append(head.val + " -> ");
            head = head.next;
        }
        
        str.append("Null");
        return str.toString();
    }
}
