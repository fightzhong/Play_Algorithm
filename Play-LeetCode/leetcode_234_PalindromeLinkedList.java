import java.util.LinkedList;

/**
     利用队列, 每次从前取出一个, 从后取出一个, 一旦出现不相等就是false
 */
public class leetcode_234_PalindromeLinkedList {
    public boolean isPalindrome(ListNode head) {
        // 1 2 3 3 4 3 3 2 1
        LinkedList<ListNode> queue = new LinkedList<ListNode>();
        while (head != null) {
            queue.add(head);
            head = head.next;
        }
        
        while (queue.size() > 1) {
            if (queue.removeFirst().val != queue.removeLast().val) {
                return false;
            }
        }
        
        return true;
    }
}
