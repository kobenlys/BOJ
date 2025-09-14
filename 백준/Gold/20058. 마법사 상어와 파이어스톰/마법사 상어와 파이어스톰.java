import java.io.*;
import java.util.*;

public class Main {

    public static int N, Q, NN;
    public static int[][] arr1;
    public static int[][] cache;
    public static boolean[][] vi;

    public static int[] dx = {0, 0, -1, 1};
    public static int[] dy = {-1, 1, 0, 0};

    public static class Node {

        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

    }

    public static boolean isIn(int x, int y) {
        return 0 <= x && 0 <= y && x < NN && y < NN;
    }

    public static void turnArray(int x, int y, int padding) {

        for (int i = y; i < y + padding; i++) {
            for (int j = x; j < x + padding; j++) {
                cache[y + (j - x)][(x + padding - 1) - (i - y)] = arr1[i][j];
            }
        }

        for (int i = y; i < y + padding; i++) {
            for (int j = x; j < x + padding; j++) {
                arr1[i][j] = cache[i][j];
            }
        }
    }

    public static void meltIce() {
        for (int i = 0; i < NN; i++) {
            for (int j = 0; j < NN; j++) {
                int iceCnt = 0;
                if (arr1[i][j] == 0) {
                    cache[i][j] = 0;
                    continue;
                }
                for (int k = 0; k < 4; k++) {
                    int nx = j + dx[k];
                    int ny = i + dy[k];
                    if (isIn(nx, ny) && arr1[ny][nx] > 0) {
                        iceCnt++;
                    }
                }

                if (iceCnt < 3) {
                    cache[i][j] = arr1[i][j] - 1;
                }
            }
        }

        for (int i = 0; i < NN; i++) {
            for (int j = 0; j < NN; j++) {
                arr1[i][j] = cache[i][j];
            }
        }
    }

    public static int[] bfs(int x, int y) {
        Queue<Node> qu = new ArrayDeque<>();
        qu.offer(new Node(x, y));
        int sum = arr1[y][x];
        int cnt = 1;
        vi[y][x] = true;

        while (!qu.isEmpty()) {

            Node nd = qu.poll();

            for (int i = 0; i < 4; i++) {
                int nx = nd.x + dx[i];
                int ny = nd.y + dy[i];
                if (isIn(nx, ny) && !vi[ny][nx] && arr1[ny][nx] > 0) {
                    vi[ny][nx] = true;
                    sum += arr1[ny][nx];
                    cnt++;
                    qu.offer(new Node(nx, ny));
                }
            }
        }

        return new int[]{sum, cnt};
    }

    public static void dfs(int posSize, int targetSize, int x, int y) {

        if (posSize == targetSize) {
            turnArray(x, y, posSize);
            return;
        }

        int newSize = posSize / 2;
        dfs(newSize, targetSize, x, y); // 상좌
        dfs(newSize, targetSize, x + newSize, y); // 상우
        dfs(newSize, targetSize, x, y + newSize); // 하좌
        dfs(newSize, targetSize, x + newSize, y + newSize); // 하우

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        NN = (int) Math.pow(2, N);
        arr1 = new int[NN][NN];
        cache = new int[NN][NN];
        vi = new boolean[NN][NN];

        int sum = 0;
        int max = 0;

        for (int i = 0; i < NN; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < NN; j++) {
                arr1[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < Q; i++) {
            int L = Integer.parseInt(st.nextToken());
            int size = (int) Math.pow(2, L);
            dfs(NN, size, 0, 0);
            meltIce();
        }

        for (int i = 0; i < NN; i++) {
            for (int j = 0; j < NN; j++) {
                if (arr1[i][j] > 0 && !vi[i][j]) {
                    int[] res = bfs(j, i);

                    max = Math.max(max, res[1]);
                    sum += res[0];
                }
            }
        }
        System.out.println(sum);
        System.out.println(max);
    }
}