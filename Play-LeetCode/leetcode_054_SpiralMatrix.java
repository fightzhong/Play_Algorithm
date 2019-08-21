import java.util.ArrayList;
import java.util.List;

/**
 * 维护左上角顶点和右下角顶点, 通过这两个顶点构成一个矩形, 对这个矩形进行圆圈遍历,
 * 即先从左上角的顶点x往右遍历, 遍历到矩形的横轴终点即右边的终点时即停止, 停止的条件是同一行的最后一个点,
 * 但是不将这个点纳入横向遍历中, 这样是为了方便处理边界, 然后从这个点出发纵向遍历到竖轴的终点, 同样不将
 * 这个竖轴的终点纳入范围(方便处理边界), 然后从这个终点往左横向遍历, 最后从左边的终点往上轴向遍历, 这样就完成了一个周期
 *
 * 然后缩小矩形的范围, 使得左上角的顶点(x,y)均加1即(x+1,y+1), 右下角的顶点(a,b)均减一即(a-1,b-1)
 *
 * 最后考虑边界情况, 当缩小矩形到最后后变为了同一行后即x == a的时候或者提供的矩阵本身是只有一列的时候,
 * 如: [[1],[2],[3],[4],[5],[6],[7]]
 *
 */
public class leetcode_054_SpiralMatrix {
	public List<Integer> spiralOrder(int[][] matrix) {
		if ( matrix.length == 0 ) {
			return new ArrayList<>();
		}
		int leftX = 0;
		int leftY = 0;
		int rightX = matrix.length - 1;
		int rightY = matrix[0].length - 1;

		ArrayList<Integer> result = new ArrayList<>();
		System.out.println( leftX + "  " + rightX + leftY + "  " + rightY );
		while ( leftX < rightX && leftY < rightY ) {
			int x = leftX;
			int y = leftY;
			while ( y + 1 <= rightY ) {
				result.add( matrix[x][y++] );
			}

			while ( x + 1 <= rightX ) {
				result.add( matrix[x++][y] );
			}

			while ( y - 1 >= leftY ) {
				result.add( matrix[x][y--] );
			}

			while ( x - 1 >= leftX ) {
				result.add( matrix[x--][y] );
			}

			leftX++;
			leftY++;
			rightX--;
			rightY--;
		}

		System.out.println( result );

		if ( leftX == rightX ) {
			int curIndex = leftY;
			while ( curIndex <= rightY ) {
				result.add( matrix[leftX][curIndex++] );
			}
		} else if ( leftY == rightY ) {
			int curIndex = leftX;
			while ( curIndex <= rightX ) {
				result.add( matrix[curIndex++][leftY] );
			}
		}

		return result;
	}
}
