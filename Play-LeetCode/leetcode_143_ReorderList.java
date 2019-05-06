import java.util.LinkedList;

/**
     1->2->3                ==> 1->3->2
     1->2->3->4             ==> 1->4->2->3 
     1->2->3->4->5          ==> 1->5->2->4->3
     1->2->3->4->5->6       ==> 1->6->2->5->3->4
     1->2->3->4->5->6->7    ==> 1->7->2->6->3->5->4
         元素个数和从后取得元素个数的关系
     1 -> 0     
     2 -> 0
     3 -> 1
     4 -> 1
     5 -> 2
     6 -> 2
     7 -> 3
     8 -> 3
     
     所以从后取得元素的个数 = (元素个数 - 1) / 2
 */
public class leetcode_143_ReorderList {
    public static void main(String[] args) {
        ListNode head = ListNode.generateList(new int[] {1, 2, 3, 4, 5});
        new leetcode_143_ReorderList().reorderList(head);
    }
    
    public void reorderList(ListNode head) {
        // 将链表的每一个元素放入队列中
        LinkedList<ListNode> queue = new LinkedList<ListNode>();
        ListNode cur = head == null ? null : head.next;        
        while (cur != null) {
            queue.addLast(cur);
            cur = cur.next;
        }
       
        // 初始tail指向head, 然后从链表中开始取出元素, 先从后面取一个, 再从前面取一个
        ListNode tail = head;
        while (queue.size() >= 2) {
            tail.next = queue.removeLast();
            tail = tail.next;
            tail.next = queue.removeFirst();
            tail = tail.next;
        }
        
        if (queue.size() == 1) {
            tail.next = queue.removeLast();
            tail.next.next = null;
        } else if (tail != null){
            tail.next = null;
        }
    }    
}
