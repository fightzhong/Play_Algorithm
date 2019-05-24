import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 思路:
 *  将num即多少个点, 获取两两组合的情况, 这个情况作为小时和分钟的情况, 例如: num = 3, 则其中一种情况为2, 1
 *  对每一种情况再进行时钟和分钟的组合, 如上述2, 1, 当2作为时钟的时候, 查找两个点在时钟的情况 
 */
public class leetcode_401_BinaryWatch {
    public static void main(String[] args) {
        System.out.println(new leetcode_401_BinaryWatch().readBinaryWatch( 8 ));
    }
    
    public List<String> readBinaryWatch(int num) {
        // 获取num个点的分布情况
        Set<List<Integer>> set = getPointCombination( num );
        
        // 对每一个分布情况进行在数组[ 1,2,4,8 ]和数组[ 1,2,4,8,16,32 ]中细分
        int[] hour = new int[] { 1, 2, 4, 8 }; 
        int[] minute = new int[] { 1, 2, 4, 8, 16, 32 };
        ArrayList<String> result = new ArrayList<>();
        
        if ( set.size() == 0 ) {
            result.add( "0:00" );
            return result;
        }
        
        for ( List<Integer> condition: set ) {
            if ( condition.get(0) == 4 ) {
                System.out.println();
            }
            // 获取小时, 分钟的情况
            ArrayList<Integer> hourCondition = getCombination( -1, 0, condition.get( 0 ), hour );
            ArrayList<Integer> minuteCondition = getCombination( -1, 0, condition.get( 1 ), minute );
            // 删除前的个数
            int hourDelSize = hourCondition.size();
            int minuteDelSize = minuteCondition.size();
            
            
            // 删除小时中大于11的, 分钟中大于59的;
            for ( int i = 0; i < hourCondition.size(); i ++ ) {
                if ( hourCondition.get( i ) > 11 ) {
                    hourCondition.remove( i-- );
                }
            }
            
            for ( int i = 0; i < minuteCondition.size(); i ++ ) {
                if ( minuteCondition.get( i ) > 59 ) {
                    minuteCondition.remove( i-- );
                }
            }
            
            // 对这两种情况合并成字符串, 放入result中
            // 这里有个特殊情况: 当删除前有1个元素, 删除后没有一个元素的话, 此时是不能合并的
            if ( ( hourCondition.size() == 0 && hourDelSize != 0 ) || minuteCondition.size() == 0 && minuteDelSize != 0 ) {
                continue;
            }
            
            merge( hourCondition, minuteCondition, result );
        }
        
        result.sort( ( a, b ) -> {
            return a.compareTo( b );
        });
        return result;
    }
    
    public void merge (ArrayList<Integer> hourCondition, ArrayList<Integer> minuteCondition, ArrayList<String> result) {
        // 处理小时为0的情况
        if ( hourCondition.size() == 0 ) {
            for ( Integer i: minuteCondition ) {
                if ( i < 10 ) {
                    result.add( "0:0" + i );
                } else {
                    result.add( "0:" + i );
                }
            }
            return;
        }
        
        // 处理分钟为0的情况
        if ( minuteCondition.size() == 0 ) {
            for ( Integer i: hourCondition ) {
                result.add( i + ":00" );
            }
            return;
        }
        
        // 都不为0的情况
        for ( Integer h: hourCondition ) {
            for ( Integer m: minuteCondition ) {
                if ( m < 10 ) {
                    result.add( h + ":0" + m );
                } else {
                    result.add( h + ":" + m );
                }
            }
        }
    }
    
    // 获取count个点在此数组的情况
    public ArrayList<Integer> getCombination (int index, int curCount, int count, int[] arr) {
        ArrayList<Integer> list = new ArrayList<Integer>();

        // 找到一种组合的情况
        if ( curCount == count && count != 0 ) {
            list.add( arr[index] );
            return list;
        }
        
        
        for ( int i = index + 1; i <= arr.length - 1; i ++ ) {
            // count - curCount = 还需要多少个数
            // arr.length - i = 最多还能有找到多少个数
            if ( count - curCount > arr.length - i) {
                continue;
            }
            
            ArrayList<Integer> returnList = getCombination( i, curCount + 1, count, arr );
            // 将list的每一个值加上当前的值, 然后放入list中
            for ( Integer l: returnList ) {
                if ( index != - 1 ) {
                    list.add( l + arr[ index ] );
                } else {
                    list.add( l );
                }
            }
        }
        
        return list;
    }
    
    // 获取两个值相加等于num的情况, 其中第一个值必须是1-4, 第二个值必须是1-6
    public Set<List<Integer>> getPointCombination (int target){
        int hourLED = 1;    // hourLED <= 4
        int minuteLED = 1;  // minuteLED <= 6
        Set<List<Integer>> list = new HashSet<>();
        
        for ( int i = hourLED; i <= 4; i ++ ) {
            if ( target - i >= 0 && target - i <= 6 ) {
                List<Integer> aList = Arrays.asList( i, target - i );
                list.add( aList );
            }
        }
        
        for ( int i = minuteLED; i <= 6; i ++ ) {
            if ( target - i >= 0 && target - i <= 4 ) {
                List<Integer> aList = Arrays.asList( target - i, i );
                list.add( aList );
            }
        }
        
        return list;
    }
}
