/**
     利用递归的方式, 将最后一个元素和第一个元素对比, 然后倒数第二个元素和第二个元素对比
   这里通过递归来从后往前获取元素, 但是从前往后获取元素用数组, 这样是因为数组作为变量的时候传入的是地址值,
   才能够维持第一个元素一直到后面的元素
 */
public class leetcode_234_PalindromeLinkedList2 {
    public boolean isPalindrome(ListNode head) {
        boolean[] isP = new boolean[]{true};
        ListNode[] left = new ListNode[]{head};
        traverse(head, left, isP);
        return isP[0];
    }
    
    public void traverse (ListNode right, ListNode[] left, boolean[] isP) {
        if (right == null) {
            return;
        }
        
        traverse(right.next, left, isP);
        if (left[0].val != right.val) {
            isP[0] = false;
        } 
        left[0] = left[0].next;
        
    }
}
