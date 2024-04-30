import java.io.*;
import java.util.*;

public class Main {
    public static int N, M, K, answer;
    public static int minX = 2001, minY = 2001, maxX, maxY;
    public static char[][] arr1;
    public static boolean[][] vi;
    public static int[] dx = {0, 0, -1, 1};
    public static int[] dy = {-1, 1, 0, 0};
    public static Queue<node> qu = new LinkedList<>();

    public static class node {
        int x, y;

        public node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static boolean isCanChange(int x, int y) {
        int cnt = 0;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || ny < 0 || nx >= M || ny >= N) continue;

            if (arr1[ny][nx] == 'O') cnt++;
        }

        return cnt >= 2;
    }

    public static void calcMinMaxCoordi(int x, int y) {
        maxX = Math.max(maxX, x);
        maxY = Math.max(maxY, y);
        minX = Math.min(minX, x);
        minY = Math.min(minY, y);
    }

    public static int daytimeBFS(boolean dayOrNight) {
        int cnt = 1;
        int[] dx = {0, 0, -1, 1};
        int[] dy = {-1, 1, 0, 0};

        while (!qu.isEmpty()) {

            node nd = qu.poll();

            if (dayOrNight) {
                if (arr1[nd.y][nd.x] == 'O') continue;
                if (!isCanChange(nd.x, nd.y)) continue;
                answer++;
                arr1[nd.y][nd.x] = 'O';
            }

            for (int i = 0; i < 4; i++) {
                int nx = nd.x + dx[i];
                int ny = nd.y + dy[i];
                if (nx < 0 || ny < 0 || nx >= M || ny >= N) continue;

                if (dayOrNight) {
                    if (arr1[ny][nx] == '.') {
                        qu.offer(new node(nx, ny));
                    }
                } else {
                    if (!vi[ny][nx] && arr1[ny][nx] == 'O') {
                        vi[ny][nx] = true;
                        calcMinMaxCoordi(nx, ny);
                        cnt++;
                        qu.offer(new node(nx, ny));
                    }
                }
            }
        }
        return cnt;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr1 = new char[N][M];
        vi = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                arr1[i][j] = str.charAt(j);
                if (arr1[i][j] == '.') {
                    qu.offer(new node(j, i));
                } else {
                    answer++;
                }
            }
        }

        daytimeBFS(true);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!vi[i][j] && arr1[i][j] == 'O') {
                    minX = minY = 20001;
                    maxX = maxY = 0;
                    vi[i][j] = true;
                    calcMinMaxCoordi(j, i);
                    qu.offer(new node(j, i));
                    int cnt = daytimeBFS(false);
                    int tmpX = maxX - minX + 1;
                    int tmpY = maxY - minY + 1;

                    if (K >= tmpX || K >= tmpY) {
                        answer -= cnt;
                    }
                }
            }
        }
        System.out.println(answer);
    }
}