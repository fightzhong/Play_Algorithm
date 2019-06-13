/**
 * 背包问题:
 *      有一个背包, 它的容量为C(Capacity), 现在有n种不同的物品, 编号为0...n-1, 其中每一件物品的重量
 *      为w(i), 价值为v(i)。问可以向这个背包种盛放哪些物品, 使得在不超过 容量的基础上, 物品的总价值最大
 */
public class leetcode_000_knapsack01 {
    public static void main(String[] args) {
        int[] w = new int[] {1, 2, 3};
        int[] v = new int[] {6, 10, 12};
        int capacity = 5;
        System.out.println( new leetcode_000_knapsack01().getMaxValue( w, v, capacity ) );
    }
    
//    public int getMaxValue (int[] w, int[] v, int capacity) {
//        return fun( w, v, 0, capacity );
//    }
//    
//    // 考虑放入第[i,end]个物品后获得的最大容量
//    public int fun (int[] w, int[] v, int index, int capacity) {
//        int maxValue = 0;
//        // 选择放入第i个物品
//        for ( int i = index; i < w.length; i ++ ) {
//            if ( w[i] <= capacity ) {
//                int value = v[i] + fun( w, v, i + 1, capacity - w[i] );
//                maxValue = Math.max( value, maxValue );
//            }
//        }
//        
//        return maxValue;
//    }
    
    public int getMaxValue (int[] w, int[] v, int capacity) {
        return getMaxValueWithN( w, v, w.length - 1, capacity );
    }
    
    // 考虑将[0,index]个物品放入背包, 获取最大价值
    public int getMaxValueWithN (int[] w, int[] v, int index, int capacity) {
        if ( index < 0 ) {
            return 0;
        }
        // 不放入第index个物品
        int value1 = getMaxValueWithN( w, v, index - 1, capacity );
        // 放入第index个物品
        int value2 = 0;
        if ( capacity - w[index] >= 0 ) {
            value2 = v[index] + getMaxValueWithN( w, v, index - 1, capacity - w[index] );
        }
        
        return Math.max( value1, value2 );
    }
}





