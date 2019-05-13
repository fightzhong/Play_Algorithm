import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

/**
             思路: 我们首先将每个元素的出现频率统计在HashMap中, 然后对这些元素进行遍历, 将元素和出现的频率封装在Element
                     对象中, 然后将这些对象压入最大堆, 以出现的频率为排序的标准, 然后取出前K个元素
 */
public class leetcode_347_TopKFrequentElements {
    public List<Integer> topKFrequent(int[] nums, int k) {
        
        HashMap<Integer, Integer> count = new HashMap<Integer, Integer>();
        for (int i: nums){
            if (count.containsKey(i)){
                count.put(i, count.get(i) + 1);
            } else {
                count.put(i, 1);
            }
        }
        
        PriorityQueue<Element> queue = new PriorityQueue<>((a, b) -> {
            return b.count - a.count;
        });
        
        for (Integer key: count.keySet()){
            queue.add(new Element(key, count.get(key)));
        }
        
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            list.add(queue.poll().ele);
        }
        
        return list;
    }
    
    private class Element {
        private Integer ele;
        private Integer count;
        
        public Element(Integer ele, Integer count) {
            super();
            this.ele = ele;
            this.count = count;
        }
    }
}




