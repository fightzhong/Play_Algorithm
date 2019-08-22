import java.util.ArrayList;
import java.util.List;

/**
 * 先通过层序遍历的方式将每一层的节点放入不同的List中, 即result这个ArrayList<List<TreeNode>>,
 * 比如第一层的节点就放在索引为0的List中, 然后对索引为0的节点进行层序遍历, 即先访问左节点再访问右节点,
 * 然后将这些节点放入索引为1的List中, 然后又对索引为1的List进行层次遍历, 以此类推, 每一个List都存放了
 * 对应层次的节点信息, 之后对这些为奇数索引的List进行反转即可
 */
public class leetcode_103_BinaryTreeZigzagLevelOrderTraversa2 {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if ( root == null ) {
            return new ArrayList<>();
        }

        ArrayList<List<TreeNode>> result = new ArrayList<>();
        ArrayList<TreeNode> rootList = new ArrayList<>();
        rootList.add( root );
        result.add( rootList );

        int index = 0;
        while ( true ) {
            ArrayList<TreeNode> list = new ArrayList<>();
            List<TreeNode> preList = result.get( index );
            for ( TreeNode node: preList ) {
                if ( node.left != null )
                    list.add( node.left );

                if ( node.right != null )
                    list.add( node.right );
            }

            if ( list.size() == 0 )
                break;
            result.add( list );
            index ++;
        }

        ArrayList<List<Integer>> allList = new ArrayList<>();
        for ( int i = 0; i < result.size(); i ++ ) {
            ArrayList<Integer> list = new ArrayList<>();
            if ( i % 2 == 0 ) {
                for ( TreeNode node: result.get( i ) ) {
                    list.add( node.val );
                }
            } else {
                for ( int j = result.get( i ).size() - 1; j >= 0; j -- ) {
                    list.add( result.get( i ).get( j ).val );
                }
            }
            allList.add( list );
        }

        return allList;
    }
}










