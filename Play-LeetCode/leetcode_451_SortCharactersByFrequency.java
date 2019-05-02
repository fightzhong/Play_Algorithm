import java.util.PriorityQueue;

/**
     Description:
         Given a string, sort it in decreasing order based on the frequency of characters.
     
     Example 1:
        Input:
            "tree"
        Output:
            "eert"
        Explanation:
            'e' appears twice while 'r' and 't' both appear once.
             So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid answer.
 
 
 */
public class leetcode_451_SortCharactersByFrequency {
    public static void main(String[] args) {
        String s = "tree";
        System.out.println(new leetcode_451_SortCharactersByFrequency().frequencySort(s));
    }
    public String frequencySort(String s) {
        int[] count  =  new int[127];       // 用于存储每个字符出现的次数
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i)]++;
        }
        
        // 获取一个最大堆, 以出现的频率作为最大堆比较的对象
        PriorityQueue<Node> queue = new PriorityQueue<>((a, b) -> {
            if (a.key > b.key) {
                return -1;
            } else if (a.key < b.key) {
                return 1;
            } else {
                return 0;
            }
        });
        
        // 将字符的频率封装成一个Node节点, 放入最大堆中
        for (int i  = 0; i < count.length; i++) {
            if (count[i] != 0) {
                Node node = new Node(count[i], (char)i);
                queue.add(node);
            }
        }
        
        // 依次取出Node, 并对每个字符进行个数的遍历
        StringBuilder str = new StringBuilder();
        while (!queue.isEmpty()) {
            Node node = queue.remove();
            for (int i = 0; i < node.key; i++) {
                str.append(node.val);
            }
        }
        
        return str.toString();
    }
}

class Node {
    int key;
    char val;
    
    public Node (int key, char val) {
        this.key = key;
        this.val = val;
    }
}
