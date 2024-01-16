import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int N, M, cnt;
    public static boolean[][] arr1;
    public static boolean[][] vi;
    public static Queue<node> qu = new LinkedList<>();

    public static class node { // 현재 청소기 좌표 담는 객체
        int x, y, dir;
        public node(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
    }
    public static boolean range(int x, int y) { // 범위체크
        return x >= 0 && y >= 0 && x < M && y < N;
    }

    public static int dirAction(int dir) {
        // 반시계 방향으로 회전.
        if (dir == 0) { // 북
            return 3;
        } else if (dir == 1) { // 동
            return 0;
        } else if (dir == 2) { // 남
            return 1;
        } else { // 서
            return 2;
        }
    }

    public static boolean isClean(int x, int y) {
        // 청소 안한 빈칸 찾기
        int[] dx = {0, 0, -1, 1};
        int[] dy = {-1, 1, 0, 0};

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (range(nx, ny) && !vi[ny][nx] && !arr1[ny][nx]) {
                return true;
            }
        }
        return false;
    }

    public static int changeCordi(int num) {
        // 현재 보고있는방향 반대로 뒤로 한칸
        if (num == 1) {
            return -1;
        } else if (num == -1) {
            return 1;
        }
        return 0;
    }

    public static void bfs(int startX, int startY, int d) {
        // 북, 동, 남, 서
        int[] dx = {0, 1, 0, -1};
        int[] dy = {-1, 0, 1, 0};
        qu.offer(new node(startX, startY, d));
        cnt++;
        vi[startY][startX] = true;

        while (!qu.isEmpty()) { // BFS 알고리즘
            node nd = qu.poll();

            if (isClean(nd.x, nd.y)) { // 주변에 청소안한 공간 있는 경우
                int nDir = dirAction(nd.dir); // 90도 반시계방향으로 돌리기
                int nx = nd.x + dx[nDir];
                int ny = nd.y + dy[nDir];

                if (range(nx, ny) && !arr1[ny][nx] && !vi[ny][nx]) {
                    // 돌린 방향이 청소가 안되어 있다면 청소하기.
                    vi[ny][nx] = true;
                    cnt++;
                    qu.offer(new node(nx, ny, nDir));
                } else {
                    // 돌린 방향이 벽 또는 청소가 되어있다면, 다음 행동하기.
                    qu.offer(new node(nd.x, nd.y, nDir));
                }

            } else { // 주변에 청소할 곳이 없다, 뒤로 한칸
                int nx = nd.x + changeCordi(dx[nd.dir]);
                int ny = nd.y + changeCordi(dy[nd.dir]);

                if (!range(nx, ny) || arr1[ny][nx]) {
                    // 뒤로 한칸시 벽에 닿았다면 청소기 종료
                    break;
                } else {
                    // 뒤로 한칸
                    qu.offer(new node(nx, ny, nd.dir));
                }
            }

        }
    }

    public static void main(String[] args) throws IOException { //조건 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine(), " ");
        int y = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int dir = Integer.parseInt(st.nextToken());

        arr1 = new boolean[N][M];
        vi = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                if (Integer.parseInt(st.nextToken()) == 1) {
                    arr1[i][j] = true;
                }
            }
        }

        bfs(x, y, dir); //함수 호출, 출력
        System.out.println(cnt);
    }
}
