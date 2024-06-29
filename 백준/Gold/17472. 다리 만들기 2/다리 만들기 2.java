import java.io.*;
import java.util.*;

public class Main {
    public static int N, M;
    public static int[] parent;
    public static int[][] arr1;
    public static boolean[][] vi;
    public static int[] dx = {0, 0, -1, 1};
    public static int[] dy = {-1, 1, 0, 0};
    public static PriorityQueue<mstNode> pq = new PriorityQueue<>();

    public static class node {
        int x, y;

        public node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static class mstNode implements Comparable<mstNode> {
        int x, y, w;

        public mstNode(int x, int y, int w) {
            this.x = x;
            this.y = y;
            this.w = w;
        }

        @Override
        public int compareTo(mstNode o) {
            return w - o.w;
        }
    }

    public static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    public static boolean union(int x, int y) {
        int from = find(x);
        int to = find(y);

        if (from != to) {
            parent[to] = from;
            return false;
        }
        return true;
    }

    public static void makeBridge(int x, int y) {
        int nowIsland = arr1[y][x];

        for (int i = 0; i < 4; i++) {
            int cnt = 0;
            for (int j = 1; j <= 10; j++) {
                int nx = x + j * dx[i];
                int ny = y + j * dy[i];

                if (nx < 0 || nx >= M || ny < 0 || ny >= N) break;
                if (arr1[ny][nx] == nowIsland) break;
                int endIsland = arr1[ny][nx];


                if (arr1[ny][nx] == 0) {
                    cnt++;
                } else if (endIsland != nowIsland) {
                    if (cnt >= 2) {
                        pq.offer(new mstNode(nowIsland, endIsland, cnt));
                    }
                    break;
                }
            }
        }
    }

    public static void bfs(int x, int y, int flagN) {
        Queue<node> qu = new LinkedList<>();
        qu.offer(new node(x, y));
        vi[y][x] = true;
        arr1[y][x] = flagN;

        while (!qu.isEmpty()) {

            node nd = qu.poll();

            for (int i = 0; i < 4; i++) {
                int nx = nd.x + dx[i];
                int ny = nd.y + dy[i];

                if (nx < 0 || nx >= M || ny < 0 || ny >= N) continue;

                if (!vi[ny][nx] && arr1[ny][nx] == 1) {
                    vi[ny][nx] = true;
                    arr1[ny][nx] = flagN;
                    qu.offer(new node(nx, ny));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int cnt = 0;

        arr1 = new int[N][M];
        parent = new int[7];
        vi = new boolean[N][M];

        for (int i = 0; i < 7; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr1[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!vi[i][j] && arr1[i][j] == 1) {
                    bfs(j, i, ++cnt);
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (arr1[i][j] != 0) {
                    makeBridge(j, i);
                }
            }
        }

        int answer = 0;
        int cntIsland = 1;
        while (!pq.isEmpty()) {
            mstNode now = pq.poll();

            if (!union(now.x, now.y)) {
                cntIsland++;
                answer += now.w;
            }
        }

        System.out.println(cnt == cntIsland ? answer : -1);

    }
}