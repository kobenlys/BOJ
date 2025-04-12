import java.io.*;
import java.util.*;

public class Main {
    public static List<List<Integer>> tree = new ArrayList<>();
    public static boolean[] vi;
    public static int[][] dp;

    public static void dfs(int node) {
        vi[node] = true;
        dp[node][0] = 0; // 얼리어답터가 아닐때
        dp[node][1] = 1; // 얼리어답터일때

        for (Integer next : tree.get(node)) {
            if(vi[next]) continue;
            dfs(next);

            dp[node][0] += dp[next][1];
            dp[node][1] += Math.min(dp[next][0], dp[next][1]);
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int answer = 0;
        vi = new boolean[N + 1];
        dp = new int[N + 1][2];

        for (int i = 0; i <= N; i++) {
            tree.add(new ArrayList<>());
        }

        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            tree.get(A).add(B);
            tree.get(B).add(A);
        }

        dfs(1);
        answer = Math.min(dp[1][0], dp[1][1]);
        System.out.println(answer);
    }
}