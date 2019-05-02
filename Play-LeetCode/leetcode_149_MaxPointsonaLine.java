/**
     思路: 固定两个点i, j, 求这两个点的斜率, 判断是否还有其它点与点i构成的斜率是相同的, 
     难点: 斜率的计算, 对x, y进行化简, 得到最简的斜率, 然后将斜率用两个位置的数组表示, 从而避免了浮点数的精度问题
             化简的时候需要获取到x, y的最大公约数, 因为化简这一步, 所以我们在判断两个斜率是否相等的时候就可以直接判断
             值是否相等就好了
 */
public class leetcode_149_MaxPointsonaLine {
    public int maxPoints(int[][] points) {
        // 如果点的个数小于等于2个, 直接返回点的个数
        if (points.length <= 2) {
            return points.length;
        }
        
        // 初始化同一条直线上最多点的个数为2
        int maxCount = 2;
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < points.length; j++) {
                if (i == j) {
                    continue;
                }
                // 固定的两个点为i, j所在的点, 求这两个点构成的斜率, 通过数组表示斜率来解决精度问题
                int[] targetSlope = getSlope(points[i], points[j]);
                int count = 2;
                // 接下来要找其它点与第一个点的斜率, 如果与targetSlope相等, 那么count++;
                for (int k = 0; k < points.length; k++) {
                    if (k == i || k == j) {
                        continue;
                    }
                    
                    // 如果该点与第一个点重合了, 此时直接count++, 不计算斜率
                    if (isSimilarPoint(points[i], points[k])) {
                        count++;
                        continue;
                    }
                    
                    // 获取k点与i点的斜率
                    int[] curTan = getSlope(points[i], points[k]);
                    
                    if (isSimilarSlope(curTan, targetSlope)) {
                        count++;
                    }
                }
                
                // 循环结束后, 维护最大个数
                if (count > maxCount) {
                    maxCount = count;
                }
            }
        }
        
        return maxCount;
    }
    
    // 获取两个顶点的斜率, 斜率 = (y2 - y1) / (x2 - x1); 这里不进行除法运算, 用数组保存斜率的分子和分母
    public int[] getSlope (int[] pointA, int[] pointB) {
        // 获取斜率
        int[] slope = new int[] {(pointA[1] - pointB[1]), (pointA[0] - pointB[0])};
        
        // 获取斜率的最大公约数
        int gcd = getGreatestCommonDivisor(slope[0], slope[1]);
        
        
        // 对斜率进行约分到最简形式, 并将其返回
        return new int[] {slope[0] / gcd, slope[1] / gcd};
    }
    
    // 判断两个斜率是否相等
    public boolean isSimilarSlope (int[] slope1, int[] slope2) {
        return slope1[0] == slope2[0] && slope1[1] == slope2[1]; 
    }
    
    // 获取a, b的最大公约数
    public int getGreatestCommonDivisor (int a, int b) {
        // 如果两个数都是0, 则其最大公约数可以为任意值
        // 如果一个为0, 另一个不为0, 则最大公约数为不为0的那个
        if (a == 0 && b == 0) {
            return 1;
        } else if (b == 0) {
            return a;
        } else if (a == 0) {
            return b;
        }
        
        // 两个都不为0的情况
        int num1 = a;
        int num2 = b;
        
        // 求a, b的最小公因数
        while (num1 % num2 != 0) {
            int temp = num1 % num2;
            num1 = num2;
            num2 = temp;
        }

        // 此时num2即为最小公因数
        return num2;
    }
    
    // 判断两个点是否重合
    public boolean isSimilarPoint (int[] pointA, int[] pointB) {
        return pointA[0] == pointB[0] && pointA[1] == pointB[1];
    }
}
