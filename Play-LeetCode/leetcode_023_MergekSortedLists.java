import java.util.PriorityQueue;

public class leetcode_023_MergekSortedLists {
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> queue = new PriorityQueue<ListNode>((a, b) -> {
            return b.val - a.val;
        });
        
        for (int i = 0; i < lists.length; i ++) {
            ListNode node = lists[i];
            while (node != null) {
                queue.add(node);
                node = node.next;
            }
        }
        
        ListNode head = null;
        while (!queue.isEmpty()) {
            ListNode cur = queue.poll();
            cur.next = head;
            head = cur;
        }
        
        return head;
    }
}
