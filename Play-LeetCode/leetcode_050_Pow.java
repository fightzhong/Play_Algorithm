public class leetcode_050_Pow {
	public double myPow(double x, int n) {
		if ( n == 0 ) return 1;
		if ( n == 1 ) return x;
		if ( n == -1 ) return 1 / x;

		// 将n变成一个正数进行计算, 在最后再对结果进行判断, 如果n为负数则 1 / 结果
		long basic = Math.abs( (long)n );
		double result = positivePow( x, basic );

		return n > 0 ? result : 1 / result;
	}

	private double positivePow (double x, long basic) {
		if ( basic == 1 ) {
			return x;
		}

		// 如果basic为奇数, 则减一变成偶数进行二分计算, 但是需要在最后再乘以一个x
		long newBasic = basic % 2 == 0 ? basic : basic - 1;
		double result = positivePow( x, newBasic / 2 );

		return  basic % 2 == 0 ? result * result : result * result * x;
	}
}
