/**
 * 思路: 将x转为字符串(保留x是正和负的标识), 再将字符串转为字符数组, 对数组进行双指针交换元素,
 *       最后将字符串解析为long类型的数据, 判断是否在int范围内即可
 */
public class leetcode_007_ReverseInteger {
	public int reverse(int x) {
		if ( x == 0 ) return 0;
		boolean isPositive = x > 0;


		long target = isPositive ? x : -( (long)x );
		char[] chars = ( target + "" ).toCharArray();
		int l = 0;
		int r = chars.length - 1;

		while ( l < r ) {
			char temp = chars[l];
			chars[l] = chars[r];
			chars[r] = temp;
			l ++;
			r --;
		}



		int offset = 0;
		while ( offset < chars.length && chars[offset] == '0' ) {
			offset ++;
		}

		String resultStr = new String( chars, offset, chars.length - offset );
		long resultLong = Long.parseLong( resultStr );
		resultLong = isPositive ? resultLong : -resultLong;

		return resultLong >= Integer.MIN_VALUE && resultLong <= Integer.MAX_VALUE ?
		(int)resultLong : 0;
	}
}
