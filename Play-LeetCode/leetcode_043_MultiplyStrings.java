import java.util.ArrayList;

public class leetcode_043_MultiplyStrings {
	public String multiply(String num1, String num2) {
		if ( ( num1.length() == 1 && num1.charAt( 0 ) == '0' ) ||
		( num2.length() == 1 && num2.charAt( 0 ) == '0' ) ) {
			return "0";
		}

		ArrayList<String> allString = new ArrayList<>();

		/*
		 * 举例: 123 * 456
		 * 依次获取123 * 6, 123 * 5, 123 * 4的字符串表示并放入allString中
		 */
		int index2 = num2.length() - 1;
		while ( index2 >= 0 ) {
			int n2 = Integer.parseInt( num2.charAt( index2 ) + "" );
			int overflowValue = 0;
			String resultStr = "";
			int index1 = num1.length() - 1;
			while ( index1 >= 0 ) {
				System.out.println( "index1: " + index1 + "  index2: " +index2  );
				int n1 = Integer.parseInt( num1.charAt( index1-- ) + "" );
				int result = n1 * n2 + overflowValue;
				resultStr = result % 10 + resultStr;
				overflowValue = result / 10;
			}

			if ( overflowValue > 0 ) {
				resultStr = overflowValue + resultStr;
			}

			allString.add( resultStr );
			index2--;
		}

		// 对所有的字符串按照乘法的表示相加
		String topStr = allString.get( 0 );
		int topIndex = topStr.length() - 1;
		for ( int i = 1; i < allString.size(); i ++ ) {
			String tempStr = topStr.substring( topIndex, topStr.length() );
			topStr = sumOfTwoString( topStr.substring( 0, topIndex ), allString.get( i ) ) + tempStr;
			topIndex = topStr.length() - i - 1;
		}

		return topStr;
	}

	// 获取两个字符串数值相加的字符串表示
	private String sumOfTwoString (String s1, String s2) {
		int index1 = s1.length() - 1;
		int index2 = s2.length() - 1;

		String resultStr = "";
		int overflowValue = 0;
		while ( index1 >= 0 && index2 >= 0 ) {
			int n1 = Integer.parseInt( s1.charAt( index1-- ) + "" );
			int n2 = Integer.parseInt( s2.charAt( index2-- ) + "" );

			int result = n1 + n2 + overflowValue;
			resultStr = result % 10 + resultStr;
			overflowValue = result / 10;
		}

		if ( index1 >= 0 ) {
			while ( index1 >= 0 ) {
				int n1 = Integer.parseInt( s1.charAt( index1-- ) + "" );
				int result = n1 + overflowValue;
				resultStr = result % 10 + resultStr;
				overflowValue = result / 10;
			}

		}

		if ( index2 >= 0 ) {
			while ( index2 >= 0 ) {
				int n2 = Integer.parseInt( s2.charAt( index2-- ) + "" );
				int result = n2 + overflowValue;
				resultStr = result % 10 + resultStr;
				overflowValue = result / 10;
			}
		}

		if ( overflowValue > 0 ) {
			resultStr = overflowValue + resultStr;
		}

		return resultStr;
	}
}
