/**
      思路同086题类似, 都是将整个链表拆分成两段, 最后合并这两段
 
 */
public class leetcode_328_OddEvenLinkedList {
    public ListNode oddEvenList(ListNode head) {
        ListNode oddFirst = null;
        ListNode oddLast = null;
        ListNode evenFirst = null;
        ListNode evenLast = null;
        
        ListNode cur = head;
        int index = 1;
        while (cur != null) {
            ListNode tempNode = cur.next;
            
            // 基数的情况
            if (index % 2 != 0) {
                
                if (oddFirst == null) {
                    cur.next = null;
                    oddFirst = cur;
                } else {
                    cur.next = oddLast.next;
                    oddLast.next = cur;
                }
                oddLast = cur;
                
            } else { // 偶数的情况
                
                if (evenFirst == null) {
                    cur.next = null;
                    evenFirst = cur;
                } else {
                    cur.next = evenLast.next;
                    evenLast.next = cur;
                }
                evenLast = cur;
                
            }
            
            cur = tempNode;
            index++;
        }
        
        if (oddLast != null) {
            oddLast.next = evenFirst;
        }
        return oddFirst;
    }
    
    private static class ListNode {
        int val;
        ListNode next;
        public ListNode (int x) {
            val = x;
        }
    }
    
    // 用于测试链表的方法
    public static ListNode generateList (int[] arr) {
        ListNode head = null;

        for (int i = arr.length - 1; i >= 0; i--) {
            ListNode node = new ListNode(arr[i]);
            node.next = head;
            head = node;
        }
        
        return head;
    }
    
    public static String printList (ListNode head) {
        StringBuilder str = new StringBuilder();
        
        while (head != null) {
            str.append(head.val + " -> ");
            head = head.next;
        }
        
        str.append("Null");
        return str.toString();
    }
}
