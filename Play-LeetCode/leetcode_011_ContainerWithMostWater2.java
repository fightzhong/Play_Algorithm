import java.util.HashMap;

/**
 	Description:
	 	Given n non-negative integers a1, a2, ..., an , where each represents a point at coordinate (i, ai).
	 	n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). 
	 	Find two lines, which together with x-axis forms a container, such that the container contains the most water.

 	Example:
    Input: [1, 8, 6, 2, 5, 4, 8, 3, 7]
    Output: 49
    
   思路: 
      以上面的数组为例, 一个指针指向1, 一个指针指向7, 此时的面积为7, 并且也是在x轴(宽度)为8的情况下面积的最大值, 接下来缩小宽度, 缩小为7,
      为了能够求得更大的面积, 此时应该获取更大的高度, 也就是说指向1的指针需要向右移动, 才有可能获取比1大的高度
      当左边的高度小于右边的高度时, 左边的应该往右移动, 才能获取到更大的高度, 反之, 右边的指针往左移, 这些都是因为宽度在减少 
 */

public class leetcode_011_ContainerWithMostWater2 {
  public int maxArea(int[] height) {
    int max = 0;
    int l = 0;
    int r = height.length - 1;
    
    while (l < r) {
      int x = r - l;
      int y = Math.min(height[l], height[r]);
      
      if (x * y > max) {
        max = x * y;
        if (height[l] < height[r]) {
          l++;
        } else {
          r--;
        }
      }
    }
     
    return max;
  }
}
