package com.fightzhong.I_leetcode;

/**
 * 优化二: 通过自底向上的实现来看, 我们知道每遍历一行只需要上一行的数据, 而对于之前
 *         的数据就不再需要了, 所以我们可以利用两行来解决, 从而使得空间复杂度由
 *         O(n * C) 变成了O(2 * C)
 */
public class knapsack01_2 {
	public int getMaxValue (int capacity, int[] weight, int[] value) {
		int[][] memo = new int[2][capacity + 1];

		// 初始化第一个物品的数据
		for ( int i = 0; i < memo[0].length; i ++ ) {
			memo[0][i] = i >= weight[0] ? value[0] : 0;
		}

		// 从第二个物品开始遍历
		for ( int i = 1; i < weight.length; i ++ ) {
			// 奇数则其上一列的数据应该在memo[0]中, 则当前列的数据应该放入memo[1]中
			// 偶数则其上一列数据在memo[1]中, 则当前列的数据应该放入memo[0]中
			int lastIndex = i % 2 == 0 ? 1 : 0; // 上一列数据
			int insertIndex = i % 2;             // 当前列应该将数据放入的地方
			for ( int j = 0; j < memo[0].length; j ++ ) {
				if ( weight[i] > j ) {
					memo[insertIndex][j] = memo[lastIndex][j];
				} else {
					// 不将当前物品放入背包
					int res = memo[lastIndex][j];

					// 将当前物品放入背包
					int res2 = memo[lastIndex][j - weight[i]] + value[i];

					memo[insertIndex][j] = Math.max( res, res2 );
				}
			}
		}

		return memo[( i - 1 ) % 2][capacity];
	}
}
