import java.io.*;
import java.util.*;

public class Main {

    public static int N, M, answer, wallCnt;
    public static boolean[][] vi;
    public static int[][] arr1;
    public static List<Node> list;
    public static List<Node> cache;
    public static int[] dx = {0, 0, -1, 1};
    public static int[] dy = {-1, 1, 0, 0};

    public static class Node {

        int x, y, step;

        public Node(int x, int y, int step) {
            this.x = x;
            this.y = y;
            this.step = step;
        }
    }

    public static void dfs(int start) {

        if (cache.size() == M) {
            bfs();
            return;
        }

        for (int i = start; i < list.size(); i++) {
            cache.add(list.get(i));
            dfs(i+1);
            cache.remove(cache.size() - 1);
        }
    }

    public static void bfs() {
        Queue<Node> qu = new ArrayDeque<>();
        vi = new boolean[N][N];
        int res = 0;
        int nodeCnt = 0;

        for (Node ch : cache) {
            vi[ch.y][ch.x] = true;
            nodeCnt++;
            qu.offer(ch);
        }

        while (!qu.isEmpty()) {

            Node nd = qu.poll();

            for (int i = 0; i < 4; i++) {
                int nx = nd.x + dx[i];
                int ny = nd.y + dy[i];
                if (nx < 0 || ny < 0 || nx >= N || ny >= N) {
                    continue;
                }

                if (arr1[ny][nx] != 1 && !vi[ny][nx]) {
                    vi[ny][nx] = true;
                    res = Math.max(res, nd.step + 1);
                    nodeCnt++;
                    qu.offer(new Node(nx, ny, nd.step + 1));
                }
            }
        }

        if (N * N == nodeCnt + wallCnt) {
            answer = Math.min(answer, res);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        list = new ArrayList<>();
        cache = new ArrayList<>();
        answer = Integer.MAX_VALUE;
        arr1 = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr1[i][j] = Integer.parseInt(st.nextToken());
                if (arr1[i][j] == 1) {
                    wallCnt++;
                } else if (arr1[i][j] == 2) {
                    list.add(new Node(j, i, 0));
                }
            }
        }

        dfs(0);
        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }
}