
public class leetcode_61_RotateList {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null) {
            return null;
        }
        
        ListNode tail = head;
        // 计算链表中元素的个数
        int num = 1; // 链表中元素的个数
        while (tail.next != null) {
            tail = tail.next;
            num++;
        }
        
        k = k % num;        // 反转的次数
        
        // 第二次遍历链表, 并维护指定个数的值
        // 真正的反转, 就是将从后往前k个元素拼接到前面就好了, 所以需要维护一个targetPre, 代表这组元素的前一个元素
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;
        
        ListNode targetPre = dummyHead;
        ListNode cur = head;
        int i = 1;      // 从targetPre.next -> cur中元素的个数
        
        while (cur != null) {
            if (i > k) {
                i--;
                targetPre = targetPre.next;
            }
            
            i++;
            cur = cur.next;
        }
        
        // 从targetPre.next -> 尾部的元素就是要反转的元素
        tail.next = head;
        head = targetPre.next;
        targetPre.next = null;
        
        return head;
    }
}
