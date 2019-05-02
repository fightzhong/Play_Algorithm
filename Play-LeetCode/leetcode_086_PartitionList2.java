import java.util.Stack;

/**
      Description:
          Given a linked list and a value x, partition it such that all nodes 
          less than x come before nodes greater than or equal to x.
     
          You should preserve the original relative order of the nodes in each of the two partitions.
          
     Example:
        Input: head = 1->4->3->2->5->2, x = 3
        Output: 1->2->2->4->3->5     
     
     思路: (O(n))时间复杂度!!!
         我们将整个链表拆分成两段, 一段是小于x的, 一段是大于等于x的, 最后将这两段链表拼接成一个链表
          就是将小段链表的最后一个节点指向大段链表的第一个节点
 */
public class leetcode_086_PartitionList2 {
    public static void main(String[] args) {
        int[] arr = new int[] {1,4,3,2,5,2};
        int x = 3;
        
        ListNode head = generateList(arr);
        
        head = partition(head, x);
        
        System.out.println(printList(head));
    }
    
    public static ListNode partition(ListNode head, int x) {
        ListNode firstBig = null;        // 大段链表的第一个元素, 用于被小段链表拼接
        ListNode firstSmall = null;      // 小段链表的第一个元素, 用于函数的返回值, 链表的第一个
        ListNode lastSmall = null;  // 小段链表的最后一个元素, 用于每次添加元素的时候在链表尾部添加,从而不改变原顺序
        ListNode lastBig = null;    // 大段链表的最后一个元素, 用于每次添加元素的时候在链表尾部添加,从而不改变原顺序
        ListNode cur = head;
        while (cur != null) {
            // 保存当前节点的下一个节点, 用于循环, 因为在if操作中会改变当前节点的指向
            ListNode tempNode = cur.next;
            if (cur.val >= x) {
                if (firstBig == null) {
                    cur.next = null;
                    firstBig = cur;
                } else {
                    cur.next = lastBig.next;
                    lastBig.next = cur;
                }
                lastBig = cur;
                
            } else {
                
                if (firstSmall == null) {
                    cur.next = null;
                    firstSmall = cur;
                } else {
                    cur.next = lastSmall.next;
                    lastSmall.next = cur;
                }
                lastSmall = cur;
                
            }
            
           cur = tempNode; 
        }
        
        // 如果没有比x小的, 则直接返回大段链表的第一个元素, 否则就进行拼接
        if (firstSmall == null) {
            return firstBig;
        }
        
        // 拼接链表
        lastSmall.next = firstBig;
        return firstSmall;
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
