import java.util.Arrays;
import java.util.HashMap;

/**
 * 思路: 首先对硬币进行从小到大排序, findCount(coins, amount)该方法表示在硬币中查找最少个数组合成amount值
 *       在最上面维护一个minCount用于表示在硬币中查找组合成amount的最少的硬币个数, 然后对硬币进行遍历, 当硬
 *       币的值小于amount的时候, 即可以选择当前硬币i, 那么则继续调用findCount(conins, amount - coins[i])
 *       在coins中查找amount - coins[i]的最少硬币个数
 */
public class leetcode_322_CoinChange2 {
	public int coinChange(int[] coins, int amount) {
		Arrays.sort( coins );
		int count = findCount(coins, amount);
		return count;
	}

	private int findCount (int[] coins, int amount) {
		if ( amount == 0 ) {
			return 0;
		}

		// 初始化为最小值为-1, 即表示找不到一个硬币可以满足amount
		int minCount = -1;
		for ( int i = coins.length - 1; i >= 0; i -- ) {
			if ( coins[i] <= amount ) {
				int count = findCount( coins, amount - coins[i] );
				// 如果count为-1则表示在所有硬币coins中找不到满足amount - coins[i]价值的硬币组合
				// 不为-1则表示找到了一个组合, 此时需要去维护minCount的值
				if ( count != -1 ) {
					if ( minCount == -1 ) {
						minCount = count;
					} else {
						minCount = Math.min( minCount, count );
					}
				}
			}
		}

		return minCount = minCount == -1 ? minCount : minCount + 1;
	}

}

/********************
      带有记忆化搜索的版本
 ************************/
class leetcode_322_CoinChange3 {
	HashMap<Integer, Integer> memo = new HashMap<>();
	public int coinChange(int[] coins, int amount) {
		Arrays.sort( coins );
		int count = findCount(coins, amount);
		return count;
	}

	private int findCount (int[] coins, int amount) {
		if ( amount == 0 ) {
			return 0;
		}

		if ( memo.containsKey( amount ) )
			return memo.get( amount );

		int minCount = -1;
		for ( int i = coins.length - 1; i >= 0; i -- ) {
			if ( coins[i] <= amount ) {
				int count = findCount( coins, amount - coins[i] );
				if ( count != -1 ) {
					if ( minCount == -1 ) {
						minCount = count;
					} else {
						minCount = Math.min( minCount, count );
					}
				}
			}
		}

		minCount = minCount == -1 ? minCount : minCount + 1;
		memo.put( amount, minCount );

		return memo.get( amount );
	}
}