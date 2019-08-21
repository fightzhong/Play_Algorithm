public class leetcode_392_IsSubsequence {
	public boolean isSubsequence(String s, String t) {
		int sIndex = 0;
		int tIndex = 0;

		while ( sIndex < s.length() && tIndex < t.length() ) {
			if ( t.charAt( tIndex ) == s.charAt( sIndex ) ) {
				tIndex++;
				sIndex++;
			} else {
				tIndex++;
			}
		}

		return sIndex == s.length() ? true : false;
	}
}
