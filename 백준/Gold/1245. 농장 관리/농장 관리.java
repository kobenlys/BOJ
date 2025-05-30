import java.io.*;
import java.util.*;

public class Main {

    public static int N, M;
    public static int[][] arr1;
    public static boolean[][] vi;
    public static int[] dx = {0, 0, -1, 1, -1, 1, -1, 1};
    public static int[] dy = {-1, 1, 0, 0, -1, -1, 1, 1};

    public static class Node {

        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static boolean isIsland(int x, int y) {
        vi[y][x] = true;
        int baseNumber = arr1[y][x];
        Queue<Node> qu = new ArrayDeque<>();
        qu.offer(new Node(x, y));

        boolean isIsland = true;

        while (!qu.isEmpty()) {
            Node nd = qu.poll();

            for (int i = 0; i < 8; i++) {
                int nx = nd.x + dx[i];
                int ny = nd.y + dy[i];
                if (nx < 0 || ny < 0 || nx >= M || ny >= N) {
                    continue;
                }

                if (!vi[ny][nx] && baseNumber == arr1[ny][nx]) {
                    vi[ny][nx] = true;
                    qu.offer(new Node(nx, ny));
                } else if (baseNumber < arr1[ny][nx]) {
                    isIsland = false;
                }
            }
        }

        return isIsland;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr1 = new int[N][M];
        vi = new boolean[N][M];
        int answer = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr1[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!vi[i][j] && arr1[i][j] > 0) {
                    if (isIsland(j, i)) {
                        answer++;
                    }
                }
            }
        }

        System.out.println(answer);
    }
}