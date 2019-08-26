/**
 * 利用快慢指针, 一个指针移动一步, 一个指针移动两步, 如果存在环那么两个指针一定会相遇,
 * 如果不存在环, 那么快慢指针 都有可能指向null, 从而退出循环, 并返回false
 */
public class leetcode_141_LinkedListCycle {
	public boolean hasCycle(ListNode head) {
		if ( head == null ) return false;

		ListNode slow = head;
		ListNode fast = head.next;

		while ( slow != null && fast != null ) {
			if ( slow == fast ) return true;
			if ( fast.next == null ) return false;
			slow = slow.next;
			fast = fast.next.next;
		}

		return false;
	}
}


