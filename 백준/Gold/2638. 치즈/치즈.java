import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int N, M, time;
    public static int[][] arr1;
    public static boolean[][] vi;
    public static boolean[][] usableAir;
    public static Queue<node> qu = new LinkedList<>();
    public static int[] dx = {0, 0, -1, 1};
    public static int[] dy = {-1, 1, 0, 0};

    public static class node {
        int x, y;

        public node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void meltingCheese(int x, int y) {
        int cnt = 0;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || ny < 0 || nx >= M || ny >= N) continue;

            if (!vi[ny][nx] && usableAir[ny][nx] && arr1[ny][nx] == 0) {
                cnt++;
            }
        }

        if (cnt >= 2) {
            vi[y][x] = true;
            arr1[y][x] = 0;
        }
    }

    public static void updateAir() {
        qu.clear();

        qu.offer(new node(0, 0));

        while (!qu.isEmpty()) {

            node nd = qu.poll();

            for (int k = 0; k < 4; k++) {
                int nx = nd.x + dx[k];
                int ny = nd.y + dy[k];

                if (nx < 0 || ny < 0 || nx >= M || ny >= N) continue;

                if (!usableAir[ny][nx] && arr1[ny][nx] == 0) {
                    usableAir[ny][nx] = true;
                    qu.offer(new node(nx, ny));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException { //조건 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr1 = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                arr1[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while (true) {
            vi = new boolean[N][M];
            usableAir = new boolean[N][M];
            updateAir();

            boolean loop = false;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (arr1[i][j] == 1) {
                        loop = true;
                        meltingCheese(j, i);
                    }
                }
            }

            if (!loop) {
                break;
            }
            time++;
        }
        
        System.out.print(time);
    }
}