public class leetcode_240_Searcha2DMatrixII2 {
	private Boolean[][] memo;
	public boolean searchMatrix(int[][] matrix, int target) {
		if ( matrix.length == 0 ) return false;

		// 从第一排的最后一个元素开始查找, 如果当前元素比target要大, 那么为了能够找到比target小的
		// 我们需要使得in --, 即缩小一列的范围, 如果当前元素比target要小, 那么为了能够找到比target
		// 大的元素我们需要使得out++, 即查看下一行
		int out = 0;
		int in = matrix[0].length - 1;

		while ( out < matrix.length && in >= 0 ) {
			if ( matrix[out][in] > target ) {
				in --;
			} else if ( matrix[out][in] < target ) {
				out ++;
			} else {
				return true;
			}
		}

		return false;
	}
}
