import java.util.HashMap;

public class leetcode_169_MajorityElement {
	public int majorityElement(int[] nums) {
		int mid = nums.length / 2;
		HashMap<Integer, Integer> memo = new HashMap<>();

		for ( int i: nums ) {
			int count;
			if ( memo.containsKey( i ) ) {
				count = memo.get( i ) + 1;
			} else {
				count = 1;
			}

			if ( count > mid ) return i;
			memo.put( i, count );
		}

		return -1;
	}
}
