package 动态规划.最长公共子序列;

/**
 * 对上一个版本的进行记忆化搜索的优化
 */
public class LongestCommonSequence2 {
	public static void main (String[] args) {
		String s1 = "AAACCGTGAGTTATTCGTTCTAGAA";
		String s2 = "CACCCCTAAGGTACCTTTGGTTC";

		System.out.println( new LongestCommonSequence2().getLongestCommonSequence( s1, s2 ) );
	}

	private Integer[][] memo;
	public int getLongestCommonSequence (String s1, String s2) {
		memo = new Integer[s1.length()][s2.length()];
		return getLongestCommonSequenceInInterval( s1, s2, s1.length() - 1, s2.length() - 1 );
	}

	// 获取字符串s1[0, index1]和字符串s2[0, index2]指定区间内最长公共子序列的长度
	public int getLongestCommonSequenceInInterval (String s1, String s2, int index1, int index2) {
		if ( index1 < 0 || index2 < 0 ) {
			return 0;
		}

		if ( memo[index1][index2] != null ) {
			return memo[index1][index2];
		}

		// 如果两个元素相等, 则将这个元素纳入最长子序列当中
		if ( s1.charAt( index1 ) == s2.charAt( index2 ) ) {
			int count = 1 + getLongestCommonSequenceInInterval( s1, s2, index1 - 1, index2 - 1 );
			memo[index1][index2] = count;
		} else {
			// 两个元素不相等, 则获取s1中[0, index1 - 1]和s2中[0, index2]的最长子序列
			// 以及s1[0, index1]和s2[0, index2 - 1]的最长子序列, 并求最大值
			int count1 = getLongestCommonSequenceInInterval( s1, s2, index1 - 1, index2 );
			int count2 = getLongestCommonSequenceInInterval( s1, s2, index1, index2 - 1 );
			memo[index1][index2] = Math.max( count1, count2 );
		}

		return memo[index1][index2];
	}
}
