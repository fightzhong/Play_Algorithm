import java.util.Arrays;

/**
 * 对上一个版本的动态规划解法进行记忆化搜索的优化
 */
public class leetcode_435_Non_overlappingIntervals2 {
	public static void main (String[] args) {
		int[][] intervals = new int[][]{ {1,100},{11,22},{1,11},{2,12} };
		System.out.println( new leetcode_435_Non_overlappingIntervals2().eraseOverlapIntervals( intervals ) );
	}

	private Integer[] memo;
	public int eraseOverlapIntervals(int[][] intervals) {
		Arrays.sort( intervals, (a, b ) -> {
			return a[0] - b[0];
		} );

		memo = new Integer[intervals.length];
		int maxCount = 0;
		for ( int i = 0; i < intervals.length; i++ ) {
			int count = memo[i] == null ? getMaxNumberNonOverlapping( intervals, i ) : memo[i];
			maxCount = Math.max( count, maxCount );
		}

		return intervals.length - maxCount;
	}

	public int getMaxNumberNonOverlapping (int[][] intervals, int index) {
		if ( index >= intervals.length ) {
			return 0;
		}

		if ( memo[index] != null ) {
			return memo[index];
		}

		int count = 1;
		for ( int i = index + 1; i < intervals.length; i ++ ) {
			if ( intervals[i][0] >= intervals[index][intervals[index].length - 1] ) {
				int count2 =  getMaxNumberNonOverlapping( intervals, i );
				count = Math.max( count, count2 + 1 );
			}
		}

		memo[index] = count;
		return memo[index];
	}
}
