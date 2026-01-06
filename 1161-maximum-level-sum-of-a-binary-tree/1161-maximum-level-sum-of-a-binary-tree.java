/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {

    public TreeNode root;
    public int maxLevel;
    public int[] levelValues;

    public void dfs(int level, TreeNode curr) {
        
        levelValues[level] += curr.val;
        maxLevel = Math.max(maxLevel, level);

        if(curr.left != null) {
            dfs(level+1, curr.left);
        }

        if(curr.right != null) {
            dfs(level+1, curr.right);
        }

    }

    public int maxLevelSum(TreeNode root) {
        
        this.root = root;
        levelValues = new int[1001];
        dfs(1, root);

        int answer = 0;
        int tmpMax = Integer.MIN_VALUE;

        for(int i = 1; i <= maxLevel; i++) {
            if(tmpMax < levelValues[i]) {
                answer = i;
                tmpMax = levelValues[i];
            }
        }

        return answer;
    }
}