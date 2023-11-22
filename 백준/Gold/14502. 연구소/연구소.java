import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    public static int N, M, max;
    public static int[][] arr1;
    public static int[][] virus;
    public static boolean[][] vi;
    public static Queue<node> qu = new LinkedList<>();

    
    // DFS로 모든 벽의 경우의 수 생성 후 BFS함수 실행
    public static void dfs(int wall, int x) {

        if (wall == 3) {
            bfs();
            return;
        }
        for (int i = 0; i < N; i++) {
            for (int j = x; j < M; j++) {
                if (arr1[i][j] == 0) {
                    arr1[i][j] = 1;
                    dfs(wall + 1, j);
                    arr1[i][j] = 0;

                }
            }
        }
    }
    
    // 현재 벽 상태에 대한 바이러스 전염 = BFS로 구현
    public static void bfs() {
        int[] dx = {0, 0, -1, 1};
        int[] dy = {-1, 1, 0, 0};

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (arr1[i][j] == 2) {
                    vi[i][j] = true;
                    qu.offer(new node(j, i));

                    while (!qu.isEmpty()) {
                        node nd = qu.poll();

                        for (int k = 0; k < 4; k++) {
                            //상 하 좌 우 탐색
                            int nx = nd.x + dx[k];
                            int ny = nd.y + dy[k];

                            if (range(nx, ny)) {
                                if (arr1[ny][nx] == 0 && !vi[ny][nx]) {
                                    //갈 수 있는 공간 있다면 바이러스 전염
                                    qu.offer(new node(nx, ny));
                                    arr1[ny][nx] = 2;
                                    vi[ny][nx] = true;
                                }
                            }
                        }
                    }
                }
            }
        }
        // vi 배열 초기화 및 안전구역 카운트 후 arr1 바이러스 초기상태로 초기화
        vi = new boolean[N][M];
        cntSafe();
        cloneCopy();
    }
    // 안전구역 카운트 및 최대값 저장.
    public static void cntSafe() {
        int safe = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (arr1[i][j] == 0) {
                    safe++;
                }
            }
        }
        max = Math.max(max, safe);
    }
    // 벽이 세워진 형태는 유지하면서 virus 초기값으로 변환.
    public static void cloneCopy() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (arr1[i][j] == 2) {
                    arr1[i][j] = 0;
                }
                // virus 배열에는 바이러스 초기값이 저장되어있다.
                if (virus[i][j] == 2) {
                    arr1[i][j] = 2;
                }
            }
        }
    }
    // Queue에 좌표입력을 위한 객체
    public static class node {
        int x;
        int y;

        public node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    // 좌표 범위 체크
    public static boolean range(int x, int y) {
        return x >= 0 && y >= 0 && x < M && y < N;
    }

    public static void main(String[] args) throws IOException { //조건 입력

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr1 = new int[N][M];
        virus = new int[N][M];
        vi = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                arr1[i][j] = Integer.parseInt(st.nextToken());
                if (arr1[i][j] == 2) {
                    // virus 초기값 저장 = 시뮬레이션 끝나고 초기화 하기 위함.
                    virus[i][j] = 2;
                }
            }
        }

        max = 0;
        dfs(0, 0); // 함수 호출
        System.out.println(max);


    }
}
