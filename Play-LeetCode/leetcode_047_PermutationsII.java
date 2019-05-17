import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
      类似于046题, 不同的是在进行生成排列的时候, 需要对一个元素进行判断, 是否已经被提取过(因为可能出现重复的元素, 那么对
      重复的元素进行提取就会出现重复的情况了
 */
public class leetcode_047_PermutationsII {
    public static void main(String[] args) {
        System.out.println( new leetcode_047_PermutationsII().permuteUnique(new int[] { 1, 1, 2 }) );
    }
    
    public List<List<Integer>> permuteUnique(int[] nums) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        for ( int i: nums ) {
            list.add( i );
        }
        
        return generatePermute( list );
    }
    
    // 对数组list进行排列, 取出一个元素, 然后将剩余的元素进行排列, 这里要处理重复的情况, 不能重复取出元素
    public ArrayList<List<Integer>> generatePermute (ArrayList<Integer> list){
        if ( list.size() == 1 ) {
            ArrayList<List<Integer>> returnList = new ArrayList<List<Integer>>();
            returnList.add(list);
            return returnList;
        }
        
        // 判断是否已经提取过该元素
        HashMap<Integer, Boolean> isExtract = new HashMap<Integer, Boolean>();
        ArrayList<List<Integer>> allList = new ArrayList<List<Integer>>();
        for ( int i = 0; i < list.size(); i ++ ) {
            int extractEle = list.get(i);
            if ( !isExtract.containsKey( extractEle ) ) {
                // 获取剩余的元素
                ArrayList<Integer> remainEleList = new ArrayList<Integer>();
                for ( int j = 0; j < list.size(); j ++ ) {
                    if ( i != j ) {
                        remainEleList.add( list.get(j) );
                    }
                }
                
                // 对剩余的元素进行排列, 获得一个排列的情况
                ArrayList<List<Integer>> singlePermute = generatePermute( remainEleList );
                
                // 将当前提取的元素整合到这个排列中, 并将每一个整合的排列放入总的排列中
                for ( List<Integer> l: singlePermute ) {
                    l.add( extractEle );
                    allList.add(l);
                }
                
                // 将当前元素设置为已经提取过
                isExtract.put( extractEle, true );
            }
        }
        
        return allList;
    }
}
