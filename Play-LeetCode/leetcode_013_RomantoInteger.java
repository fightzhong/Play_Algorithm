import java.util.HashMap;

public class leetcode_013_RomantoInteger {
	public int romanToInt(String s) {
		HashMap<Character, Integer> memo = new HashMap<>();
		memo.put( 'I', 1 );
		memo.put( 'V', 5 );
		memo.put( 'X', 10 );
		memo.put( 'L', 50 );
		memo.put( 'C', 100 );
		memo.put( 'D', 500 );
		memo.put( 'M', 1000 );

		int pre = 0;
		int sum = 0;
		for ( int i = s.length() - 1; i >= 0; i -- ) {
			if ( memo.get( s.charAt( i ) ) >= pre ) {
				sum += memo.get( s.charAt( i ) );
			} else {
				sum -= memo.get( s.charAt( i ) );
			}
			pre = memo.get( s.charAt( i ) );
		}

		return sum;
	}
}
