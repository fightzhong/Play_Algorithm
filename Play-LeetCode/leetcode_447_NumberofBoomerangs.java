import java.util.ArrayList;
import java.util.HashMap;

/**
     Description:
         Given n points in the plane that are all pairwise distinct, 
         a "boomerang" is a tuple of points (i, j, k) such that the distance 
         between i and j equals the distance between i and k (the order of the tuple matters).

         Find the number of boomerangs. You may assume that n will be at most 500 and 
         coordinates of points are all in the range [-10000, 10000] (inclusive).
      
      Example:
        Input:
            [[0,0],[1,0],[2,0]]
        Output:
            2
        Explanation:
            The two boomerangs are [[1,0],[0,0],[2,0]] and [[1,0],[2,0],[0,0]]
 
 */

public class leetcode_447_NumberofBoomerangs {
    public static void main(String[] args) {
        int[][] points = new int[][] {{0,0},{1,0},{2,0}};
        System.out.println(new leetcode_447_NumberofBoomerangs().numberOfBoomerangs(points));
    }
    
    public int numberOfBoomerangs(int[][] points) {
        int count = 0;
        // 以每一个点作为根顶点, 判断该点与其它所有点的距离, 将距离相等的放入同一个组
        for (int i = 0; i < points.length; i++) {
            
            // map计数, 将距离相等的算为一组, 计算同一个距离的个数
            // 分析: 假设与根顶点距离为3的点有1个, 那么就不能构成三元组
            //       与根顶点距离为3的点有2个, 则个数为2
            //       与根顶点距离为3的点有3个, 则个数为6
            //       与根顶点距离为3的点有4个, 则个数为12
            // 得出规律: 点有x个, 则个数为x * (x - 1)
            HashMap<Integer, Integer> map = new HashMap<>();
            for (int j = 0; j < points.length; j++) {
                if (i == j) {
                    continue;
                }
                
                int distance = getDistance(points[i], points[j]);
                if (map.get(distance) == null) {
                    map.put(distance, 1);
                } else {
                    map.put(distance, map.get(distance) + 1);
                    // 这里其实做了一步优化, 本来是在内层循环结束后对map进行遍历再求个数得
                    // 这里通过当前的个数 - 上一次的个数, 来求得增加的个数, 再将这个个数叠加到count中
                    count += (map.get(distance) * (map.get(distance) - 1)) - ((map.get(distance) - 1) * (map.get(distance) - 2)) ; 
                }
            }
        }
        
        return count;
    }
    
    public int getDistance (int[] a, int[] b) {
        assert a != null && b != null;
//        return Math.abs((a[0] - b[0]) * (a[0] - b[0]) + (a[1] - b[1]) * (a[1] - b[1]));
        return Math.abs((int)Math.pow(a[0] - b[0], 2) + (int)Math.pow(a[1] - b[1], 2));
    }
    
}
