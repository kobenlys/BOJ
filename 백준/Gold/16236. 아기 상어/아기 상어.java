import java.io.*;
import java.util.*;

public class Main {
    public static int N;
    public static int[][] arr1;
    public static PriorityQueue<node> pq = new PriorityQueue<>();
    public static Queue<node> qu = new ArrayDeque<>();
    
    // 물고기 담는 객체
    public static class node implements Comparable<node> {
        int x, y, nowSize, eatCnt, step;

        public node(int x, int y, int nowSize, int eatCnt, int step) {
            this.x = x;
            this.y = y;
            this.nowSize = nowSize;
            this.eatCnt = eatCnt;
            this.step = step;
        }

        @Override
        public int compareTo(node o) {

            if (step == o.step) { // 첫번째 우선순위 : 최단거리
                if (y == o.y) { // 두번째 우선순위 : 가장 위에 있는 상어
                    return x - o.x; // 3번째 우선순위 : 가장 왼쪽에 있는 상어
                }
                return y - o.y;
            }
            return step - o.step;
        }
    }

    public static void bfs() {
        int[] dx = {0, 0, -1, 1};
        int[] dy = {-1, 1, 0, 0};
        boolean[][] vi = new boolean[N][N];

        while (!qu.isEmpty()) {

            node nd = qu.poll();

            for (int i = 0; i < 4; i++) {
                int nx = nd.x + dx[i];
                int ny = nd.y + dy[i];
                int nStep = nd.step + 1;
                if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;


                if (!vi[ny][nx]) {
                    
                    // 상어는 빈칸과 크기가 같은 물고기가 있는 칸을 지나갈 수 있음
                    if (arr1[ny][nx] == 0 || arr1[ny][nx] == nd.nowSize) {
                        vi[ny][nx] = true;
                        qu.offer(new node(nx, ny, nd.nowSize, nd.eatCnt, nStep));
                        continue;
                    }
                    
                    // 크기가 나보다 작은 물고기를 먹는다
                    if (arr1[ny][nx] != 0 && arr1[ny][nx] < nd.nowSize) {
                        // 가장 우선순위인 물고기를 판별하기 위해 우선순위 큐에 담는다.
                        pq.offer(new node(nx, ny, nd.nowSize, nd.eatCnt, nStep));
                        vi[ny][nx] = true;
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        int day = 0;
        arr1 = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr1[i][j] = Integer.parseInt(st.nextToken());
                if (arr1[i][j] == 9) {
                    arr1[i][j] = 0;
                    qu.offer(new node(j, i, 2, 0, 0));
                }
            }
        }

        while (true) {
            bfs();

            if (pq.isEmpty()) { // 더이상 먹을 상어가 없다면 출력 후 종료
                System.out.println(day);
                System.exit(0);
            }

            node shark = pq.poll();
            arr1[shark.y][shark.x] = 0; // 먹은 물고기 제거
            shark.eatCnt += 1; // 가장 우선순위를 가지는 상어 물고기 먹음 처리

            if (shark.nowSize == shark.eatCnt) { // 상어크기 == 물고기 먹은 개수 같다면
                shark.nowSize += 1; // 사이즈 키우기
                shark.eatCnt = 0;
            }

            day += shark.step; // 1칸당 1초 == 최단거리 는 걸리는 시간
            shark.step = 0;
            qu.offer(shark); // 다음 상어BFS 탐색으로 먹을 수 있는 물고기 탐색
            pq.clear(); // 다음 정렬을 위해 우선순위 큐 클리어
        }
    }
}