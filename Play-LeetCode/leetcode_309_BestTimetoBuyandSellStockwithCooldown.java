package com.fightzhong.I_leetcode;

import java.util.HashMap;

/**
 * 该解法时间复杂度过高, 但是通过了leetcode的验证
 *
 *  一天有三种情况, 买入, 卖出, 冷却
 *  对于买入来说: 当天买入获取的最大值 = 昨天冷却状态的最大值 - 当天买入的价格(因为今天要买入, 昨天必须是冷却状态)
 *  对于冷却来说: 当天冷却获取的最大值 = max(昨天冷却状态的值, 昨天卖出状态的值, 昨天买入状态的值)
 *
 *  对于卖出来说, 相对更加复杂, 如果当天需要卖出, 那么一定之前是持有股票的, 也就是说昨天, 前天, 大前天....
 *  这些天当中买入股票收益的最大值, 利用这个最大值 + 卖出的价格 就能获得当天卖出状态的最大值, 所以我在对卖出
 *  状态进行最大值获取的时候, 通过遍历[0, 昨天]闭区间内所有买入状态的值, 然后取最大的
 */
public class leetcode_309_BestTimetoBuyandSellStockwithCooldown {
	public int maxProfit(int[] prices) {
		if ( prices.length <= 1 ) {
			return 0;
		}

		HashMap<String, Integer>[] memo = new HashMap[prices.length];
		memo[0] = new HashMap<>();
		memo[0].put( "buy", -prices[0] );
		memo[0].put( "sell", 0 );
		memo[0].put( "cooldown", 0 );
		
		for ( int i = 1; i < prices.length; i ++ ) {
			System.out.println( memo[i - 1] );
			// 昨天即第i - 1天各个方式的收益
			int buy = memo[i - 1].get( "buy" );
			int cooldown = memo[i - 1].get( "cooldown" );
			int sell = memo[i - 1].get( "sell" );

			HashMap<String, Integer> map = new HashMap<>();
			// 当天为买入
			map.put( "buy", cooldown - prices[i] );
			// 当天为冷却
			map.put( "cooldown", Math.max( sell, Math.max( buy, cooldown ) ) );

			// 当天为卖出
			int max = Integer.MIN_VALUE;
			for ( int j = i - 1; j >= 0; j -- ) {
				max = Math.max( memo[j].get( "buy" ), max );
			}
			map.put( "sell", max + prices[i] );

			memo[i] = map;
		}

		// 最后一天各种方式的收益
		int cooldown = memo[memo.length - 1].get( "cooldown" );
		int sell = memo[memo.length - 1].get( "sell" );

		return Math.max( cooldown, sell );
	}
}
