/**
 * 我们每一次只有两种操作, 复制和黏贴, 那么如果前一次操作是复制的话, 下一次操作就只能是黏贴了
 * 如果前一次操作是黏贴的话, 那么下一次操作就可以是复制和黏贴, 每一步操作都需要有前一步操作四个
 * 参数【前一次操作类型、复制的个数, 需要A的目标个数, 前一次操作结束后还需要多少个A】,
 * 如果到最后不能凑够目标个数, 或者超过了目标个数, 则返回-1, 表示不可达的情况
 */
public class leetcode_650_2KeysKeyboard {
	public static void main (String[] args) {
		System.out.println( new leetcode_650_2KeysKeyboard().minSteps( 4 ) );;
	}
	private int COPY = 1;
	private int PASTE = 2;

	public int minSteps(int n) {
		return getMinStep( COPY, 1, n - 1, 1 ) + 1;
	}

	// 获取最少的步数
	private int getMinStep (int preStep, int copyCount, int targetCount, int preCount) {
		/**递归终止条件***/
		if ( targetCount == 0 ) {
			return 0;
		}

		if ( targetCount < 0 ) return -1;
		/**递归终止条件***/

		int steps;
		// 如果前一步是COPY, 那么当前步只能是PASTE, 所以应该使得数量减去copy的值
		if ( preStep == COPY ) {
			steps = getMinStep( PASTE, copyCount, targetCount - copyCount, preCount + copyCount );
		} else {
			// 如果前一步是PASTE, 那么当前步可以是COPY, 也可以是PASTE
			int step1 = getMinStep( PASTE, copyCount, targetCount - copyCount, preCount + copyCount );
			int step2 = getMinStep( COPY, preCount, targetCount, preCount );

			if ( step1 == -1 && step2 == -1 ) {
				steps = -1;
			} else if ( step1 == -1 && step2 != -1 ){
				steps = step2;
			} else if ( step1 != -1 && step2 == -1 ) {
				steps = step1;
			} else {
				steps = Math.min( step1, step2 );
			}
		}

		return steps == -1 ? -1 : steps + 1;
	}
}
