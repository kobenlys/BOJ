import java.io.*;
import java.util.*;

public class Main {
    public static int N, M;
    public static boolean[][] arr1;

    public static class node {
        int x, y;
        public node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void bfs(int x, int y) {
        Queue<node> qu = new LinkedList<>();
        int[] dx = {0, 0, -1, 1};
        int[] dy = {-1, 1, 0, 0};
        qu.offer(new node(x, y));

        while (!qu.isEmpty()) {

            node nd = qu.poll();

            for (int i = 0; i < 4; i++) {
                int nx = nd.x + dx[i];
                int ny = nd.y + dy[i];
                
                // nx, ny가 범위를 벗어난다면 회전할 수 있게 인덱스 값 지정.
                // nx = -1 => nx = M-1 도넛행성 처럼 한바퀴 돌아서 원래자리로 올수있음.
                if (nx >= 0 && ny >= 0) {
                    nx %= M;
                    ny %= N;
                } else {
                    if (nx < 0) nx = M - 1;
                    if (ny < 0) ny = N - 1;
                }

                // 방문처리하기.
                if (!arr1[ny][nx]) {
                    arr1[ny][nx] = true;
                    qu.offer(new node(nx, ny));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr1 = new boolean[N][M];
        int cnt = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                arr1[i][j] = st.nextToken().equals("1");
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!arr1[i][j]) {
                    cnt++; // 방문이 안된곳이 있다면 cnt++
                    bfs(j, i);
                }
            }
        }

        System.out.print(cnt);
    }
}