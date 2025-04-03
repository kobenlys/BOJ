import java.io.*;
import java.util.*;

public class Main {
    public static int N;
    public static int[][] arr1;
    public static int[][] vi;

    public static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void bfs() {
        Queue<Node> qu = new ArrayDeque<>();
        qu.offer(new Node(0, 0));

        int[] dx = {0, 0, -1, 1};
        int[] dy = {-1, 1, 0, 0};

        while (!qu.isEmpty()) {

            Node nd = qu.poll();

            for (int i = 0; i < 4; i++) {
                int nx = nd.x + dx[i];
                int ny = nd.y + dy[i];
                if(nx < 0 || ny < 0 || nx >= N || ny >= N) continue;

                if (vi[ny][nx] > vi[nd.y][nd.x] + arr1[ny][nx]) {
                    vi[ny][nx] = vi[nd.y][nd.x] + arr1[ny][nx];
                    qu.offer(new Node(nx, ny));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int phase = 1;
        while (true) {
            N = Integer.parseInt(br.readLine());
            if (N == 0) break;
            arr1 = new int[N][N];
            vi = new int[N][N];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    arr1[i][j] = Integer.parseInt(st.nextToken());
                    vi[i][j] = Integer.MAX_VALUE;
                }
            }

            vi[0][0] = arr1[0][0];
            bfs();
            sb.append("Problem ").append(phase++).append(": ").append(vi[N - 1][N - 1]).append("\n");
        }

        System.out.println(sb);
    }
}