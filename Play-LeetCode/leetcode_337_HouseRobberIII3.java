import java.util.HashMap;

/**
 * 动态规划: 偷以root为根节点的树, 求得偷取的最大的钱数
 *      
 *      分析: 偷取以node为根节点的树, 则可以选择偷node, 也可以选择不偷node
 *      f(node) 表示偷以node为根节点的树, 获得偷取的最大的钱数
 *      v(node)表示node所在的这个房子的钱数
 *      状态转移方程:  
 *                   - 偷node : v(node) + f(node.left.left) + f(node.left.right) + f(node.right.left) + f(node.right.right)
 *          f(node)
 *                   - 不偷node: f(node.left) + f(node.right)
 *       临界点: 直接能得到的f(node)一定是叶子节点, 我们通过后序遍历, 先访问左右叶子节点, 再访问当前节点的方式, 把叶子节点的f(node)进行初始化, 然后向上
 *                回溯, 当node.left.left不存在的时候则f(node.left.left) 为0                 
 *                   
 */
public class leetcode_337_HouseRobberIII3 {
    HashMap<TreeNode, Integer> visited = new HashMap<TreeNode, Integer>();
    public int rob(TreeNode root) {
        if ( root == null ) {
            return 0;
        }
        traverseTree( root );
        return visited.get( root );
    }

    // 对树进行后序遍历, 先访问叶子节点, 再访问父亲节点
    public void traverseTree (TreeNode node) {
        if ( node == null ) {
            return;
        }
        
        // 找到一个叶子节点
        if ( node.left == null && node.right == null ) {
            visited.put( node, node.val );
            return;
        }
        
        traverseTree( node.left );
        traverseTree( node.right );
        
        /*************状态转移方程的代码实现****************/
        // 偷node节点
        int money1 = node.val;
        if ( node.left != null ) {
            if ( node.left.left != null ) {
                money1 += visited.get( node.left.left );
            }
            
            if ( node.left.right != null ) {
                money1 += visited.get( node.left.right );
            }
        }
        
        if ( node.right != null ) {
            if ( node.right.left != null ) {
                money1 += visited.get( node.right.left );
            }
            
            if ( node.right.right != null ) {
                money1 += visited.get( node.right.right );
            }
        }
        
        // 不偷node节点
        int money2 = 0;
        if ( node.left != null ) {
            money2 += visited.get( node.left );
        }
        
        if ( node.right != null ) {
            money2 += visited.get( node.right );
        }
        
        int maxMoney = Math.max( money1, money2 );
        visited.put( node, maxMoney );
    }
}

