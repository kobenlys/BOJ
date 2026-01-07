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

    public long answer;

    public long dfsMax(TreeNode curr) {

        if (curr == null) {
            return 0;
        }

        return dfsMax(curr.left) + dfsMax(curr.right) + curr.val;
    }

    public long dfsAnswer(TreeNode curr, long max) {

        if (curr == null) {
            return 0;
        }

        long tmpMax = dfsAnswer(curr.left, max) + dfsAnswer(curr.right, max) + curr.val;
        answer = Math.max(answer, (max - tmpMax) * tmpMax);
        return tmpMax;
    }

    public int maxProduct(TreeNode root) {

        long max = dfsMax(root);
        dfsAnswer(root, max);

        return (int) (this.answer % 1_000_000_007);
    }
}