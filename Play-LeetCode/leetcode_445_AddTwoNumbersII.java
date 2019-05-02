/*
 * 与002题类似, 不同的是这里的相加是从右边开始的, 所以需要对这两个链表先进行反转, 然后执行002题的代码
 * 再将结果反转一次就好了
 */
public class leetcode_445_AddTwoNumbersII {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        l1 = reverseNode(l1);
        l2 = reverseNode(l2);
        
        ListNode result = addTwoNumbersI(l1, l2);
        return reverseNode(result);
    }
    
    public ListNode reverseNode (ListNode head) {
        ListNode pre = null;
        while (head != null) {
            ListNode tempNode = head;
            head = head.next;
            tempNode.next = pre;
            pre = tempNode;
        }
        
        return pre;
    }
    
    public ListNode addTwoNumbersI (ListNode l1, ListNode l2) {
        ListNode head = null;
        ListNode tail = null;
        
        int basis = 0;  // 进位, 初始为0, 当两个数相加大于10则将进位设为1, 这样下一次相加就要加上这个1了
        while (true) {
            if (l1 == null && l2 == null) {
                break;
            }
            int l1Val = l1 == null ? 0 : l1.val;
            int l2Val = l2 == null ? 0 : l2.val;
            int sum = l1Val + l2Val + basis;
            
            if (sum < 10) {
                
                if (head == null) {
                    head = new ListNode(sum);
                    tail = head;
                } else {
                    tail.next = new ListNode(sum);
                    tail = tail.next;
                }
                basis = 0;
                
            } else {
                
                if (head == null) {
                    head = new ListNode(sum - 10);
                    tail = head;
                } else {
                    tail.next = new ListNode(sum - 10);
                    tail = tail.next;
                }
                
                basis = 1;
            }
            
            l1 = l1 == null ? null : l1.next;
            l2 = l2 == null ? null : l2.next;
        }
        
        if (basis != 0) {
            tail.next = new ListNode(basis);
        }
        
        return head;
    }
    
    private static class ListNode {
        int val;
        ListNode next;
        public ListNode (int x) {
            val = x;
        }
    }
    
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
