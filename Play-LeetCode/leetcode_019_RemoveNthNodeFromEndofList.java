
public class leetcode_019_RemoveNthNodeFromEndofList {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        int num = 1;
        ListNode cur = head;
        while (cur != null) {
            cur = cur.next;
            num++;
        }
        
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;
        
        ListNode pre = dummyHead;
        cur = head;
        while (--num != n) {
            pre = cur;
            cur = cur.next;
        }
        
        // 找到了删除的元素
        pre.next = cur.next;
        return dummyHead.next;
    }
}
