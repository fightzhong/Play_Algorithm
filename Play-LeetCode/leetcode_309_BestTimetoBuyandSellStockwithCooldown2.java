package com.fightzhong.I_leetcode;

import java.util.HashMap;

/**
 * 对于第一种解法, 时间复杂度过高, 每遍历一天, 就要对前面所有的天数进行遍历一次, 时间复杂度为O(n^2)
 *
 * 优化1: 既然我们在卖出状态的时候是需要获取到前n天买入状态的最大值, 那么我们从一开始就维护一个最大值即可
 * 优化2: 我们发现对于当天来说, 只有前一天的各种状态的最大值才有用, 再之前的都会用不上, 所以我们根本就不用
 *        维护一个map, 而是直接每次循环都去更新三种状态的值即可
 *
 * 经过这两次优化, 时间复杂度已经变成了O(n)了, 空间复杂度O(1)
 */
public class leetcode_309_BestTimetoBuyandSellStockwithCooldown {
	public int maxProfit(int[] prices) {
		if ( prices.length <= 1 ) {
			return 0;
		}

		// 初始化第一天三种状态的值
		int buy = -prices[0];
		int cooldown = 0;
		int sell = 0;
		int maxBuy = buy;     // 维护一个买入状态的最大值
		
		for ( int i = 1; i < prices.length; i ++ ) {
			// 昨天即第i - 1天各个方式的收益
			int preBuy = buy;
			int preCooldown = cooldown;
			int preSell = sell;

			// 当天为买入
			buy = preCooldown - prices[i];
			// 当天为冷却
			cooldown = Math.max( sell, Math.max( preBuy, preCooldown ) );
			// 当天为卖出 = 前i - 1天中买入股票的最大值 + 当天卖出的收益
			sell = maxBuy + prices[i];

			// 更新买入状态的最大值
			maxBuy = Math.max( maxBuy, buy );
		}

		return Math.max( cooldown, sell );
	}
}
