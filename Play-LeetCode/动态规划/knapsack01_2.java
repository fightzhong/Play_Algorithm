package com.fightzhong.I_leetcode;

/**
 * 在第二次优化中, 我们只使用了两行的空间就完成了代码的实现, 但是还可以继续优化, 因为我们
 * 在进行价值计算的时候, 每次只用到了上一行中同一列的值和上一行前面列的值, 对于上一行后面
 * 的值是没有用到的, 所以我们可以从后往前遍历capacity, 并且只用一行的空间就可以实现了
 */
public class knapsack01_4 {
	public int getMaxValue (int capacity, int[] weight, int[] value) {
		int[] memo = new int[capacity + 1];

		// 初始化第一个物品放在第一行的数据
		for ( int i = 0; i < memo.length; i ++ ) {
			memo[i] = weight[0] <= i ? value[0] : 0;
		}

		// 从第二个物品开始遍历
		for ( int i = 1; i < weight.length; i ++ ) {
			for ( int j = memo.length - 1; j >= 0; j -- ) {
				// 如果当前物品的重量已经大于了j了, 则j之前也没必要遍历了, 因为已经放不下当前物品了
				if ( weight[i] > j ) {
					break;
				}

				// 不放入当前物品
				int res = memo[j];
				// 放入当前物品
				int res2 = value[i] + memo[j - weight[i]];

				memo[j] = Math.max( res, res2 );
			}
		}

		return memo[memo.length - 1];
	}
}
