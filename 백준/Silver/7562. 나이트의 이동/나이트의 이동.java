import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int N, coordix, coordiy;
    public static boolean[][] vi;
    public static int[][] arr1;
    public static Queue<node> qu = new LinkedList<>();
    
    // 범위 체크
    public static boolean range(int x, int y) {
        return x >= 0 && y >= 0 && x < N && y < N;
    }
    
    // 좌표 x,y 이동횟수 step 저장하는 객체
    public static class node {
        int x;
        int y;
        int step;

        public node(int x, int y, int step) {
            this.x = x;
            this.y = y;
            this.step = step;
        }
    }

    public static void bfs() {
        // 나이트 이동 범위 총 8가지 이다.
        int[] dx = {-2, -1, 1, 2, -2, -1, 1, 2};
        int[] dy = {-1, -2, -2, -1, 1, 2, 2, 1};

        while (!qu.isEmpty()) { // BFS 알고리즘 
            node nd = qu.poll();
            
            // 모든 경로 탐색
            for (int i = 0; i < 8; i++) {
                int nx = nd.x + dx[i];
                int ny = nd.y + dy[i];
                int ns = nd.step + 1;

                if (range(nx, ny)) {

                    if (!vi[ny][nx]) {
                        qu.offer(new node(nx, ny, ns));
                        vi[ny][nx] = true;
                        if (nx == coordix && ny == coordiy) {
                            // 목표 좌표 도착 시 걸린 step 출력 후 break;
                            System.out.println(ns);
                            qu.clear();
                            break;
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int tcase = 0; tcase < T; tcase++) {

            N = Integer.parseInt(br.readLine());
            arr1 = new int[N][N];
            vi = new boolean[N][N];

            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            qu.offer(new node(x, y, 0));


            st = new StringTokenizer(br.readLine(), " ");
            coordix = Integer.parseInt(st.nextToken());
            coordiy = Integer.parseInt(st.nextToken());
            
            // 나이트의 현재위치 == 목표위치 일때 예외처리
            if (x == coordix && y == coordiy) {
                System.out.println(0);
                qu.clear();
                continue;
            }

            bfs();

        }
    }
}
