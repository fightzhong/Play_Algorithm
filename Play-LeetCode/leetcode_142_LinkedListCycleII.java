public class leetcode_142_LinkedListCycleII {
	public static ListNode detectCycle(ListNode head) {
		ListNode fast = head;
		ListNode slow = head;

		while ( fast != null ) {
			if ( fast.next == null ) return null;
			fast = fast.next.next;
			slow = slow.next;
			if ( fast == slow ) break;

		}

		if ( fast == null ) return null;

		fast = head;
		while ( fast != slow ) {
			fast = fast.next;
			slow = slow.next;
		}

		return fast;
	}
}
