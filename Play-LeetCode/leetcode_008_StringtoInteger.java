import java.util.HashMap;

public class leetcode_008_StringtoInteger {
	public int myAtoi(String str) {
		// 先把数字给保存到map中, 然后在查询的时候查看一个字符是否是map中的值, 是则说明是数字
		HashMap<Character, Boolean> memo = new HashMap<>();
		memo.put( '0', true );
		memo.put( '1', true );
		memo.put( '2', true );
		memo.put( '3', true );
		memo.put( '4', true );
		memo.put( '5', true );
		memo.put( '6', true );
		memo.put( '7', true );
		memo.put( '8', true );
		memo.put( '9', true );

		// 找到第一个不为空格的字符所在的索引
		int index = 0;
		while ( index < str.length() && str.charAt( index ) == ' ' ) {
			index ++;
		}

		// 如果已经超出了字符串的大小则返回0
		if ( index >= str.length() ) return 0;

		// l,r用于截取字符串中的数字字符
		int l;
		int r;
		boolean isPossive = true;
		if ( str.charAt( index ) == '-' ) { // 如果为-则说明是负数
			isPossive = false;
			l = index + 1;
			r = index + 1;
		} else if ( str.charAt( index ) == '+' ) { // 如果为+则说明为正数
			isPossive = true;
			l = index + 1;
			r = index + 1;
		}else if ( !memo.containsKey( str.charAt( index ) ) ) {  // 如果既不为正也不为负也不是数字, 则说明不能解析, 返回0
			return 0;
		} else { // 如果为数字, 则l,r先初始化为该位置
			l = index;
			r= index;
		}

		// 找到第一个不为0的数字固定为l
		while ( l < str.length() && str.charAt( l ) == '0' ) {
			l++;
			r++;
		}

		// 找到最后一个数字所在的索引固定为r
		while ( r < str.length() && memo.containsKey( str.charAt( r ) ) )
			r ++;

		// 如果这个数字超过了10个数(防止比long类型还大), 则直接返回最大值或者最小值
		if ( r - l > 10 ) {
			return isPossive ? Integer.MAX_VALUE : Integer.MIN_VALUE;
		}

		// 对区间的数字进行解析, 解析完成后判断是否在整型范围内
		long result = r == l ? 0 : Long.parseLong( str.substring( l, r ) );
		result = isPossive ? result : -result;
		result = result >= Integer.MIN_VALUE && result <= Integer.MAX_VALUE ?
		result : ( isPossive ? Integer.MAX_VALUE : Integer.MIN_VALUE );
		return (int)result;
	}
}
