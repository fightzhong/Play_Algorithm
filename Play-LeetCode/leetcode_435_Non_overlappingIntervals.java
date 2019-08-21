import java.util.Arrays;

/**
 * 动态规划(未记忆化版本):
 *    第一步:
 *	      题目有一个隐含的条件, 那就是对于两个数组{11,22},{1,11}来说, 也算是一个没有覆盖的数组,
 *	      所以我们可以对所有的数组进行排序, 排序的方式是根据这些数组的第一个值
 *	   第二步:
 *	      要想获得intervals数组中最多移除个数, 其实就可以理解为获取数组中最长的子序列, 并且这些
 *	      子序列是没有覆盖的, 然后通过intervals数组的长度减去这个子序列的长度, 即得到最多移除的个数
 *	   第三步:
 *	      要想获取intervals中最长的没有覆盖的子序列, 就需要获取当每一个数组成为子序列中的第一个的时候,
 *	      所能得到的最长的子序列的个数, 然后取这些的最大值
 *
 *	      getMaxNumberNonOverlapping( intervals, i )该方法表示获取第i个数组作为子序列的第一个值时,
 *	      所能获得的最长的子序列的长度, 在该方法中, 需要遍历从第i个数组后面的所有数组j, 当j的第一个值大于
 *	      i的第一个值的时候, j可以列入该序列中
 *
 *	  状态转移方程: f(i) = Max( f(i + 1), f(i + 2), f(i + n) ), 其中intervals[i + n][0]的值必须
 *	               大于intervals[i][ intervals[i] ](第i个数组的最后一个值)
 *
 */
public class leetcode_435_Non_overlappingIntervals {
	public static void main (String[] args) {
		int[][] intervals = new int[][]{ {1,100},{11,22},{1,11},{2,12} };
		System.out.println( new leetcode_435_Non_overlappingIntervals().eraseOverlapIntervals( intervals ) );
	}

	public int eraseOverlapIntervals(int[][] intervals) {
		Arrays.sort( intervals, (a, b ) -> {
			return a[0] - b[0];
		} );

		int maxCount = 0;
		for ( int i = 0; i < intervals.length; i++ ) {
			int count = getMaxNumberNonOverlapping( intervals, i );
			maxCount = Math.max( count, maxCount );
		}

		return intervals.length - maxCount;
	}

	public int getMaxNumberNonOverlapping (int[][] intervals, int index) {
		if ( index >= intervals.length ) {
			return 0;
		}

		int count = 1;
		for ( int i = index + 1; i < intervals.length; i ++ ) {
			if ( intervals[i][0] >= intervals[index][intervals[index].length - 1] ) {
				int count2 =  getMaxNumberNonOverlapping( intervals, i );
				count = Math.max( count, count2 + 1 );
			}
		}

		return count;
	}
}
