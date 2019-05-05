
public class leetcode_021_MergeTwoSortedLists extends ListNode {
    public static void main(String[] args) {
        ListNode l1 = ListNode.generateList(new int[] {1,2,4});
        ListNode l2 = ListNode.generateList(new int[] {1,3,4});
        
        new leetcode_021_MergeTwoSortedLists().mergeTwoLists(l1, l2);
    }
    
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(-1);
        ListNode pre = dummyHead;
        ListNode cur1 = l1;
        ListNode cur2 = l2;
        ListNode temp;
        
        while (cur1 != null && cur2 != null) {
            if (cur1.val >= cur2.val) {
                temp = cur2.next;
                pre = merge(pre, cur2);
                cur2 = temp;
            } else {
                temp = cur1.next;
                pre = merge(pre, cur1);
                cur1 = temp;
            }
        }
        
        if (cur1 == null) {
            while (cur2 != null) {
                temp = cur2.next;
                pre = merge(pre, cur2);
                cur2 = temp;
            }
        } else {
            while (cur1 != null) {
                temp = cur1.next;
                pre = merge(pre, cur1);
                cur1 = temp;
            }
        }
        
        return dummyHead.next;
    }
    
    public ListNode merge (ListNode pre, ListNode cur) {
        cur.next = pre.next;
        pre.next = cur;
        return pre.next;
    }
}
