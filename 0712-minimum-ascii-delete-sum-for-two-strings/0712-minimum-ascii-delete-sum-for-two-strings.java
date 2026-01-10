class Solution {
    public int minimumDeleteSum(String s1, String s2) {

        int N = s1.length();
        int M = s2.length();
        int answer = 0;
        int[][] dp = new int[N + 1][M + 1];

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = s1.charAt(i - 1) + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        for (char c : s1.toCharArray()) {
            answer += c;
        }

        for (char c : s2.toCharArray()) {
            answer += c;
        }
        answer -= dp[N][M] * 2;
        return answer;
    }
}