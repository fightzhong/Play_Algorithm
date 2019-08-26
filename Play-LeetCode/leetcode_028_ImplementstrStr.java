public class leetcode_028_ImplementstrStr {
	public int strStr(String haystack, String needle) {
		if ( haystack.length() == 0 ) {
			if ( needle.length() == 0 ) return 0;
			return -1;
		}

		int index = 0;
		while ( ( index + needle.length() ) <= haystack.length() ) {
			if ( haystack.substring( index, index + needle.length() ).equals( needle ) ) {
				return index;
			}

			index++;
		}

		return -1;
	}
}
