/**
 * find(String[] strs, int m, int n, int index): 该方法的含义是在字符串数组[0, index]闭区间中查找最大的组合个数
 *
 * 对于第index个字符串, 可以选择, 也可以不选择
 *
 * 状态转移方程: f(index, [m,n])表示在前index个字符串中查找最大组合数, 其中m为0的个数, n为0的个数
 *      f(index, [m,n] ) = Max( f(index, [m - m(index), n - n(index)]), f(index - 1, [m,n]) )
 */
public class leetcode_474_OnesandZeroes {
	public int findMaxForm(String[] strs, int m, int n) {
		return find( strs, m, n, strs.length - 1 );
	}

	// 在字符串数组[0, index]闭区间中查找最大的组合个数
	public int find (String[] strs, int m, int n, int index) {
		if ( index < 0 || m < 0 || n < 0 ) {
			return 0;
		}

		// 获取index字符串中0和1的个数
		int[] zeroAndOne = getZeroNumberFromString( strs[index] );

		// 选择索引为index的字符串, 需要判断m,n的值是否大于该字符串中0和1的值
		int count1 = 0;
		if ( ( m >= zeroAndOne[0] && n >= zeroAndOne[1] ) ) {
			count1 = 1 + find( strs, m - zeroAndOne[0], n - zeroAndOne[1], index - 1  );
		}

		// 不选择索引为index的字符串
		int count2 = find( strs, m, n, index - 1 );

		int count = Math.max( count1, count2 );

		return count;
	}

	private int[] getZeroNumberFromString (String str) {
		int[] count = new int[]{0, 0};
		for ( int i = 0; i < str.length(); i++ ) {
			if ( str.charAt( i ) == '0' ) {
				count[0]++;
			} else {
				count[1]++;
			}
		}

		return count;
	}
}
