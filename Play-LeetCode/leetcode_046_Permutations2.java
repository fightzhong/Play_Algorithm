import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/***
  
     getPermuteList(): 该方法用于对一个数组进行组合, 首先通过循环每次抽出不同的元素, 然后再对剩下的元素进行组合
                         当剩下的元素组合完毕的时候, 再通过回溯的方式, 回溯到当前元素的时候, 将当前元素整合到里面
                         在之前抽出当前元素后, 可能还有多个元素需要被组合, 所以在回溯的时候可能会出现多种情况, 所以回溯时
                         需要将这些情况整合到一起后再返回给上一层
 */
public class leetcode_046_Permutations2 {
    public List<List<Integer>> permute(int[] nums) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        for ( int i: nums ) {
            list.add(i);
        }
        
        if ( nums.length == 0 ) {
            ArrayList<List<Integer>> result = new ArrayList<List<Integer>>();
            result.add( list );
            return result;
        }
        
        // 对list进行排列
        return getPermuteList( list );
    }
    
    public ArrayList<List<Integer>> getPermuteList(ArrayList<Integer> permutationList) {
        if ( permutationList.size() == 1 ) {
            ArrayList<List<Integer>> result = new ArrayList<List<Integer>>();
            result.add(permutationList);
            return result;
        }
        
        ArrayList<List<Integer>> allList = new ArrayList<List<Integer>>();
        for ( int i = 0; i < permutationList.size(); i ++ ) {
            // 提取第i个元素
            int extractEle = permutationList.get( i );
            
            // 获取除了第i个元素后的所有的元素
            ArrayList<Integer> nextPermutationList = new ArrayList<Integer>();
            for ( int j = 0; j < permutationList.size(); j ++ ) {
                if ( i != j ) {
                    nextPermutationList.add( permutationList.get(j) );
                }
            }
            
            // 对这些元素进行排列
            ArrayList<List<Integer>> returnList = getPermuteList( nextPermutationList );
            
            // 将排列后的情况整合当前元素, 然后加入allList中
            for ( List<Integer> l: returnList ) {
                l.add( extractEle );
                allList.add( l );
            }
        }
        
        return allList;
    }
}
