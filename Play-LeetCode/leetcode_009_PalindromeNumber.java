import java.util.ArrayList;

public class leetcode_009_PalindromeNumber {
	public boolean isPalindrome(int x) {
		if ( x < 0 ) return false;
		if ( x >= 0 && x <= 9 ) return true;

		int num = x;
		ArrayList<Integer> list = new ArrayList<>();
		while ( num != 0 ) {
			list.add( num % 10 );
			num = num / 10;
		}

		int l = 0;
		int r = list.size() - 1;
		while ( l < r ) {
			if ( list.get( l++ ) != list.get( r-- ) )
				return false;
		}

		return true;
	}
}
