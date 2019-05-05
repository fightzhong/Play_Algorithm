
public class leetcode_025_ReverseNodesinkGroup extends ListNode {
    public ListNode reverseKGroup(ListNode head, int k) {
        // 计算链表中节点的个数
        ListNode curNode = head;
        int number = 0;
        while (curNode != null) {
            curNode = curNode.next;
            number++;
        }
        
        if (number == 0) {
            return null;
        } else if (number == 1) {
            return head;
        }
         
        int i = 1; // 倍数, 循环一次增加K个
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;
        ListNode pre = dummyHead;
        while (i * k <= number) {
            ListNode[] nodes = reverseList(pre.next, k);
            pre.next = nodes[0];
            pre = nodes[1];
            i++;
        }
        
       return dummyHead.next;
    }
    
    /**对以head为根的链表的前n个元素进行反转**/
    public ListNode[] reverseList (ListNode head, int n) {
        ListNode firstNode = null;      // 新链表的第一个节点
        ListNode lastNode = head;       // 临时保存一下当前链表的第一个节点, 用于反转后将该节点指向第n + 1个节点
        int i = 0; 
        while (i < n) {
           ListNode next = head.next;
           head.next = firstNode;
           firstNode = head;
           head = next;
           i++;
        }
        
        lastNode.next = head;
        
        return new ListNode[] {firstNode, lastNode};
    }
}
