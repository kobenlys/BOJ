import java.io.*;
import java.util.*;

public class Main {
    public static int N;
    public static int[] left;
    public static int[] right;
    public static int[][] dp;

    public static int dfs(int l, int r) {

        // 모든 카드를 다 사용했다면 Top-Down시작
        if (l == N || r == N) return 0;
        // 방문했다면 리턴
        if (dp[l][r] != -1) return dp[l][r];

        // 왼쪽카드만 버리는 경우 , 왼쪽 오른쪽 카드 둘 다 버리는 경우.
        dp[l][r] = Math.max(dfs(l + 1, r), dfs(l + 1, r + 1));

        // 만약 오른쪽 카드가 왼쪽 보다 작다면 오른쪽 카드 버리고 + 버린카드 점수 합계
        if (left[l] > right[r]) {
            dp[l][r] = Math.max(dp[l][r], dfs(l, r + 1) + right[r]);
        }
        return dp[l][r];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st1;
        StringTokenizer st2;

        N = Integer.parseInt(br.readLine());
        dp = new int[N][N];
        left = new int[N];
        right = new int[N];

        st1 = new StringTokenizer(br.readLine());
        st2 = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            left[i] = Integer.parseInt(st1.nextToken());
            right[i] = Integer.parseInt(st2.nextToken());
            Arrays.fill(dp[i], -1);
        }

        // 다이나믹 프로그래밍 (Top - Down)
        System.out.println(dfs(0, 0));
    }
}