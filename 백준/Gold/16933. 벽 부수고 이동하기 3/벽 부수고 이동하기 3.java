import java.io.*;
import java.util.*;

public class Main {
    public static int N, M, K;
    public static int[][] arr1;

    public static class Node {
        int x, y, len, pos;
        boolean dayTime;

        public Node(int x, int y, int len, int pos, boolean dayTime) {
            this.x = x;
            this.y = y;
            this.len = len;
            this.pos = pos;
            this.dayTime = dayTime;
        }
    }

    public static int bfs() {
        int[] dx = {0, 0, -1, 1};
        int[] dy = {-1, 1, 0, 0};
        Queue<Node> qu = new ArrayDeque<>();
        qu.offer(new Node(0, 0, 1, 0, true));
        boolean[][][] vi = new boolean[N][M][K + 1];

        int answer = Integer.MAX_VALUE;

        for (int i = 0; i <= K; i++) {
            vi[0][0][i] = true;

        }

        while (!qu.isEmpty()) {

            Node nd = qu.poll();

            for (int i = 0; i < 4; i++) {
                int nx = nd.x + dx[i];
                int ny = nd.y + dy[i];
                if (nx < 0 || ny < 0 || nx >= M || ny >= N) continue;

                if (ny == N - 1 && nx == M - 1) {
                    answer = Math.min(answer, nd.len + 1);
                    continue;
                }

                // 낮
                if (nd.dayTime) {

                    if (arr1[ny][nx] == 0 && !vi[ny][nx][nd.pos]) {
                        vi[ny][nx][nd.pos] = true;
                        qu.offer(new Node(nx, ny, nd.len + 1, nd.pos, false));
                        continue;
                    }

                    if (arr1[ny][nx] == 1 && nd.pos + 1 <= K && !vi[ny][nx][nd.pos + 1]) {
                        vi[ny][nx][nd.pos + 1] = true;
                        qu.offer(new Node(nx, ny, nd.len + 1, nd.pos + 1, false));
                    }
                } else { // 밤

                    if (arr1[ny][nx] == 0 && !vi[ny][nx][nd.pos]) {
                        vi[ny][nx][nd.pos] = true;
                        qu.offer(new Node(nx, ny, nd.len + 1, nd.pos, true));
                        continue;
                    }

                    if (arr1[ny][nx] == 1 && nd.pos < K && !vi[ny][nx][nd.pos + 1]) {
                        qu.offer(new Node(nd.x, nd.y, nd.len + 1, nd.pos, true));
                    }
                }
            }
        }

        return answer == Integer.MAX_VALUE ? -1 : answer;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr1 = new int[N][M];


        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                arr1[i][j] = str.charAt(j) - '0';
            }
        }
        
        if (N == 1 && M == 1) {
            System.out.println(1);
            System.exit(0);
        }

        
        System.out.println(bfs());
    }
}