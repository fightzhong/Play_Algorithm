/**
 *
 * 思路: 一个升序的数组0,1,2,4,5,6,7在经过反转后变成了4,5,6,7,0,1,2
 *       观察发现如果我们要找到的target比第一个值即nums[0]要大的话就从0开始查找, 找到数字7对应的位置
 *       如果我们要找到的target比第一个值即nums[0]要小的话就从数组的最后一个位置开始找, 找到数字0的位置
 *
 *       对于第一种情况, 找的终点是nums[i + 1] < nums[i]即不是升序的情况
 *       对于第二种情况, 找的终点是nums[i - 1] > nums[i]即不是降序的情况
 */
public class leetcode_033_SearchinRotatedSortedArray {
	public int search(int[] nums, int target) {
		if ( nums.length == 0 ) return -1;
		if ( target < nums[0] ) {
			int index = nums.length - 1;
			while ( index >= 0 ) {
				if ( target == nums[index] )
					return index;
				if ( index < 0 || ( index - 1 >= 0 && nums[index - 1] > nums[index] ) )
					break;
				index --;

			}
		} else {
			int index = 0;
			while ( index < nums.length ) {
				if ( target == nums[index] ) return index;
				if ( index >= nums.length ||
				( index + 1 < nums.length &&nums[index] > nums[index + 1] ) )
					break;
				index ++;
			}
		}

		return -1;
	}
}
