public class leetcode_123_BestTimetoBuyandSellStockIII {
	public static int maxProfit(int[] prices) {
		if ( prices.length == 0 ) return 0;

		int buy1 = -prices[0];
		int buy2 = -prices[0];
		int sell1 = 0;
		int sell2 = 0;

		for ( int i = 1; i < prices.length; i ++ ) {
			int preBuy1 = buy1;
			int preBuy2 = buy2;
			int preSell1 = sell1;
			int preSell2 = sell2;

			// 第一次买获得利润, 应该越大越好, 等于之前的第一次买的最大利润和当前作为第一次买的利润(-prices[i])的最大值
			buy1 = Math.max( preBuy1, -prices[i] );
			// 第二次买的利润, 应该越大越好, 等于之前的第二次买的最大利润和( 第一次卖的利润的最大值减去当前价格作为第二次买入的值 )的最大值
			buy2 = Math.max( preBuy2, preSell1 - prices[i] );
			// 第一次卖的利润, 应该越大越好, 等于之前的第一次卖的最大的利润和( 之前第一次买的利润的最大值加上当前卖出的价格 )的最大值
			sell1 = Math.max( preSell1, prices[i] + preBuy1 );
			// 第二次卖的利润, 应该越大越好, 等于之前第二次卖的最大的利润和( 之前第二次买的利润的最大值加上当前卖出的价格 )的最大值
			sell2 = Math.max( preSell2, preBuy2 + prices[i] );
		}

		return Math.max( sell1, sell2 );
	}
}
