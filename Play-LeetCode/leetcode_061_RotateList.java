/**
     1 -> 2 -> 3 -> 4:
            k = 2:  3 -> 4 -> 1 -> 2
            k = 3:  2 -> 3 -> 4 -> 1
            k = 4:  1 -> 2 -> 3 -> 4
     由此可得, 链表的个数是4个, 那么我旋转4次就会回到初始情况, 旋转一次就从后往前选中一个元素将其拼接到前面
    旋转两次就选中两个, 所以需要对k取模于链表的长度, 才是其真正旋转的次数 
   
 */
public class leetcode_061_RotateList {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null) {
            return null;
        }
        
        // 计算链表的长度, 并获取尾节点
        int listLen = 1;
        ListNode cur = head;
        ListNode tail = null;
        while (cur.next != null) {
            cur = cur.next;
            listLen ++;
        }
        tail = cur; 
        
        int rotateLen = k % listLen;
        if (rotateLen == 0) {
            return head;
        } else {
            // 从后往前选中第rotatelen个元素, 这里运用链表长度值减减的方式获取, 当前链表长度代表倒数第x个元素....
            ListNode dummyHead = new ListNode(-1);
            dummyHead.next = head;
            ListNode pre = dummyHead;
            cur = head;
            while (listLen != rotateLen) {
                listLen --;
                pre = cur;
                cur = cur.next;
            }
            
            // 开始维护位置
            tail.next = head;
            dummyHead.next = cur;
            pre.next = null;
            return dummyHead.next;
        }
    }
}
