class Solution {

    public long[][][][] dp;
    public int limit;
    public List<int[]> combinations;

    public long dfs(int idx, int r, int y, int g) {

        if (idx == limit) {
            return 1;
        }

        if (dp[idx][r][y][g] != 0) {
            return dp[idx][r][y][g];
        }

        for (int[] arr : combinations) {
            if (r == arr[0] || y == arr[1] || g == arr[2]) {
                continue;
            }
            dp[idx][r][y][g] = (dp[idx][r][y][g] + dfs(idx + 1, arr[0], arr[1], arr[2])) % 1_000_000_007;
        }
        return dp[idx][r][y][g];
    }

    public int numOfWays(int n) {

        this.limit = n;
        dp = new long[n + 1][4][4][4];

        combinations = new ArrayList<>();
        // red = 0, yellow = 1, green = 2;
        combinations.add(new int[] { 1, 2, 1 });
        combinations.add(new int[] { 1, 2, 3 });
        combinations.add(new int[] { 1, 3, 1 });
        combinations.add(new int[] { 1, 3, 2 });
        combinations.add(new int[] { 2, 1, 2 });
        combinations.add(new int[] { 2, 1, 3 });
        combinations.add(new int[] { 2, 3, 1 });
        combinations.add(new int[] { 2, 3, 2 });
        combinations.add(new int[] { 3, 1, 2 });
        combinations.add(new int[] { 3, 1, 3 });
        combinations.add(new int[] { 3, 2, 1 });
        combinations.add(new int[] { 3, 2, 3 });

        // for(int i = 2; i <= n; i++) {
        //     dp[i] = dp[i-1] * 56 % 1_000_000_007;
        // }
        dfs(0, 0, 0, 0);

        return (int) dp[0][0][0][0];
    }
}