import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class leetcode_341_FlattenNestedListIterator{
    public class NestedIterator implements Iterator<Integer> {
        private int index;
        private ArrayList<Integer> list;
        
        public NestedIterator(List<NestedInteger> nestedList) {
            index = 0;
            list = new ArrayList<Integer>();
            addElement(nestedList); 
        }
        
        public void addElement (List<NestedInteger> nestedList) {
            for (int i = 0; i < nestedList.size(); i++) {
                NestedInteger node = nestedList.get(i);
                if (node.isInteger()){
                    list.add(node.getInteger());
                } else {
                    addElement(node.getList());
                }
                
            }
        }

        @Override
        public Integer next() {
            return list.get(index++);
        }

        @Override
        public boolean hasNext() {
            return index < list.size();
        }
    }
}

interface NestedInteger {
     // @return true if this NestedInteger holds a single integer, rather than a nested list.
     public boolean isInteger();
    
     // @return the single integer that this NestedInteger holds, if it holds a single integer
     // Return null if this NestedInteger holds a nested list
     public Integer getInteger();
    
     // @return the nested list that this NestedInteger holds, if it holds a nested list
     // Return null if this NestedInteger holds a single integer
     public List<NestedInteger> getList();
 }
