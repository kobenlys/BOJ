import java.io.*;
import java.util.*;

public class Main {
    public static int N, M, K;
    public static node[][] arr1;

    public static class node {
        int x, y, speed, dir, val;

        public node(int x, int y, int speed, int dir, int val) {
            this.x = x;
            this.y = y;
            this.speed = speed;
            this.dir = dir;
            this.val = val;
        }
    }


    public static int findShark(int x) {

        for (int i = 0; i < N; i++) {
            if (arr1[i][x].val != 0) {
                node nd = arr1[i][x];
                arr1[i][x] = new node(0, 0, 0, 0, 0);
                return nd.val;
            }
        }
        return 0;
    }


    public static void moveShark() {
        // 0 : up, 1 : down, 2 : right, 3: left
        int[] dx = {0, 0, 1, -1};
        int[] dy = {-1, 1, 0, 0};
        ArrayList<node> list = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {

                node nd = arr1[i][j];
                if (nd.val == 0) continue;
                arr1[i][j] = new node(0, 0, 0, 0, 0);
                int tmpSpeed = nd.speed;
                int nowDir = nd.dir;
                int ny = i;
                int nx = j;

                // up, down
                if (nd.dir == 0 || nd.dir == 1) {
                    while (tmpSpeed > 0) {

                        int tmp = ny;
                        ny += dy[nowDir];

                        if (ny < 0 || ny >= N) {
                            ny = tmp;
                            nowDir = nowDir == 0 ? 1 : 0;
                            continue;
                        }
                        tmpSpeed--;
                    }
                }

                // left, right
                if (nd.dir == 2 || nd.dir == 3) {

                    while (tmpSpeed > 0) {
                        int tmp = nx;
                        nx += dx[nowDir];

                        if (nx < 0 || nx >= M) {
                            nx = tmp;
                            nowDir = nowDir == 2 ? 3 : 2;
                            continue;
                        }
                        tmpSpeed--;
                    }
                }

                list.add(new node(nx, ny, nd.speed, nowDir, nd.val));
            }
        }

        list.sort((o1, o2) -> o2.val - o1.val);

        for (node nd : list) {
            if (arr1[nd.y][nd.x].val != 0) continue;
            arr1[nd.y][nd.x] = new node(nd.x, nd.y, nd.speed, nd.dir, nd.val);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr1 = new node[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                arr1[i][j] = new node(0, 0, 0, 0, 0);
            }
        }

        while (K-- > 0) {

            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken()) - 1;
            int x = Integer.parseInt(st.nextToken()) - 1;
            int speed = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken()) - 1;
            int val = Integer.parseInt(st.nextToken());

            arr1[y][x] = new node(x, y, speed, dir, val);
        }

        int answer = 0;
        for (int i = 0; i < M; i++) {
            answer += findShark(i);
            moveShark();
        }
        System.out.println(answer);
    }
}