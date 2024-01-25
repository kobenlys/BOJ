import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int N, cnt;
    public static boolean[][] arr1;
    public static Queue<node> qu = new LinkedList<>();

    public static class node {
        int x, y,shape;
        public node(int x, int y, int shape) {
            this.x = x;
            this.y = y;
            this.shape = shape;
        }
    }

    public static void bfs(){
        // shape = 0(가로), 1(세로), 2(대각선)
        int[] dx = {1, 0, 1};
        int[] dy = {0, 1, 1};
        qu.offer(new node(1, 0, 0));

        while (!qu.isEmpty()) {
            node nd = qu.poll();

            if ( nd.x== N - 1 && nd.y == N - 1) {
                cnt++;
                continue;
            }

            for (int i = 0; i < 3; i++) {
                int nx = nd.x + dx[i];
                int ny = nd.y + dy[i];
                // 범위체크
                if(nx < 0 || ny < 0 || nx >= N || ny >= N) continue;

                if (!arr1[ny][nx]) { // 이동범위에 벽이 없다면 진행
                    // 대각선 이동시 벽에 닿는지 체크
                    if(i == 2 && isWall(nx,ny)) continue;

                    if (nd.shape == 0) { // 저번 턴에 가로로 이동했다면
                        // 가로, 대각선으로만 이동가능
                        if (i == 0 || i == 2) {
                            qu.offer(new node(nx, ny, i));
                        }
                    } else if (nd.shape == 1) { // 저번 턴에 세로로 이동했다면
                        if (i == 1 || i == 2) {
                            // 세로, 대각선으로만 이동가능
                            qu.offer(new node(nx, ny, i));
                        }
                    } else {
                        qu.offer(new node(nx, ny, i));
                    }
                }
            }
        }
    }

    // 대각선 이동시 벽에 닿는지 체크
    public static boolean isWall(int x, int y) {
        int[] dx = {0, -1};
        int[] dy = {-1, 0};
        for (int i = 0; i < 2; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(nx < 0 || ny < 0 || nx >= N || ny >= N) continue;

            if (arr1[ny][nx]) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException { //조건 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        arr1 = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                if (st.nextToken().equals("1")) {
                    arr1[i][j] = true;
                }
            }
        }
        // 목적지에 벽이 있는 경우 제외
        if (arr1[N - 1][N - 1]) {
            System.out.print(0);
        } else {
            bfs();
            System.out.println(cnt);
        } 
    }
}
