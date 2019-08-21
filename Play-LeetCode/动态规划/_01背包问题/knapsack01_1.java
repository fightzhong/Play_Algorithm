package 动态规划._01背包问题;

import java.util.Arrays;

/**
 * 问题描述: 有一个背包, 它的容量是C(capacity), 现在有n种不同的物品, 编号为0..n-1, 其中每一件物品
 *          的重量都为w(i), 价值为v(i), 问可以向这个背包中盛放哪些物品, 使得在不超过背包容量的基础上,
 *          物品的总价值最大
 * 自底向上的解法:
 *    状态转移方程: f(n, c)表示考虑将前n个物品放入容量为c的背包(即可以放也可以不放)
 *       f(n, c) = max( f(n+1, c), v(n) + f(n+1, c) )
 *
 *    所以我们在维护状态的时候, 应该维护两个状态, 即n, c, 只有当n和c完全相同时才能直接使用该记忆化搜索的内容
 */

public class knapsack01_1 {
	private int[] weight;
	private int[] value;
	private int[][] memo;   // 记忆着表示将前n个物品放入capacity不同的情况下的价值
	// 往背包中放入不同的物品, 不同的物品拥有不同的价值和重量, 背包容量一定的情况下, 获取最大的价值
	public int getMaxValue (int capacity, int[] weight, int[] value) {
		this.weight = weight;
		this.value = value;
		// 横轴表示每一个物品, 竖轴表示该物品对应不同的容量下的价值, 这里capacity+1的原因是需要表示
		// 容量为capacity时的价值
		memo = new int[weight.length][capacity + 1];
		for ( int[] arr: memo ) {
			Arrays.fill( arr, -1 );
		}

		return getMax( 0, capacity );
	}

	// 往容量为capacity的背包中放入第index个物品
	public int getMax (int index, int capacity) {
		if ( index >= weight.length || capacity <= 0 ) {
			return 0;
		}

		if ( memo[index][capacity] != -1 ) {
			return memo[index][capacity];
		}

		// 放置index物品有两种情况, 第一种是将物品放入背包, 第二种是不放入
		// 不放入
		int res = getMax( index + 1, capacity );

		// 放入
		if ( capacity >= weight[index] ) {
			res = Math.max( res, value[index] + getMax( index + 1, capacity - weight[index] ) );
		}

		memo[index][capacity] = res;
		return res;
	}
}
