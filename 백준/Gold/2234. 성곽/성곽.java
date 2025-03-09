import java.io.*;
import java.util.*;

public class Main {
    public static int N, M;
    public static int[][] arr1;
    public static int[][] area;
    public static int[][] vi;

    public static int[] dx = {-1, 0, 1, 0};
    public static int[] dy = {0, -1, 0, 1};

    public static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void bfs(int x, int y, int viIndex) {
        List<Node> list = new ArrayList<>();
        Queue<Node> qu = new ArrayDeque<>();
        qu.offer(new Node(x, y));
        list.add(new Node(x, y));
        int result = 1;

        while (!qu.isEmpty()) {
            Node nd = qu.poll();
            int pos = arr1[nd.y][nd.x];

            for (int i = 0; i < 4; i++) {
                if ((pos & (1 << i)) == 0) {
                    int nx = nd.x + dx[i];
                    int ny = nd.y + dy[i];
                    if (nx < 0 || ny < 0 || nx >= M || ny >= N) continue;

                    if (vi[ny][nx] == 0) {
                        vi[ny][nx] = viIndex;
                        result++;
                        list.add(new Node(nx, ny));
                        qu.offer(new Node(nx, ny));
                    }
                }
            }
        }

        for (Node nd : list) {
            area[nd.y][nd.x] = result;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        arr1 = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr1[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int roomCnt = 0;
        int maxWidth = 0;
        int usingChance = 0;
        vi = new int[N][M];
        area = new int[N][M];

        int index = 1;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (vi[i][j] == 0) {
                    vi[i][j] = index;
                    roomCnt++;
                    bfs(j, i, index);
                    index++;
                    maxWidth = Math.max(maxWidth, area[i][j]);
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (arr1[i][j] != 0) {
                    int pos = arr1[i][j];

                    for (int k = 0; k < 4; k++) {
                        if ((pos & (1 << k)) != 0) {
                            int nx = j + dx[k];
                            int ny = i + dy[k];
                            if (nx < 0 || ny < 0 || nx >= M || ny >= N) continue;
                            if (vi[i][j] == vi[ny][nx]) continue;
                            usingChance = Math.max(usingChance, area[i][j] + area[ny][nx]);
                        }
                    }
                }
            }
        }

        if(usingChance == 0) usingChance = area[0][0];
        sb.append(roomCnt).append("\n").append(maxWidth).append("\n").append(usingChance);
        System.out.println(sb);
    }
}