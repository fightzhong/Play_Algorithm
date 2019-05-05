
public class leetcode_024_SwapNodesinPairs extends ListNode {
    public ListNode swapPairs(ListNode head) {
        // 增加一个虚拟头节点
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;
        
        ListNode pre = dummyHead;
        ListNode odd = head;
        ListNode even = head == null ? null : head.next;
        
        while (odd != null && even != null) {
            
            // 交换位置
            odd.next = even.next;
            even.next = odd;
            pre.next = even;
            pre = odd;
            
            // 维护odd, even的位置
            odd = odd.next;
            even = odd == null ? null : odd.next;
        }
        
        return  dummyHead.next;
    }  
}
