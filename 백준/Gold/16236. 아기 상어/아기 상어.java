import java.io.*;
import java.util.*;

public class Main {
    public static int N;
    public static int[][] arr1;
    public static PriorityQueue<node> pq = new PriorityQueue<>();
    public static Queue<node> qu = new ArrayDeque<>();

    public static class node implements Comparable<node> {
        int x, y, nowSize, eatCnt, step;

        public node(int x, int y, int nowSize, int eatCnt, int step) {
            this.x = x;
            this.y = y;
            this.nowSize = nowSize;
            this.eatCnt = eatCnt;
            this.step = step;
        }

        @Override
        public int compareTo(node o) {

            if (step == o.step) {
                if (y == o.y) {
                    return x - o.x;
                }
                return y - o.y;
            }
            return step - o.step;
        }
    }

    public static void bfs() {
        int[] dx = {0, 0, -1, 1};
        int[] dy = {-1, 1, 0, 0};
        boolean[][] vi = new boolean[N][N];

        while (!qu.isEmpty()) {

            node nd = qu.poll();

            for (int i = 0; i < 4; i++) {
                int nx = nd.x + dx[i];
                int ny = nd.y + dy[i];
                int nStep = nd.step + 1;
                if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;


                if (!vi[ny][nx]) {

                    if (arr1[ny][nx] == 0 || arr1[ny][nx] == nd.nowSize) {
                        vi[ny][nx] = true;
                        qu.offer(new node(nx, ny, nd.nowSize, nd.eatCnt, nStep));
                        continue;
                    }

                    if (arr1[ny][nx] != 0 && arr1[ny][nx] < nd.nowSize) {
                        pq.offer(new node(nx, ny, nd.nowSize, nd.eatCnt, nStep));
                        vi[ny][nx] = true;
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        int day = 0;
        arr1 = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr1[i][j] = Integer.parseInt(st.nextToken());
                if (arr1[i][j] == 9) {
                    arr1[i][j] = 0;
                    qu.offer(new node(j, i, 2, 0, 0));
                }
            }
        }

        while (true) {
            bfs();

            if (pq.isEmpty()) {
                System.out.println(day);
                System.exit(0);
            }

            node shark = pq.poll();
            arr1[shark.y][shark.x] = 0;
            shark.eatCnt += 1;
            if (shark.nowSize == shark.eatCnt) {
                shark.nowSize += 1;
                shark.eatCnt = 0;
            }
            day += shark.step;
            shark.step = 0;
            qu.offer(shark);
            pq.clear();
        }

    }
}