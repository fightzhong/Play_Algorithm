import java.util.HashMap;

public class leetcode_160_IntersectionofTwoLinkedLists {
	public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
		HashMap<ListNode, Boolean> memo = new HashMap<>();

		while ( headA != null ) {
			memo.put( headA, true );
			headA = headA.next;
		}

		while ( headB != null ) {
			if ( memo.containsKey( headB ) ) return headB;
			headB = headB.next;
		}

		return null;
	}
}
