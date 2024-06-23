import java.io.*;
import java.util.*;

public class Main {
    public static int N, M, ans;
    public static int[][] arr1;
    public static int[][] vi;
    public static ArrayList<node> virus = new ArrayList<>();
    public static ArrayList<node> tmp = new ArrayList<>();
    public static Queue<node> qu = new LinkedList<>();

    public static class node {
        int x, y;

        public node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static int cntMax() {
        int max = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (arr1[i][j] != 1) {
                    if (vi[i][j] != 0) {

                        if (arr1[i][j] == 2) continue;
                        max = Math.max(max, vi[i][j] - 1);

                    } else {
                        if(arr1[i][j] == 2) continue;
                        return -1;
                    }

                }
            }
        }
        return max;
    }

    public static void bfs() {
        int[] dx = {0, 0, -1, 1};
        int[] dy = {-1, 1, 0, 0};

        while (!qu.isEmpty()) {

            node nd = qu.poll();

            for (int i = 0; i < 4; i++) {
                int nx = nd.x + dx[i];
                int ny = nd.y + dy[i];

                if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;

                if (arr1[ny][nx] != 1 && vi[ny][nx] == 0) {
                    vi[ny][nx] = vi[nd.y][nd.x] + 1;
                    qu.offer(new node(nx, ny));
                }
            }
        }
    }

    public static void dfs(int idx) {

        if (tmp.size() == M) {
            vi = new int[N][N];
            for (node nd : tmp) {
                vi[nd.y][nd.x] = 1;
                qu.offer(new node(nd.x, nd.y));
            }

            bfs();
            int res = cntMax();
            if (res != -1) {
                ans = Math.min(ans, res);
            }
            return;
        }

        for (int i = idx; i < virus.size(); i++) {
            node nd = virus.get(i);
            tmp.add(new node(nd.x, nd.y));
            dfs(i + 1);
            tmp.remove(tmp.size() - 1);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        ans = Integer.MAX_VALUE;

        arr1 = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr1[i][j] = Integer.parseInt(st.nextToken());
                if (arr1[i][j] == 2) virus.add(new node(j, i));
            }
        }

        dfs(0);
        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
    }
}