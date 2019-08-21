/**
 * 遍历数组, 不停的维护前面所有值当中的最小值, 把它当作最低的购买股票的价格
 */
public class leetcode_121_BestTimetoBuyandSellStock {
	public int maxProfit(int[] prices) {
		if ( prices.length == 0 ) return 0;
		int minBuy = prices[0]; // 最低的购买股票价格
		int maxProfit = 0;      // 最大的收益初始化为0

		for ( int i = 1; i < prices.length; i ++ ) {
			// 维护最大的收益 = Max( 原来最大的收益, 当前数组索引所在的值作为卖的价格 - 前面当中最低的购买价格 );
			maxProfit = Math.max( maxProfit, prices[i] - minBuy );
			// 维护从[0, i]中最低的购买价格
			minBuy = Math.min( minBuy, prices[i] );
		}

		return maxProfit;
	}
}
