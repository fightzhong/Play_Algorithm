
import java.util.Arrays;

/**
 * 从后往前遍历, 举个例子nums[1, 4, 7, 2, 5], 我们维护两个数组:
 * 数组level1: 里面索引i的值对应nums中索引i的值的最长序列, 并且这个最长序列中i的下一个数比其大, 比如i为3, 则下一个数必须比i大
 * 数组level2: 里面索引i的值对应nums中索引i的值的最长序列, 并且这个最长序列中i的下一个数比其小, 比如i为3, 则下一个数必须比i小
 * 两个数组的值都初始化为1
 *
 * 对于nums[1, 4, 7, 2, 5]来说, 从后往前遍历: 每遍历一个元素时都从该元素的下一个元素遍历到末尾, 遇到比i大的值j
 * 则更新level1[i]的值为Math.max( level[j] + 1, level1[i] ), 对于level1[i]来说, 其必须取level2[j]的值, 即
 * 表示取得是元素j作为小一点的数时最长序列
 *
 */
public class leetcode_376_WiggleSubsequence {
	public static void main (String[] args) {
		int[] nums = new int[]{1,4,7,2,5};
		System.out.println( new leetcode_376_WiggleSubsequence().wiggleMaxLength( nums ) );
	}

	public int wiggleMaxLength(int[] nums) {
		if ( nums.length == 0 ) {
			return 0;
		}

		int[] level1 = new int[nums.length]; // 每一个level1[i]的值都表示当level为小值的时候的值, 即下一个比其大
		int[] level2 = new int[nums.length];
		Arrays.fill( level1, 1 );
		Arrays.fill( level2, 1 );

		for ( int i = nums.length - 1; i >= 0; i -- ) {
			for ( int j = i + 1; j < nums.length; j ++ ) {
				if ( nums[j] > nums[i] ) {
					level1[i] = Math.max( level2[j] + 1, level1[i] );
				} else if ( nums[j] < nums[i] ) {
					level2[i] = Math.max( level1[j] + 1, level2[i] );
				}
			}
		}

		int count = 1;
		for ( int i = 0; i < level1.length; i ++ ) {
			count = Math.max( count, Math.max( level1[i], level2[i] ) );
		}

		return count;
	}
}
