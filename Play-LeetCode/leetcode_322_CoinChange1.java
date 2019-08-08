import java.util.Arrays;

/**
 *
 * f(n, C)表示在[n, nums.length - 1]闭区间中查找和为C的最小硬币个数
 * 状态转移方程: f(n, C) = Min( f( n, C-v(n) ), f( n + 1, C ), f( n + 1, C -v(n) ) )
 *
 * 对索引为n的硬币有三种情况:
 *      选择当前硬币, 并从下一个硬币继续选择: f( n + 1, C-v(n) )
 *      选择当前硬币, 并从当前硬币继续选择: f( n, C-v(n) )
 *      不选择当前硬币, 从下一个硬币开始选择: f( n + 1, C -v(n) )
 *
 * 临界点: amount == nums[index], 即当前硬币的值刚好等于需要的amount
 *       如果amount < 0, 或者index >= 硬币数组的长度, 那么就不可能找到对应的硬币个数了, 此时返回-1
 */
public class leetcode_322_CoinChange1 {
	int[][] memo;
	public int coinChange(int[] coins, int amount) {
		// 初始化都为-2, 表示没有访问过, 即没有值
		memo = new int[coins.length][amount + 1];
		for ( int[] arr: memo ) {
			Arrays.fill( arr, -2 );
		}

		int count = tryGetAmount( coins, 0, amount );

		return count;
	}

	// 查找[index, length - 1]中和为amount数的硬币组合情况
	public int tryGetAmount (int[] coins, int index, int amount) {
		if ( index >= coins.length || amount < 0 ) {
			return -1;
		}

		// 当不等于-2, 说明已经记录过该值了, 直接返回
		if ( memo[index][amount] != -2 ) {
			return memo[index][amount];
		}

		// 找到一个组合, 返回一个1表示当前这个硬币
		if ( amount == coins[index] ) {
			return 1;
		}

		// 选择当前值, 并从当前值开始继续查找
		int c1 = tryGetAmount( coins, index, amount - coins[index] );
		// 如果返回的是-1, 说明没找到, 那么就直接返回-1, 如果不是, 那么就说明找到一个组合, 此时应该加1表示加上当前这个硬币
		c1 = c1 == -1 ? -1 : c1 + 1;

		// 选择当前值, 并从下一个值开始继续查找
		int c2 = tryGetAmount( coins, index + 1, amount - coins[index] );
		// 如果返回的是-1, 说明没找到, 那么就直接返回-1, 如果不是, 那么就说明找到一个组合, 此时应该加1表示加上当前这个硬币
		c2 = c2 == -1 ? -1 : c2 + 1;

		// 不选择当前值, 从下一个值开始继续查找, 不选择当前值的时候就不用进行是否等于-1的判断了, 因为不用加上当前的硬币
		int c3 = tryGetAmount( coins, index + 1, amount );

		int res = -1;
		if ( c1 >= 0 || c2 >= 0 || c3 >= 0 ) {
			// 获取三个数当中不为0的最小值
			int[] all = new int[]{c1, c2, c3};
			int count = -1;
			for ( int c: all ) {
				if ( c != -1 ) {
					if ( count == -1 ) {
						count = c;
					} else {
						count = Math.min( c, count );
					}
				}
			}

			res = count;
		}

		memo[index][amount] = res;
		return res;
	}

}