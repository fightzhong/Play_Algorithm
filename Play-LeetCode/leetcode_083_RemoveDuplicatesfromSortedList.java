/**
      Description:
          Given a sorted linked list, delete all duplicates such that each element appear only once.
      
      Example 1:
        Input: 1->1->2
        Output: 1->2
      
      Example 2:
        Input: 1->1->2->3->3
        Output: 1->2->3    
 */
public class leetcode_083_RemoveDuplicatesfromSortedList {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode curNode = head;
        while (curNode != null) {
            if (curNode.next != null && curNode.next.val == curNode.val) {
                curNode.next = curNode.next.next;
            } else {
                curNode = curNode.next;
            }
        }
        
        return head;
    }
    
    private class ListNode {
        int val;
        ListNode next;
        public ListNode (int x) {
            val = x;
        }
    }
    
    public ListNode generateList (int[] arr) {
        ListNode head = null;
        
        for (int i: arr) {
            ListNode node = new ListNode(i);
            node.next = head;
            head = node;
        }
        
        return head;
    }
    
    public String printList (ListNode head) {
        StringBuilder str = new StringBuilder();
        
        while (head != null) {
            str.append(head.val + " -> ");
        }
        
        str.append("Null");
        return str.toString();
    }
}
