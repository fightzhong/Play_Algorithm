public class leetcode_026_RemoveDuplicatesfromSortedArray {
	public int removeDuplicates(int[] nums) {
		int insert = 1;
		int l = 0;
		int r = 1;
		while ( r < nums.length ) {
			if ( nums[l] != nums[r] ) {
				nums[insert++] = nums[r];
				l = r;
			}

			r ++;
		}
		return insert;
	}
}
