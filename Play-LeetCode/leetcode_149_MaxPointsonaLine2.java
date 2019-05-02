import java.util.Arrays;
import java.util.HashMap;

/**
     
 */
public class leetcode_149_MaxPointsonaLine2 {
    public int maxPoints(int[][] points) {
        // 如果点的个数小于等于2个, 直接返回点的个数
        if (points.length <= 2) {
            return points.length;
        }
        
        int maxCount = 2;
        // 通过双层循环, 获取任意两个点之间的斜率
        // 映射: 键 -> 斜率, 值 -> 该斜率的个数 
        for (int i = 0; i < points.length; i++) {
            HashMap<String, Integer> slopeMap = new HashMap<>();
            int silimarPoints = 0;
            for (int j = i + 1; j < points.length; j++) {
                if (isSimilarPoint(points[i], points[j])) {
                    silimarPoints++;
                }
                // 计算两个点的斜率
                int[] slope = getSlope(points[i], points[j]);
                if (slopeMap.get(Arrays.toString(slope)) == null) {
                    slopeMap.put(Arrays.toString(slope), 1);
                } else {
                    slopeMap.put(Arrays.toString(slope), slopeMap.get(Arrays.toString(slope)) + 1);
                }
            }
            
            for (String slope: slopeMap.keySet()) {
                // 如果两个顶点重合, 则不用加silimarPoints的值, 否则要加上
                if (!slope.equals(Arrays.toString(new int[] {0,0})) ) {
                    slopeMap.put(slope, slopeMap.get(slope) + silimarPoints); 
                }
                if (slopeMap.get(slope) + 1 > maxCount) {
                    maxCount = slopeMap.get(slope) + 1;
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
