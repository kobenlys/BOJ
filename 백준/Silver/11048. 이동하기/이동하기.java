import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int N, M;
    public static int[][] arr1;
    public static int[][] vi;

    public static class node {
        int x, y;

        public node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static int bfs() {
        Queue<node> qu = new LinkedList<>();
        int[] dx = {0, 1, 1};
        int[] dy = {1, 0, 1};

        qu.offer(new node(0, 0));
        // 너비 우선 탐색 알고리즘
        while (!qu.isEmpty()) {

            node nd = qu.poll();

            for (int i = 0; i < 3; i++) {
                int nx = nd.x + dx[i];
                int ny = nd.y + dy[i];

                if (nx < 0 || ny < 0 || nx >= M || ny >= N) continue;
                // vi[ny][nx] 보다 다음 자리 + 현재 자리가 크다면 연산 후 큐 입력
                if (vi[ny][nx] == -1 || vi[ny][nx] < arr1[ny][nx] + vi[nd.y][nd.x]) {
                    vi[ny][nx] = arr1[ny][nx] + vi[nd.y][nd.x];
                    qu.offer(new node(nx, ny));
                }
            }
        }
        return vi[N - 1][M - 1];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr1 = new int[N][M];
        vi = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr1[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            Arrays.fill(vi[i], -1);
        }

        vi[0][0] = arr1[0][0]; // 초기값 설정
        System.out.println(bfs());
    }
}