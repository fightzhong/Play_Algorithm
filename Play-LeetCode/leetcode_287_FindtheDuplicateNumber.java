/**
 * 根据题意可得, 在数组中存放着数 1 - n, 其中有一个数是重复的, 那么就有 n + 1个数,
 *  比如存放着1-4的数, 由于有一个数是重复的, 那么就有5个数在数组中
 *
 *  思路: 利用二分法, 既然存放着5个数, 那么我们就求得1-5的中位数, 即2(2.5取整), 此时去遍历这个
 *         数组, 如果发现小于等于2的数是刚好2个, 那么这个重复的数就不是1, 2了, 而是3, 4之间的一个数
 *         同时也说明不是在中位数的左边的数, 而是在中位数的右边的数, 即在3-5之间, 然后我们再计算3-5的
 *         中位数, 即4, 此时再遍历数组, 如果发现小于等于4的数是大于4个了, 说明在3-4之间存在一个重复的数
 *         所以我们就去3-4之间查找
 */
public class leetcode_287_FindtheDuplicateNumber {
	public int findDuplicate(int[] nums) {
		int l = 1;
		int r = nums.length;
		while ( l < r ) {
			int mid = ( r - l ) / 2 + l; // 中位数
			int count = 0;
			for ( int i: nums ) {
				if ( i <= mid ) {
					count ++;
				}
			}

			if ( count > mid ) {
				r = mid;
			} else {
				l = mid + 1;
			}
		}

		return l;
	}
}
