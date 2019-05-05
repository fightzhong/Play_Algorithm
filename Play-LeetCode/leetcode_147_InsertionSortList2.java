/**
      思路: 一直遍历, 维护两个变量cur, pre, 代表当前正在遍历的元素和其前一个元素, 当不满足pre < cur的时候
                从前往后查找, 找到cur元素应该插入的位置, 然后将该元素插入到该位置就好了
 */

public class leetcode_147_InsertionSortList2 extends ListNode {
    public static void main(String[] args) {
        int[] arr = new int[] {1};
//        int[] arr = new int[] {1,2,4,5,3};
        ListNode head = ListNode.generateList(arr);
        
        new leetcode_147_InsertionSortList2().insertionSortList(head);
    }
    
    public ListNode insertionSortList(ListNode head) {
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;
        
        ListNode pre = head;
        ListNode cur = head == null ? null : head.next;
        
        while (cur != null) {
            
            // 需要进行位置调换的情况
            if (pre.val > cur.val) {
                
                // 位置的交换
                // 找到cur这个节点应该放在的位置, 以及该位置的前一个元素
                ListNode[] target = getInsertedLocation(cur, dummyHead);
                ListNode leftPre = target[0];
                ListNode left = target[1];
                
                pre.next = cur.next;
                cur.next = left;
                leftPre.next = cur;
                
                // 维护cur, pre
                cur = pre.next;
            } else {
                pre = cur;
                cur = cur.next;
            }
        }
        
        return dummyHead.next;
    }
    
    /**获取target节点在链表中应该插入的位置, 以及该位置的前一个元素**/
    public ListNode[] getInsertedLocation (ListNode target, ListNode dummyHead) {
        ListNode pre = dummyHead;
        ListNode cur = dummyHead.next;
        while (cur.val <= target.val) {
            pre = cur;
            cur = cur.next;
        }
        
        // 此时这个元素就是比cur节点大的了, 也就是说cur元素最终的交换是和这个元素的, 并且要将前一个节点也返回
        return new ListNode[] {pre,cur};
    }
}
