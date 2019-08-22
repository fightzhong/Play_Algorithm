/**
 * 通过getColumn方法来获取有多少列, 又因为知道了有多少行, 所以题目就变成了在
 * 一个矩阵中放入数据
 */

public class leetcode_006_ZigZagConversion {
	public String convert(String s, int numRows) {
		Character[][] data = new Character[numRows][getColumn( s, numRows )];

		int index = 0;
		int y = 0;
		while ( index < s.length() ) {
			int x = 0;
			while ( x < data.length && index < s.length() ) {
				data[x++][y] = s.charAt( index++ );
			}


			x -= 2;
			y++;
			while ( x > 0 && index < s.length() ) {
				data[x--][y++] = s.charAt( index++ );
			}
		}

		String str = "";
		for ( int i = 0; i < data.length; i ++ ) {
			for ( Character c: data[i] ) {
				if ( c != null )
					str += c;
			}
		}

		return str;
	}

	private int getColumn (String s, int numRows) {
		int num = s.length();
		int count = 0;
		while ( num > 0 ) {
			num = num - numRows;
			count ++;
			if ( num <= 0 ) break;
			for ( int i = 2; i < numRows; i ++ ) {
				num --;
				count ++;

				if ( num <= 0 ) break;
			}
		}

		if ( num - numRows < 0 ) count ++;

		return count;
	}
}
