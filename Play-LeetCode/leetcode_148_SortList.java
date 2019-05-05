/**
 * 
 * 归并排序
 */
public class leetcode_148_SortList {
    public static void main(String[] args) {
        int[] arr = new int[] {};
        ListNode head = ListNode.generateList(arr);
        
        new leetcode_148_SortList().sortList(head);
    }
    
    public ListNode sortList(ListNode head) {
        
        // 找到右边的节点
        ListNode right = head;
        while (right.next != null) {
            right = right.next;
        }
        
        return mergeSort(head, right);
    }
    
    /**链表的left到height使用归并排序**/
    public ListNode mergeSort (ListNode left, ListNode right) {
        right.next = null;
        
        if (left == right) {
            return right;
        }

        // 找到中心点
        ListNode l = left;
        ListNode r = right;
        int num = 1;
        while (l != right) {
            l = l.next;
            num++;
        }
        
        int middle = num / 2;
        
        // 将链表分成两段
        l = left;
        r = right;
        int curNum = 1;
        while (curNum != middle) {
            l = l.next;
            curNum++;
        }
        
        // 左边一段为left, l, 右边一段为l.next , right;
        ListNode lNext = l.next;
        ListNode node1 = mergeSort(left, l);
        ListNode node2 = mergeSort(lNext, right);
        
        // 归并完成后要将新的链表的尾部指向空
        return merge(node1, node2);
    }
    
    public ListNode merge (ListNode node1, ListNode node2) {
        
        ListNode tail = null;
        ListNode head = null;
        
        while (node1 != null && node2 != null) {
            if (node1.val <= node2.val) {
                ListNode next = node1.next;
                node1.next = null;
                if (tail == null) {
                    head = node1;
                } else {
                    tail.next = node1;
                }
                tail = node1;
                node1 = next;
            } else {
                ListNode next = node2.next;
                node2.next = null;
                if (tail == null) {
                    head = node2;
                } else {
                    tail.next = node2;
                }
                tail = node2;
                node2 = next;
            }
        }
        
        if (node1 == null) {
            while (node2 != null) {
                tail.next = node2;
                tail = node2;
                node2 = node2.next;
            }
        } else {
            while (node1 != null) {
                tail.next = node1;
                tail = node1;
                node1 = node1.next;
            }
        }
        
        return head;
    }
}
