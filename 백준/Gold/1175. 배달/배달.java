import java.io.*;
import java.util.*;

public class Main {
    public static int N, M;
    public static char[][] arr1;
    public static boolean[][][][] vi;
    public static node c1, c2;
    public static Queue<node> qu = new LinkedList<>();


    public static class node {
        int x, y, beforestep, gift, step;

        public node(int x, int y, int beforestep, int gift, int step) {
            this.x = x;
            this.y = y;
            this.beforestep = beforestep;
            this.gift = gift;
            this.step = step;
        }
    }


    public static void bfs() {
        int[] dx = {0, 0, -1, 1};
        int[] dy = {-1, 1, 0, 0};

        while (!qu.isEmpty()) {

            node nd = qu.poll();

            int nowGift = nd.gift;

            if (c1.y == nd.y && c1.x == nd.x) {
                if (nowGift != 1) nowGift += 1;
            } else if (c2.y == nd.y && c2.x == nd.x) {
                if (nowGift != 2) nowGift += 2;
            }

            if (nowGift == 3) {
                System.out.println(nd.step);
                System.exit(0);
            }

            for (int i = 0; i < 4; i++) {
                int nx = nd.x + dx[i];
                int ny = nd.y + dy[i];

                if (nx < 0 || ny < 0 || nx >= M || ny >= N) continue;
                if (nd.beforestep == i) continue;

                if (!vi[ny][nx][i][nowGift] && arr1[ny][nx] != '#') {
                    vi[ny][nx][i][nowGift] = true;
                    qu.offer(new node(nx, ny, i, nowGift, nd.step + 1));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int x = 0, y = 0;
        arr1 = new char[N][M];
        vi = new boolean[N][M][4][4];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                arr1[i][j] = str.charAt(j);
                if (arr1[i][j] == 'C') {
                    if (c1 == null) {
                        c1 = new node(j, i, 0, 0, 0);
                    } else {
                        c2 = new node(j, i, 0, 0, 0);
                    }
                }
                if (arr1[i][j] == 'S') {
                    vi[i][j][0][0] = true;
                    qu.offer(new node(j, i, -1, 0, 0));
                }
            }
        }

        bfs();
        System.out.println(-1);
    }
}