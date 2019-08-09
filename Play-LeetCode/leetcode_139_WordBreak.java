import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class leetcode_139_WordBreak {
	HashMap<String, Boolean> memo = new HashMap<>();

	// 判断字符串s能否用集合wordDict中的字符串来进行分割
	public boolean wordBreak(String s, List<String> wordDict) {
		if ( s.length() == 0 ) {
			return true;
		}

		for ( String str: wordDict ) {
			// 如果是以该字符串开头, 那么就截取之后的字符串继续判断能否被分割
			if ( s.startsWith( str ) ) {
				String newStr = s.substring( str.length() );
				if ( wordBreak( newStr, wordDict ) ) {
					memo.put( s, true );
					return true;
				};
			}
		}

		memo.put( s, false );
		return false;
	}
}
