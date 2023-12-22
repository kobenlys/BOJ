import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int N, M;
    public static int[][] arr1;
    public static boolean[][] vi;
    public static int[] dx = {0, 0, -1, 1};
    public static int[] dy = {-1, 1, 0, 0};
    public static Queue<node> qu = new LinkedList<>();

    public static class node{// 좌표 담는 객체
        int x, y;

        public node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static boolean range(int x, int y) { // 범위 체크
        return x >= 0 && y >= 0 && x < M && y < N;
    }

    public static void bfs(int x, int y) { // bfs 알고리즘 실행
        qu.offer(new node(x, y));
        arr1[y][x] = 0;
        while (!qu.isEmpty()) {
            node nd = qu.poll();

            for (int i = 0; i < 4; i++) {
                int nx = nd.x + dx[i];
                int ny = nd.y + dy[i];
                if (range(nx, ny)) {

                    if (arr1[ny][nx] == 1 && !vi[ny][nx]) {
                        arr1[ny][nx] += arr1[nd.y][nd.x];
                        vi[ny][nx] = true;
                        qu.offer(new node(nx, ny));
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr1 = new int[N][M];
        vi = new boolean[N][M];
        int siteX = 0, siteY = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                arr1[i][j] = Integer.parseInt(st.nextToken());
                if (arr1[i][j] == 2) {
                    siteX = j;
                    siteY = i;
                }
            }
        }
        // bfs 실행, 요소가 2 인 좌표 전달
        bfs(siteX, siteY);

        // arr1[i][j] 가 '1'이고 방문하지 않았다면 '-1' 출력
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (arr1[i][j] == 1 && !vi[i][j]) {
                    sb.append(-1).append(" ");
                    continue;
                }
                sb.append(arr1[i][j]).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}