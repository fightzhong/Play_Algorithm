/**
 * 有两种情况, 第一种是奇数个数的, 即从单个出发, 往两边扩散, 从两边找到相同的数则为一个序列
 * 第二种是偶数个数的, 先找到一对相同的数, 然后往两边扩散
 */
public class leetcode_005_LongestPalindromicSubstring {
	private String maxLengthStr;
	public String longestPalindrome(String s) {
		if ( s.length() <= 1 ) {
			return s;
		}

		maxLengthStr = s.charAt( 0 ) + "";
		single( s );
		doubleStart( s );

		return maxLengthStr;
	}

	public void single (String str) {
		for ( int i = 0; i < str.length(); i ++ ) {
			int l = i - 1;
			int r = i + 1;
			while ( l >= 0 && r < str.length() && str.charAt( l ) == str.charAt( r ) ) {
				l --;
				r ++;
			}
			String palindrome = str.substring( l + 1, r );
			maxLengthStr = palindrome.length() > maxLengthStr.length() ? palindrome : maxLengthStr;
		}
	}

	public void doubleStart (String str) {
		for ( int i = 0; i < str.length() - 1; i ++ ) {
			if ( str.charAt( i ) == str.charAt( i + 1 ) ) {
				int l = i - 1;
				int r = i + 2;
				while ( l >= 0 && r < str.length() && str.charAt( l ) == str.charAt( r ) ) {
					l --;
					r ++;
				}
				String palindrome = str.substring( l + 1, r );
				maxLengthStr = palindrome.length() > maxLengthStr.length() ? palindrome : maxLengthStr;
			}
		}
	}
}
