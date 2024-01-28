import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int N, M, max;
    public static boolean[][] arr1;
    public static boolean[][] vi;
    public static Queue<node> qu = new LinkedList<>();

    public static class node { // x,y 좌표와 걸리는 시간 
        int x, y, time;

        public node(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }

    public static void bfs(int x, int y) { // BFS 알고리즘
        int[] dx = {0, 0, -1, 1};
        int[] dy = {-1, 1, 0, 0};
        qu.offer(new node(x, y, 0));
        vi[y][x] = true;

        while (!qu.isEmpty()) {

            node nd = qu.poll();
            max = Math.max(max, nd.time);
            for (int i = 0; i < 4; i++) {
                int nx = nd.x + dx[i];
                int ny = nd.y + dy[i];

                if( nx < 0 || ny < 0 || nx >= M || ny >= N) continue;

                if (!vi[ny][nx] && arr1[ny][nx]) {
                    vi[ny][nx] = true;
                    qu.offer(new node(nx, ny, nd.time + 1));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException { //조건 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        ArrayList<node> coordi = new ArrayList<>();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr1 = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                arr1[i][j] = str.charAt(j) == 'L';
                if (arr1[i][j]) {
                    coordi.add(new node(j, i, 0));
                }
            }
        }
        // 모든 육지 좌표를 기준으로 max 값을 찾는다.
        for (int i = 0; i < coordi.size(); i++) {
            vi = new boolean[N][M];
            node site = coordi.get(i);
            bfs(site.x, site.y);
        }
        System.out.println(max);
    }
}
