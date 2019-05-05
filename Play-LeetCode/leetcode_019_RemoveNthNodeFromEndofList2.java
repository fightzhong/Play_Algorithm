/**
      固定一个长度为n
 */
public class leetcode_019_RemoveNthNodeFromEndofList2 {
    public ListNode removeNthFromEnd(ListNode head, int n) {
       ListNode dummyHead = new ListNode(-1);
       dummyHead.next = head;
       
       int i = 1;
       // 需要被删除的节点
       ListNode target = head;
       ListNode targetPre = dummyHead;
       
       // 当前遍历的节点
       ListNode cur = head;
       while (cur != null) {
           // 如果个数多于n个的话, 我们需要维护i的值, 同时让target往后移动一位
           if (i > n) {
               i--;
               targetPre = target;
               target = target.next;
           }
           cur = cur.next;
           i++;
       }
       
       targetPre.next = target.next;
       return dummyHead.next;
    }
}
