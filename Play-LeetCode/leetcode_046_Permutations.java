import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
         下面的方式其实就是暴力解法=,=.............
 */
public class leetcode_046_Permutations {
    public static void main(String[] args) {
        int[] nums = new int[] {  };
        
        
        System.out.println(new leetcode_046_Permutations().permute(nums));
    }
    
    public List<List<Integer>> permute(int[] nums) {
        ArrayList<List<Integer>> list = new ArrayList<List<Integer>>();
        if ( nums.length == 0 ) {
            list.add(new ArrayList<Integer>());
        } else {
            HashMap<ArrayList<Integer>, ArrayList<Integer>> map = getPermute( 0, nums );
            for ( List<Integer> l: map.keySet() ) {
                list.add(l);
            }
        }
        
        
        return list;
    }
    
    public HashMap<ArrayList<Integer>, ArrayList<Integer>> getPermute (int index, int[] nums) {
        if ( index == nums.length - 1 ) {
            HashMap<ArrayList<Integer>, ArrayList<Integer>> map = new HashMap<ArrayList<Integer>, ArrayList<Integer>>();
            for ( int i = 0; i < nums.length; i ++ ) {
                ArrayList<Integer> key = new ArrayList<Integer>();  // 代表一种情况
                ArrayList<Integer> val = new ArrayList<Integer>();  // 代表该情况下, 哪个位置为空, 则上一层可以在这些位置上去添加元素
                for ( int j = 0; j < nums.length; j ++ ) {
                    if ( i == j ) {
                        key.add( nums[index] );
                    } else {
                        key.add( null );
                        val.add(j);
                    }
                }
                map.put( key, val );
            }
            
            return map;
        }
        
        // 上一层应该对每一个键进行遍历, 然后在键对应的值上添加元素, 添加后成为新的键
        HashMap<ArrayList<Integer>, ArrayList<Integer>> map = getPermute( index + 1, nums );
        Iterator<ArrayList<Integer>> iterator = map.keySet().iterator();
        HashMap<ArrayList<Integer>, ArrayList<Integer>> result = new HashMap<ArrayList<Integer>, ArrayList<Integer>>();
        while ( iterator.hasNext() ) {
            ArrayList<Integer> key = iterator.next();
            ArrayList<Integer> val = map.get(key);
            for ( int i = 0; i < val.size(); i ++ ) {
                // 复制key, 并添加当前的情况
                ArrayList<Integer> newKey = new ArrayList<Integer>();
                for ( Integer n: key ) {
                    newKey.add(n);
                }
                
                // 复制完成后, 补上空位的值
                newKey.set( val.get(i), nums[index] );
                
                // 复制val的值
                ArrayList<Integer> newVal = new ArrayList<Integer>();
                for ( int j = 0; j < val.size(); j ++ ) {
                    if ( i != j ) {
                        newVal.add(val.get(j));
                    }
                }
                
                // 添加当前情况
                result.put(newKey, newVal);
            } 
            
            iterator.remove();
        }
        
        for ( ArrayList<Integer> set: result.keySet() ) {
            map.put( set, result.get(set) );
        }
        
        return map;
    }
}
