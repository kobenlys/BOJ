import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static Queue<node> qu = new LinkedList<>();
    public static int N, M, Way1 = 10001, Way2 = 10001;
    public static int[][] arr1;
    public static boolean[][] vi;

    public static class node {
        int x, y, time;

        public node(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }

    public static boolean bfs() {
        int[] dx = {0, 0, -1, 1};
        int[] dy = {-1, 1, 0, 0};
        boolean isPossible = false;
        qu.offer(new node(0, 0, 0));
        while (!qu.isEmpty()) {

            node nd = qu.poll();

            for (int i = 0; i < 4; i++) {
                int nx = nd.x + dx[i];
                int ny = nd.y + dy[i];

                if (nx < 0 || ny < 0 || nx >= M || ny >= N) continue;

                if (!vi[ny][nx]) {
                    if (arr1[ny][nx] != 1) {

                        if (nx == M - 1 && ny == N - 1) {
                            Way1 = Math.min(Way1, nd.time + 1);
                            continue;
                        }

                        if (arr1[ny][nx] == 2) {
                            isPossible = true;
                            Way2 = nd.time + 1;
                        }

                        qu.offer(new node(nx, ny, nd.time + 1));
                        vi[ny][nx] = true;
                    }
                }
            }
        }
        return isPossible;
    }

    public static void main(String[] args) throws IOException { //조건 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());
        arr1 = new int[N][M];
        vi = new boolean[N][M];

        int x = 0, y = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                arr1[i][j] = Integer.parseInt(st.nextToken());
                if (arr1[i][j] == 2) {
                    x = j;
                    y = i;
                }
            }
        }
        if (bfs()) {
            Way2 += Math.abs(x - (M - 1)) + Math.abs(y - (N - 1));
            int ans = Math.min(Way1, Way2);
            System.out.println(ans <= T ? ans : "Fail");
        } else {
            int ans = Math.min(Way1, Way2);
            System.out.println(ans <= T ? ans : "Fail");
        }
    }
}
