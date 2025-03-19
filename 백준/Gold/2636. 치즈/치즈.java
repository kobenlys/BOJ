import java.io.*;
import java.util.*;

public class Main {
    public static int N, M;
    public static boolean[][] arr1;

    public static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static int bfs() {
        Queue<Node> qu = new ArrayDeque<>();
        boolean[][] vi = new boolean[N + 2][M + 2];
        qu.offer(new Node(0, 0));

        int[] dx = {0, 0, -1, 1};
        int[] dy = {-1, 1, 0, 0};
        int res = 0;

        while (!qu.isEmpty()) {
            Node nd = qu.poll();

            for (int i = 0; i < 4; i++) {
                int nx = nd.x + dx[i];
                int ny = nd.y + dy[i];
                if (nx < 0 || ny < 0 || nx >= M + 2 || ny >= N + 2) continue;

                if (!vi[ny][nx]) {
                    vi[ny][nx] = true;

                    if (arr1[ny][nx]) {
                        res++;
                        arr1[ny][nx] = false;
                        continue;
                    }

                    qu.offer(new Node(nx, ny));
                }
            }
        }
        return res;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr1 = new boolean[N + 2][M + 2];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {
                arr1[i][j] = Integer.parseInt(st.nextToken()) == 1;
            }
        }
        int count = 0;
        int answer = 0;
        while (true) {
            count++;
            int res = bfs();

            if (res == 0) break;
            answer = res;
        }
        System.out.println(count-1);
        System.out.println(answer);
    }
}