import java.io.*;
import java.util.*;

public class Solution {
    public static int N;
    public static int[][] arr1;
    public static boolean[][] vi;

    public static class node {
        int x, y;
        public node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static int bfs(int x, int y) {
        Queue<node> qu = new LinkedList<>();
        int[] dx = {0, 0, -1, 1};
        int[] dy = {-1, 1, 0, 0};
        qu.offer(new node(x, y));
        int cnt = 1;

        while (!qu.isEmpty()) {
            node nd = qu.poll();

            for (int i = 0; i < 4; i++) {
                int nx = nd.x + dx[i];
                int ny = nd.y + dy[i];

                if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;

                if (!vi[ny][nx] && arr1[nd.y][nd.x] + 1 == arr1[ny][nx]) {
                    cnt++;
                    vi[ny][nx] = true;
                    qu.offer(new node(nx, ny));
                }
            }
        }
        return cnt;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {

            N = Integer.parseInt(br.readLine());
            arr1 = new int[N][N];
            vi = new boolean[N][N];
            int ansNode = 0;
            int ansCnt = 0;
            int[][] node = new int[N * N + 1][2];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    arr1[i][j] = Integer.parseInt(st.nextToken());
                    node[arr1[i][j]][0] = j;
                    node[arr1[i][j]][1] = i;
                }
            }

            for (int i = 1; i <= N * N; i++) {
                int x = node[i][0];
                int y = node[i][1];
                int tmp = bfs(x, y);

                if (ansCnt < tmp) {
                    ansCnt = tmp;
                    ansNode = i;
                }
            }

            sb.append("#").append(tc).append(" ");
            sb.append(ansNode).append(" ").append(ansCnt);
            sb.append("\n");
        }
        System.out.print(sb);
    }
}