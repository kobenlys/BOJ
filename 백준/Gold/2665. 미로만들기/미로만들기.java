import java.io.*;
import java.util.*;

public class Main {
    public static int N;
    public static int[][] arr1;
    public static int[][] dp;

    public static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void BFS0_1() {
        int[] dx = {0, 0, -1, 1};
        int[] dy = {-1, 1, 0, 0};

        Deque<Node> dq = new ArrayDeque<>();
        dq.offer(new Node(0, 0));
        dp[0][0] = 0;

        while (!dq.isEmpty()) {
            Node nd = dq.pollFirst();

            for (int i = 0; i < 4; i++) {
                int nx = nd.x + dx[i];
                int ny = nd.y + dy[i];
                if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;

                if (arr1[ny][nx] == 1 && dp[ny][nx] > dp[nd.y][nd.x]) {
                    dp[ny][nx] = dp[nd.y][nd.x];
                    dq.offerFirst(new Node(nx, ny));

                } else if (dp[ny][nx] > dp[nd.y][nd.x] + 1) {
                    dp[ny][nx] = dp[nd.y][nd.x] + 1;
                    dq.offerLast(new Node(nx, ny));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr1 = new int[N][N];
        dp = new int[N][N];

        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < N; j++) {
                arr1[i][j] = input.charAt(j) - '0';
                dp[i][j] = Integer.MAX_VALUE;
            }
        }

        BFS0_1();
        System.out.println(dp[N - 1][N - 1]);
    }
}