import java.io.*;
import java.util.*;

public class Main {
    public static int N, M;
    public static char[][] arr1;
    public static boolean[][] vi;
    public static List<List<Node>> list;

    public static int[] dx = {0, 0, -1, 1};
    public static int[] dy = {-1, 1, 0, 0};


    public static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static int[][] BFS0_1(int x, int y) {
        Deque<Node> dq = new ArrayDeque<>();
        int[][] dist = new int[N + 2][M + 2];
        vi = new boolean[N + 2][M + 2];

        for (int i = 0; i <= N; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }

        dq.offer(new Node(x, y));
        dist[y][x] = 0;
        vi[y][x] = true;

        while (!dq.isEmpty()) {
            Node nd = dq.pollFirst();

            for (int i = 0; i < 4; i++) {
                int nx = nd.x + dx[i];
                int ny = nd.y + dy[i];
                if (nx < 0 || ny < 0 || nx >= M + 2 || ny >= N + 2) continue;

                if (arr1[ny][nx] != '*' && !vi[ny][nx]) {

                    if (arr1[ny][nx] == '#') {
                        dist[ny][nx] = dist[nd.y][nd.x] + 1;
                        vi[ny][nx] = true;
                        dq.offerLast(new Node(nx, ny));
                        continue;
                    }

                    if (arr1[ny][nx] == '.' || arr1[ny][nx] == '$') {
                        dist[ny][nx] = dist[nd.y][nd.x];
                        vi[ny][nx] = true;
                        dq.offerFirst(new Node(nx, ny));

                    }
                }
            }
        }
        return dist;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            int x1 = -1, x2 = -1;
            int y1 = -1, y2 = -1;

            arr1 = new char[N + 2][M + 2];

            for (int i = 0; i <= N + 1; i++) {
                Arrays.fill(arr1[i], '.');
            }


            for (int i = 1; i <= N; i++) {
                String input = br.readLine();
                for (int j = 1; j <= M; j++) {
                    arr1[i][j] = input.charAt(j - 1);
                    if (x1 == -1 && arr1[i][j] == '$') {
                        x1 = j;
                        y1 = i;
                    } else if (arr1[i][j] == '$') {
                        x2 = j;
                        y2 = i;
                    }
                }
            }

            int answer = Integer.MAX_VALUE;
            int[][] dist1 = BFS0_1(x1, y1);
            int[][] dist2 = BFS0_1(x2, y2);
            int[][] dist3 = BFS0_1(0, 0);

            for (int i = 1; i <= N+1; i++) {
                for (int j = 1; j <= M+1; j++) {

                    int num = dist1[i][j] + dist2[i][j] + dist3[i][j];
                    if (arr1[i][j] == '#') {
                        num -= 2;
                    }
                    answer = Math.min(answer, num);
                }
            }
            sb.append(answer).append("\n");
        }
        System.out.println(sb);
    }
}