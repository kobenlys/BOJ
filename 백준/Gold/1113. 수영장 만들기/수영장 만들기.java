import java.io.*;
import java.util.*;

public class Main {
    public static int N, M;
    public static int[][] arr1;

    public static class node {
        int x, y;

        public node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static int bfs(int x, int y) {
        Queue<node> qu = new ArrayDeque<>();
        qu.offer(new node(x, y));
        arr1[y][x]++;

        int[] dx = {0, 0, -1, 1};
        int[] dy = {-1, 1, 0, 0};
        boolean isUpdate = true;
        int cnt = 1;

        while (!qu.isEmpty()) {
            node nd = qu.poll();

            for (int i = 0; i < 4; i++) {
                int nx = nd.x + dx[i];
                int ny = nd.y + dy[i];
                if (nx < 0 || ny < 0 || nx >= M || ny >= N) {
                    isUpdate = false;
                    continue;
                }

                if (arr1[ny][nx] + 1 == arr1[nd.y][nd.x]) {
                    arr1[ny][nx]++;
                    cnt++;
                    qu.offer(new node(nx, ny));
                }
            }
        }

        return isUpdate ? cnt : 0;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr1 = new int[N][M];
        int s = 10, e = 0;
        int answer = 0;

        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                arr1[i][j] = input.charAt(j) - '0';

                s = Math.min(s, arr1[i][j]);
                e = Math.max(e, arr1[i][j]);
            }
        }

        while (s <= e) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (s == arr1[i][j]) {
                        answer += bfs(j, i);
                    }
                }
            }
            s++;
        }

        System.out.println(answer);
    }
}