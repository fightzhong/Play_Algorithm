
public class leetcode_082_RemoveDuplicatesfromSortedListII extends ListNode {
    public static void main(String[] args) {
        int[] arr = new int[] {1,2,3,3,4,4,5};
        ListNode head = ListNode.generateList(arr);
        
        head = new leetcode_082_RemoveDuplicatesfromSortedListII().deleteDuplicates(head);
    }
    
    /**
     *  关键在于理解该递归函数的作用: 删除以head为头节点中相同的元素, 所以需要在里面处理与head值相同的情况
     *  如果不相同, 则head.next = deleteDuplicates(head.next), 对下一个节点进行该操作
     */
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }
        
        if (head.next == null) {
            return head;
        }
        
        // 如果当前节点和下一个节点相等, 那么就找到第一个不与当前节点相等的节点,然后对
        // 该节点进行递归操作, 并且将递归操作的返回值作为当前函数的返回值
        if (head.val == head.next.val) {
            ListNode temp = head;
            do {
                head = head.next;
            } while (head != null && temp.val == head.val);
            
            return deleteDuplicates(head);
        } else {
            // 如果当前节点的值和下一个节点的值不相等, 那么就直接对下一个节点进行递归操作
            head.next = deleteDuplicates(head.next);
            return head;
        }
    }
}
