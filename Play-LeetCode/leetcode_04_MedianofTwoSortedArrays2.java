/**
 * 上一个版本思路: 将两个数组合并, 求中位数, 如果是偶数个数, 则选择中间两个数
 *                进行求和再除以2, 如果是奇数个数, 则选择最中间的那个数即可
 *
 * 如果是两个数组的大小和是偶数个数, 那么就只需要获取第length / 2和第result.length / 2 - 1索引的值就好了, 而不用
 * 进行完全的数组合并, 这样就减少了一半的计算量
 */
public class leetcode_04_MedianofTwoSortedArrays2 {
	public double findMedianSortedArrays(int[] nums1, int[] nums2) {
		int length = nums1.length + nums2.length;
		Integer[] result = new Integer[ length / 2 + 1 ];

		int n1 = 0;
		int n2 = 0;
		int cur = 0;
		while ( n1 < nums1.length && n2 < nums2.length ) {
			if ( result[result.length - 1] != null ) {
				break;
			}

			if ( nums1[n1] < nums2[n2] ) {
				result[cur++] = nums1[n1++];
			} else {
				result[cur++] = nums2[n2++];
			}
		}

		if ( n1 < nums1.length ) {
			while ( n1 < nums1.length ) {
				if ( result[result.length - 1] != null ) {
					break;
				}
				result[cur++] = nums1[n1++];
			}
		} else {
			while ( n2 < nums2.length ) {
				if ( result[result.length - 1] != null ) {
					break;
				}
				result[cur++] = nums2[n2++];
			}
		}

		return length % 2 == 0 ? ( result[result.length - 1] + result[result.length - 2] ) / 2.0 :
				result[result.length - 1];
	}
}
