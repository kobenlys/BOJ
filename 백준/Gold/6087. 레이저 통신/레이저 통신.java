import java.io.*;
import java.util.*;

public class Main {
    public static int N, M;
    public static char[][] arr1;
    public static int[][][] vi;

    public static class Node{
        int x, y, before;

        public Node(int x, int y, int before) {
            this.x = x;
            this.y = y;
            this.before = before;
        }
    }

    public static int mirrorCalc(int dir, int beforeDir) {
        if ((beforeDir == 0 || beforeDir == 1) && (dir == 2 || dir == 3)) {
            return 1;
        }
        if ((beforeDir == 2 || beforeDir == 3) && (dir == 0 || dir == 1)) {
            return 1;
        }
        return 0;
    }

    public static void bfs(int sX, int sY) {
        Deque<Node> dq = new ArrayDeque<>();
        dq.offer(new Node(sX, sY, 4));

        int[] dx = {0, 0, -1, 1};
        int[] dy = {-1, 1, 0, 0};

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                for (int k = 0; k < 4; k++) {
                    vi[i][j][k] = Integer.MAX_VALUE;
                }
            }
        }
        vi[sY][sX][4] = 0;

        while (!dq.isEmpty()) {

            Node nd = dq.pollFirst();
            for (int i = 0; i < 4; i++) {
                int nx = nd.x + dx[i];
                int ny = nd.y + dy[i];
                int weight = mirrorCalc(i, nd.before);
                if(nx < 0 || ny < 0 || nx >= M || ny >= N) continue;

                if (arr1[ny][nx] != '*' && vi[ny][nx][i] > vi[nd.y][nd.x][nd.before] + weight) {
                    vi[ny][nx][i] = vi[nd.y][nd.x][nd.before] + weight;
                    if (weight == 0) {
                        dq.offerFirst(new Node(nx, ny, i));
                    }else{
                        dq.offerLast(new Node(nx, ny, i));
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        arr1 = new char[N][M];
        vi = new int[N][M][5];

        int sX = -1, sY = 0;
        int eX = 0, eY = 0;

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                arr1[i][j] = str.charAt(j);
                if (arr1[i][j] == 'C') {
                    if (sX == -1) {
                        sX = j;
                        sY = i;
                    }else{
                        eX = j;
                        eY = i;
                    }
                }
            }
        }

        bfs(sX, sY);
        int answer = Integer.MAX_VALUE;
        for (int i = 0; i < 4; i++) {
            if(vi[eY][eX][i] == Integer.MAX_VALUE) continue;
            answer = Math.min(answer, vi[eY][eX][i]);
        }
        System.out.println(answer);
    }
}