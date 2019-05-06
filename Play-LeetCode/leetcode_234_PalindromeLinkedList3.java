/**
     找到中间的元素, 对后面的元素进行反转, 反转后开始比较第一个元素和后面第一个元素, 然后比较第二个....
 */
// beat 95%
public class leetcode_234_PalindromeLinkedList3 {
    public static void main(String[] args) {
        ListNode head = ListNode.generateList(new int[] {1,2,2,1,3});
        new leetcode_234_PalindromeLinkedList3().isPalindrome(head);
    }
    
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;
        
        int listLen = 0;
        ListNode cur = head;
        while (cur != null) {
            cur = cur.next;
            listLen ++;
        }
        
        int rightIndex = listLen / 2; // 右边元素的开始, 左边是head作为开始
        int i = 0;
        ListNode rightNodePre = dummyHead;  // 获取到右边元素的前一个元素
        while (i <= rightIndex - 1) {
            rightNodePre = rightNodePre.next;
            i ++;
        }
        
        if (listLen % 2 != 0) { // 奇数个数, 那么listLen / 2位置上的元素的后一个元素就是右边开始的元素
            rightNodePre = rightNodePre.next;
        } 
        
        // 对右边元素进行反转
        ListNode node = null;
        cur = rightNodePre.next;
        while (cur.next != null) {
            ListNode temp = cur.next;
            cur.next = node;
            node = cur;
            cur = temp;
        }
        
        cur.next = node;
        while (cur != null) {
            if (cur.val != head.val) {
                return false;
            }
            cur = cur.next;
            head = head.next;
        }
        
        return true;
    }
}
