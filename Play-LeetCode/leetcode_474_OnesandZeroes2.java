/**
 * 对上一个版本进行记忆化搜索优化
 */
public class leetcode_474_OnesandZeroes2 {
	private Integer[][][] memo;
	public int findMaxForm(String[] strs, int m, int n) {
		memo = new Integer[strs.length][m + 1][n + 1];
		return find( strs, m, n, strs.length - 1 );
	}

	// 在字符串数组[0, index]闭区间中查找最大的组合个数
	public int find (String[] strs, int m, int n, int index) {
		if ( index < 0 || m < 0 || n < 0 ) {
			return 0;
		}

		if ( memo[index][m][n] != null ) {
			return memo[index][m][n];
		}

		// 获取index字符串中0和1的个数
		int[] zeroAndOne = getZeroNumberFromString( strs[index] );

		// 选择索引为index的字符串
		int count1 = 0;
		if ( ( m >= zeroAndOne[0] && n >= zeroAndOne[1] ) ) {
			count1 = 1 + find( strs, m - zeroAndOne[0], n - zeroAndOne[1], index - 1  );
		}

		// 不选择索引为index的字符串
		int count2 = find( strs, m, n, index - 1 );

		memo[index][m][n] = Math.max( count1, count2 );
		return memo[index][m][n];
	}

	// 获取一个字符串中0和1的个数
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
