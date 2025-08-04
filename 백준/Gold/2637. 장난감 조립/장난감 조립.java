import java.io.*;
import java.util.*;

public class Main {

    public static int N, M;
    public static List<List<Node>> list;
    public static int[][] dp;
    public static int[] inDegree;

    public static class Node {

        int goal, value;

        public Node(int goal, int value) {
            this.goal = goal;
            this.value = value;
        }
    }

    public static void dfs(int before, int current,  int partCnt){

        if (inDegree[current] == 0) {
            dp[before][current] += partCnt;
            return;
        }

        if (dp[current][0] != -1) {
            for (int i = 1; i <= N; i++) {
                dp[before][i] += dp[current][i] * partCnt;
            }
            return;
        }

        dp[current][0] = 0;

        for (Node nd : list.get(current)) {
            dfs(current, nd.goal, nd.value);
        }
        for (int i = 1; i <= N; i++) {
            dp[before][i] += dp[current][i] * partCnt;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        dp = new int[N + 1][N + 1];
        inDegree = new int[N + 1];

        list = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            list.add(new ArrayList<>());
            dp[i][0] = -1;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int X = Integer.parseInt(st.nextToken());
            int Y = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            list.get(X).add(new Node(Y, K));
            inDegree[X]++;
        }

        dfs(0, N, 1);


        for (int i = 1; i < N; i++) {
            if (inDegree[i] == 0) {
                sb.append(i).append(" ").append(dp[0][i]).append("\n");
            }
        }

        System.out.println(sb);
    }
}
