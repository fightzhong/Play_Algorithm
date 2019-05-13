import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

/**
             思路: 维护一个k的大小的最小堆， 如果当前插入的元素比最小堆中最小的元素还大， 那么就把最小的元素删除
                    然后把该元素插入
 */
public class leetcode_347_TopKFrequentElements2 {
    public List<Integer> topKFrequent(int[] nums, int k) {
        
        HashMap<Integer, Integer> count = new HashMap<Integer, Integer>();
        for (int i: nums){
            if (count.containsKey(i)){
                count.put(i, count.get(i) + 1);
            } else {
                count.put(i, 1);
            }
        }
        
        PriorityQueue<Element> queue = new PriorityQueue<>(k, (a, b) -> {
            return a.count - b.count;
        });
        
        int index = 0;
        for (Integer key: count.keySet()){
            if (index < k) {
                queue.add(new Element(key, count.get(key)));
            } else {
                if (key > queue.peek().ele) {
                    queue.poll();
                    queue.add(new Element(key, count.get(key)));
                }
            }
            index ++;
        }
        
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            list.add(queue.poll().ele);
        }
        
        return list;
    }
    
    class Element{
        public Integer ele;
        public Integer count;
        
        public Element(Integer ele, Integer count) {
            super();
            this.ele = ele;
            this.count = count;
        }
    }
}

