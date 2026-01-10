class Solution {
    public int minimumDeleteSum(String s1, String s2) {
        
        int N = s1.length();
        int M = s2.length();
        int answer = 0;
        int[][] dp = new int[N+1][M+1];

        for(int i = N-1; i >= 0; i--) {
            for(int j = M-1; j >=0; j--) {
                if(s1.charAt(i) == s2.charAt(j)) {
                    dp[i][j] = s1.charAt(i) + dp[i+1][j+1];
                } else {
                    dp[i][j] = Math.max(dp[i+1][j], dp[i][j+1]);
                }
            }
        }

        for(char c : s1.toCharArray()) {
            answer += c;
        }

        for(char c : s2.toCharArray()) {
            answer += c;
        }
        answer -= dp[0][0] * 2;
        return answer;
    }
}