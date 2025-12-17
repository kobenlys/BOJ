import java.io.*;
import java.util.*;

public class Main {

    public static int N, M;
    public static int[][] arr1;

    public static class Node {

        int x, y, dir, value;

        public Node(int x, int y, int dir, int value) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.value = value;
        }
    }

    public static int bfs() {

        int[] dx = {-1, 0, 1};
        int answer = Integer.MAX_VALUE;
        Queue<Node> qu = new ArrayDeque<>();

        for (int i = 0; i < M; i++) {
            qu.offer(new Node(i, -1, -1, 0));
        }

        while (!qu.isEmpty()) {
            Node nd = qu.poll();

            for (int i = 0; i < 3; i++) {
                int nx = nd.x + dx[i];
                int ny = nd.y + 1;

                if (ny == N) {
                    answer = Math.min(answer, nd.value);
                    continue;
                }

                if (nx < 0 || nx >= M) {
                    continue;
                }

                if (i != nd.dir) {
                    qu.offer(new Node(nx, ny, i, nd.value + arr1[ny][nx]));
                }
            }
        }

        return answer;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr1 = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr1[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(bfs());

    }
}