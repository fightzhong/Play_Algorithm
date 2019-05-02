/**
     Description:
         Reverse a singly linked list.
      
     Example:
         Input: 1->2->3->4->5->NULL
         Output: 5->4->3->2->1->NULL
 */
public class leetcode_206_ReverseLinkedList {
    // 题目规定的listNode;
    private class ListNode {
        private int val;
        private ListNode next;
        
        public ListNode (int x) {
            val = x;
        }
    }
    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        while (head != null) {
            ListNode tempNode = head;
            head = head.next;
            tempNode.next = pre;
            pre = tempNode;
        }
        
        return pre;
    }
}


