import java.io.*;
import java.util.*;

public class Main {

    public static class Node {

        int priority, time;

        public Node(int priority, int time) {
            this.priority = priority;
            this.time = time;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Node[] arr1 = new Node[M + 1];
        int[][] dp = new int[M + 1][N + 1];

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            arr1[i] = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        for (int i = 1; i <= M; i++) {
            for (int j = 1; j <= N; j++) {

                if (arr1[i].time > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j],
                        dp[i - 1][j - arr1[i].time] + arr1[i].priority);
                }
            }
        }

        System.out.println(dp[M][N]);
    }
}
