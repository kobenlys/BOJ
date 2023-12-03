import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int N, M, K, res, cnt;
    public static int[][] arr1;
    public static boolean[][] vi;
    public static Queue<node> qu = new LinkedList<>();

    // Queue 에 좌표 담는 객체
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
        return x >= 0 && y >= 0 && x < N && y < N;
    }


    public static void BFS() { // BFS 알고리즘
        int[] dx = {0, 0, -1, 1};
        int[] dy = {-1, 1, 0, 0};

        st:
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {

                if (arr1[i][j] == 0 && !vi[i][j]) {
                    qu.offer(new node(j, i));
                    cnt = 1;
                    vi[i][j] = true;

                    while (!qu.isEmpty()) {
                        node nd = qu.poll();

                        for (int k = 0; k < 4; k++) {
                            int nx = nd.x + dx[k];
                            int ny = nd.y + dy[k];

                            if (range(nx, ny)) {
                                if (arr1[ny][nx] == 0 && !vi[ny][nx]) {
                                    cnt++; // 하나의 구간에 자랄 수 있는 공간
                                    qu.offer(new node(nx, ny));
                                    vi[ny][nx] = true;
                                }
                            }
                        }
                    }
                    // 버섯포자 쓴 횟수 계산
                    if (cnt % K != 0) {
                        res += cnt / K + 1;
                    } else {
                        res += cnt / K;
                    }
                    // 버섯 포자 다 사용시 탈출
                    if (M - res <= 0) {
                        break st;
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr1 = new int[N][N];
        vi = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                arr1[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        cnt = Integer.MAX_VALUE;

        BFS();
        // 탐색하지 않는 자랄 수 있는 공간이 있다면 "IMPOSSIBLE"
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (arr1[i][j] == 0 && !vi[i][j]) {
                    System.out.println("IMPOSSIBLE");
                    System.exit(0);
                }
            }
        }

        // 버섯이 자랄 수 있는 공간이 없었다면 (예외처리)
        if (cnt == Integer.MAX_VALUE) {
            System.out.println("IMPOSSIBLE");
            System.exit(0);
        }
        // 버섯 포자가 없다면 IMPOSSIBLE (예외처리)
        if (M == 0) {
            System.out.println("IMPOSSIBLE");
            System.exit(0);
        }


        // 출력
        System.out.println("POSSIBLE");
        System.out.println(M - res);
    }
}


