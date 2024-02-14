import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int X, Y;
    public static int[][] arr1;
    public static boolean[][] vi;
    public static Queue<node> qu = new LinkedList<node>();

    public static class node {
        int x, y;

        public node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void bfs() {
        int[] dx = {0, 0, -1, 1, -1, -1, 1, 1};
        int[] dy = {-1, 1, 0, 0, -1, 1, -1, 1};

        while (!qu.isEmpty()) {

            node nd = qu.poll();

            for (int i = 0; i < 8; i++) {
                int nx = nd.x + dx[i];
                int ny = nd.y + dy[i];

                if(nx < 0 || ny < 0 || nx >= X || ny >= Y) continue;

                if (!vi[ny][nx]) {

                    if (arr1[ny][nx] == 1) {
                        vi[ny][nx] = true;
                        qu.offer(new node(nx,ny));
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException { //조건 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        ArrayList<node> land;
        StringBuilder sb = new StringBuilder();

        while (true) {

            st = new StringTokenizer(br.readLine(), " ");
            X = Integer.parseInt(st.nextToken());
            Y = Integer.parseInt(st.nextToken());
            if (X == 0 && Y == 0) break;

            arr1 = new int[Y][X];
            vi = new boolean[Y][X];
            land = new ArrayList<>();

            for (int i = 0; i < Y; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < X; j++) {
                    arr1[i][j] = Integer.parseInt(st.nextToken());
                    if (arr1[i][j] == 1) {
                        land.add(new node(j, i));
                    }
                }
            }

            if (!land.isEmpty()) {
                int cnt = 0;
                for (int i = 0; i < land.size(); i++) {
                    int siteX = land.get(i).x;
                    int siteY = land.get(i).y;
                    if (!vi[siteY][siteX]) {
                        cnt++;
                        qu.offer(new node(siteX, siteY));
                        bfs();
                    }
                }
                sb.append(cnt).append("\n");
            } else {
                sb.append(0).append("\n");
            }
        }
        System.out.print(sb);
    }
}