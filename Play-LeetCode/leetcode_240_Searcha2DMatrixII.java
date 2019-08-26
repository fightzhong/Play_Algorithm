public class leetcode_240_Searcha2DMatrixII {
	private Boolean[][] memo;
	public boolean searchMatrix(int[][] matrix, int target) {
		if ( matrix.length == 0 ) return false;
		memo = new Boolean[matrix.length][matrix[0].length];

		return hasTarget( matrix, 0, 0, target );
	}

	private boolean hasTarget (int[][] matrix, int out, int in, int target) {
		if ( out >= matrix.length || in >= matrix[0].length ) {
			return false;
		}

		if ( memo[out][in] != null ) return memo[out][in];

		boolean result;
		if ( matrix[out][in] == target ) {
			result = true;
		} else if ( matrix[out][in] < target ) {
			boolean b1 = hasTarget( matrix, out, in + 1, target );
			boolean b2 = hasTarget( matrix, out + 1, in, target );
			result =  b1 || b2;
		} else {
			result =  false;
		}

		memo[out][in] = result;
		return result;
	}
}
