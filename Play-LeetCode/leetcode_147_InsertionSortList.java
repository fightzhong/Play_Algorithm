
public class leetcode_147_InsertionSortList extends ListNode {
    public static void main(String[] args) {
        int[] arr = new int[] {1};
//        int[] arr = new int[] {1,2,4,5,3};
        ListNode head = ListNode.generateList(arr);
        
        new leetcode_147_InsertionSortList().insertionSortList(head);
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
                
                while (pre.val > cur.val) {     // 1,2,3,5,4
                    // 将该节点与应该插入位置的节点进行位置的交换
                    target = swapNode(left, cur, leftPre, pre);
                    
                    // 维护cur的指向 和 左边插入位置的指针
                    cur = target[1];
                    left = target[0];
                    
                    leftPre = left;
                    left = left.next;
                }
                
            } else {
                pre = cur;
            }
            cur = cur.next;
        }
        
        return dummyHead.next;
    }
    
    /**
     *  在链表中对两个节点进行位置交换, leftPre表示左边节点的前一个节点, rightPre表示右边节点的前一个节点
     *  返回交换后的两个节点
     */
    public ListNode[] swapNode (ListNode left, ListNode right, ListNode leftPre, ListNode rightPre) {
        // 这个特殊情况: 就是相邻的两个进行交换
        if (left == rightPre) {
            ListNode temp = right.next;
            right.next = left;
            leftPre.next = right;
            left.next = temp;
        } else {
            ListNode temp = right.next;
            right.next = left.next;
            leftPre.next = right;
            rightPre.next = left;
            left.next = temp;
        }
        
        // 此时left指向的就是右边的节点
        return new ListNode[] {right, left};
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
