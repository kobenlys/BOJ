import java.io.*;
import java.util.*;

public class Main {
    public static int N, M, answer;
    public static int[][] arr1;
    public static boolean[][] vi;
    public static Queue<node> qu = new ArrayDeque<>();

    public static class node {
        int x, y;

        public node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void bfs() {

        int safeZone = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (arr1[i][j] == 2) {
                    qu.offer(new node(j, i));
                }
            }
        }

        vi = new boolean[N][M];
        int[] dx = {0, 0, -1, 1};
        int[] dy = {-1, 1, 0, 0};

        while (!qu.isEmpty()) {

            node nd = qu.poll();

            for (int i = 0; i < 4; i++) {
                int nx = nd.x + dx[i];
                int ny = nd.y + dy[i];
                if (nx < 0 || ny < 0 || nx >= M || ny >= N) continue;

                if (arr1[ny][nx] == 0 && !vi[ny][nx]) {
                    vi[ny][nx] = true;
                    qu.offer(new node(nx, ny));
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (arr1[i][j] == 0 && !vi[i][j]) {
                    safeZone++;
                }
            }
        }


        answer = Math.max(answer, safeZone);
    }

    public static void dfs(int start, int x, int y) {
        if (start == 3) {
            bfs();
            return;
        }

        if(y == N) return;

        for (int i = x; i < M; i++) {
            if(arr1[y][i] == 0){
                arr1[y][i] = 1;
                dfs(start + 1, x + 1, y);
                arr1[y][i] = 0;
            }
        }
        dfs(start, 0, y + 1);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr1 = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr1[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, 0, 0);
        System.out.println(answer);
    }
}