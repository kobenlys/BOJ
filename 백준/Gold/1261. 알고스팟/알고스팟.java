import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int N, M;
    public static int[][] arr1;
    public static int[][] vi;
    public static Queue<node> qu = new LinkedList<>();

    public static class node {
        int x, y;

        public node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void BFS() {
        int[] dx = {0, 0, -1, 1};
        int[] dy = {-1, 1, 0, 0};
        vi[0][0] = 1;
        qu.offer(new node(0, 0));

        while (!qu.isEmpty()) {

            node nd = qu.poll();

            if (nd.x == M - 1 && nd.y == N - 1) {
                continue;
            }

            for (int i = 0; i < 4; i++) {
                int nx = nd.x + dx[i];
                int ny = nd.y + dy[i];

                if (nx < 0 || ny < 0 || nx >= M || ny >= N) continue;


                if (arr1[ny][nx] == 0) {

                    if (vi[ny][nx] == 0) {
                        vi[ny][nx] = vi[nd.y][nd.x];
                        qu.offer(new node(nx, ny));
                        continue;
                    }

                    if (vi[nd.y][nd.x] < vi[ny][nx]) {
                        vi[ny][nx] = vi[nd.y][nd.x];
                        qu.offer(new node(nx, ny));
                    }
                } else {

                    if (vi[ny][nx] == 0) {
                        vi[ny][nx] = vi[nd.y][nd.x] + 1;
                        qu.offer(new node(nx, ny));
                        continue;
                    }

                    if (vi[nd.y][nd.x] + 1 < vi[ny][nx]) {
                        vi[ny][nx] = vi[nd.y][nd.x] + 1;
                        qu.offer(new node(nx, ny));
                    }

                }
            }
        }
    }


    public static void main(String[] args) throws IOException { //조건 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        arr1 = new int[N][M];
        vi = new int[N][M];

        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                arr1[i][j] = Character.getNumericValue(input.charAt(j));
            }
        }
        BFS();
        System.out.print(vi[N - 1][M - 1] - 1);
    }
}