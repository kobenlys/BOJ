import java.io.*;
import java.util.*;

public class Main {
    public static int N, M;
    public static char[][] arr1;
    public static boolean[][] vi;
    public static boolean[][] fireMap;
    public static Queue<Node> qu = new ArrayDeque<>();
    public static Queue<Node> fireQu = new ArrayDeque<>();

    public static class Node {
        int x, y, step;

        public Node(int x, int y, int step) {
            this.x = x;
            this.y = y;
            this.step = step;
        }
    }

    public static int isPossibleEscape() {
        int[] dy = {-1, 1, 0, 0};
        int[] dx = {0, 0, -1, 1};

        while (!qu.isEmpty()) {

            int size = qu.size();

            for (int tc = 0; tc < size; tc++) {
                Node nd = qu.poll();

                if (fireMap[nd.y][nd.x]) {
                    continue;
                }

                for (int i = 0; i < 4; i++) {
                    int nx = nd.x + dx[i];
                    int ny = nd.y + dy[i];
                    if (nx < 0 || ny < 0 || nx >= M || ny >= N) {
                        return nd.step + 1;
                    }

                    if (!vi[ny][nx] && arr1[ny][nx] == '.') {
                        vi[ny][nx] = true;
                        qu.offer(new Node(nx, ny, nd.step + 1));
                    }
                }
            }

            size = fireQu.size();

            for (int tc = 0; tc < size; tc++) {

                Node nd = fireQu.poll();

                for (int i = 0; i < 4; i++) {
                    int nx = nd.x + dx[i];
                    int ny = nd.y + dy[i];
                    if (nx < 0 || ny < 0 || nx >= M || ny >= N) continue;
                    if (!fireMap[ny][nx] && arr1[ny][nx] == '.') {
                        fireMap[ny][nx] = true;
                        fireQu.offer(new Node(nx, ny, nd.step + 1));
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

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {

            st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());

            arr1 = new char[N][M];
            vi = new boolean[N][M];
            fireMap = new boolean[N][M];
            qu.clear();
            fireQu.clear();

            for (int i = 0; i < N; i++) {
                String str = br.readLine();
                for (int j = 0; j < M; j++) {
                    arr1[i][j] = str.charAt(j);
                    if (arr1[i][j] == '*') {
                        fireQu.offer(new Node(j, i, 0));
                        fireMap[i][j] = true;
                    }

                    if (arr1[i][j] == '@') {
                        qu.offer(new Node(j, i, 0));
                        vi[i][j] = true;
                    }
                }
            }
            int answer = isPossibleEscape();
            sb.append(answer == -1 ? "IMPOSSIBLE" : answer).append("\n");

        }

        System.out.println(sb);
    }
}