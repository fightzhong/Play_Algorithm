import java.util.Arrays;

public class leetcode_455_AssignCookies {
	public static void main (String[] args) {
		int[] g = new int[]{1, 2, 3};
		int[] s = new int[]{1, 1};
		
		System.out.println( new leetcode_455_AssignCookies().findContentChildren( g, s ) );
	}

	public int findContentChildren(int[] g, int[] s) {
		Arrays.sort( g );
		Arrays.sort( s );

		int count = 0;
		int gIndex = g.length - 1;
		int sIndex = s.length - 1;

		while ( gIndex >= 0 && sIndex >= 0 ) {
			if ( s[sIndex] >= g[gIndex] ) {
				sIndex--;
				gIndex--;
				count++;
			} else {
				gIndex--;
			}
		}

		return count;
	}
}
