/**
      Description:
          Reverse a linked list from position m to n. Do it in one-pass.
          Note: 1 ≤ m ≤ n ≤ length of list.    
 
      Example:
          Input: 1->2->3->4->5->NULL, m = 2, n = 4
          Output: 1->4->3->2->5->NULL
 */
public class leetcode_092_ReverseLinkedListII {
    private class ListNode {
        private int val;
        ListNode next;
        
        public ListNode (int x) {
            val = x;
        }
    }
    
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null) {
            return null;
        }
        
        ListNode leftNode = null; // 左半部分节点的最后一个节点
        ListNode firstNode = m == 1 ? null : head; // 整个链表的第一个节点
        ListNode curNode = head;    // 当前正在查看的节点
        int index = 1;
        
        // 循环结束后curNode就指向了m的位置
        while (index != m) {
            leftNode = curNode;
            curNode = curNode.next;
            index++;
        }
        
        // 需要被反转范围内的节点的第一个节点, 在反转结束后该节点要指向右半部分的节点
        ListNode tailNode = curNode;
        ListNode preNode = null;    // 前一个节点
        while (index != n) {
            index++;
            ListNode tempNode = curNode.next;
            curNode.next = preNode;
            preNode = curNode;
            curNode = tempNode;
        }
        
        ListNode rightNode = curNode.next; // 右半部分的节点
        curNode.next = preNode; // 保存完右半部分节点后, 当前节点应该指向前一个节点, 这样以后, 整个链表分为了左半部分, 反转部分, 右半部分
        
        // 如果左半部分为空, 说明是从第一个节点反转的, 此时不用做任何操作, 否则应该将左半部分节点指向当前节点
        if (leftNode != null) {
            leftNode.next = curNode;
        }
        
        // 尾部节点应该指向右半部分节点, 从而将整个链表拼接起来
        tailNode.next = rightNode;

        // 如果第一个节点为空, 说明是从第一个节点反转的, 那么此时返回curNode就好了, 否则返回第一个节点
        if (firstNode == null) {
            return curNode;
        } else {
            return firstNode;
        }
    }
}
