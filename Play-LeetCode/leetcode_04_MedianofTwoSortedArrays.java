/**
 * 思路: 将两个数组合并, 求中位数, 如果是偶数个数, 则选择中间两个数进行求和再除以2, 如果是奇数个数, 则选择最中间的那个数即可
 */
public class leetcode_04_MedianofTwoSortedArrays {
	public double findMedianSortedArrays(int[] nums1, int[] nums2) {
		// 合并两个数组为同一个数组
		int[] result = merge( nums1, nums2 );
		if ( result.length % 2 == 0 ) {
			return ( result[result.length / 2] + result[result.length / 2 - 1] ) / 2.0;
		} else {
			return result[result.length / 2];
		}
	}

	public int[] merge (int[] nums1, int[] nums2) {
		int[] result = new int[nums1.length + nums2.length];
		int n1 = 0;
		int n2 = 0;
		int cur = 0;
		while ( n1 < nums1.length && n2 < nums2.length ) {
			if ( nums1[n1] < nums2[n2] ) {
				result[cur++] = nums1[n1++];
			} else {
				result[cur++] = nums2[n2++];
			}
		}

		if ( n1 < nums1.length ) {
			while ( n1 < nums1.length ) {
				result[cur++] = nums1[n1++];
			}
		} else {
			while ( n2 < nums2.length ) {
				result[cur++] = nums2[n2++];
			}
		}

		return result;
	}
}
