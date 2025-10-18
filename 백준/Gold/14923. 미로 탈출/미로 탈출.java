import java.io.*;
import java.util.*;

public class Main {

    public static int N, M;
    public static int[][] arr1;
    public static boolean[][][] vi;

    public static class Node {

        int x, y, z, cnt;

        public Node(int x, int y, int z, int cnt) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.cnt = cnt;
        }
    }

    public static boolean isIn(int x, int y) {
        return x >= 0 && y >= 0 && x < M && y < N;
    }

    public static int bfs(int sX, int sY, int eX, int eY) {
        Queue<Node> qu = new ArrayDeque<>();
        qu.offer(new Node(sX, sY, 0, 0));
        vi[0][sY][sX] = vi[1][sY][sX] = true;
        int[] dx = {0, 0, -1, 1};
        int[] dy = {-1, 1, 0, 0};

        while (!qu.isEmpty()) {

            Node nd = qu.poll();
            if (nd.x == eX && nd.y == eY) {
                return nd.cnt;
            }

            for (int i = 0; i < 4; i++) {
                int nx = nd.x + dx[i];
                int ny = nd.y + dy[i];

                if (isIn(nx, ny)) {
                    if (arr1[ny][nx] == 0 && !vi[nd.z][ny][nx]) {
                        vi[nd.z][ny][nx] = true;
                        qu.offer(new Node(nx, ny, nd.z, nd.cnt + 1));

                    } else if (arr1[ny][nx] == 1 && nd.z == 0 && !vi[nd.z + 1][ny][nx]) {
                        vi[nd.z + 1][ny][nx] = true;
                        qu.offer(new Node(nx, ny, nd.z + 1, nd.cnt + 1));
                    }
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr1 = new int[N][M];
        vi = new boolean[2][N][M];

        st = new StringTokenizer(br.readLine());
        int sY = Integer.parseInt(st.nextToken()) - 1;
        int sX = Integer.parseInt(st.nextToken()) - 1;

        st = new StringTokenizer(br.readLine());
        int eY = Integer.parseInt(st.nextToken()) - 1;
        int eX = Integer.parseInt(st.nextToken()) - 1;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr1[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(bfs(sX, sY, eX, eY));
    }
}