import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int N, M, cnt, temp;
    public static boolean[][] vi;
    public static int[][] arr1;
    public static Queue<node> qu = new LinkedList<>();

    // 좌표 담기 위한 객체
    public static class node {
        int x;
        int y;

        public node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void bfs() { // BFS 알고리즘 구현
        // 상 하 좌 우
        int[] dx = {0, 0, -1, 1};
        int[] dy = {-1, 1, 0, 0};
        boolean check = false; // 빙하 갈라진 경우를 찾는다.
        temp = cnt; // 계산 전 cnt 값을 저장한다.

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {

                if (arr1[i][j] != 0 && !vi[i][j]) {
                    if (check) { // 빙하가 갈라졌다면 check 가 true인 상태로 한번 더 if문을 지나간다.
                        //갈라진 빙하를 찾기 위해선 첫 회는 지나가야 하기 때문에 -1 을 한다.
                        System.out.println(cnt - 1);
                        System.exit(0);
                    }
                    check = true; // 빙하가 갈라지지 않았다면 한턴에 BFS탐색이 끝나야 한다.
                    cnt++;
                    qu.offer(new node(j, i));

                    while (!qu.isEmpty()) {
                        node nd = qu.poll();

                        for (int k = 0; k < 4; k++) {
                            int nx = nd.x + dx[k];
                            int ny = nd.y + dy[k];

                            if (range(nx, ny)) {
                                if (!vi[ny][nx] && arr1[ny][nx] != 0) {
                                    qu.offer(new node(nx, ny));
                                    iecberg(nx, ny); // 빙하 녹이기 함수 호출
                                    vi[ny][nx] = true;
                                }
                            }
                        }
                    }
                }
            }
        }

        // cnt의 변화가 없으면 배열에 0 이상이 없다는 뜻
        if (temp == cnt) { // 조건 1. 모든 빙하가 녹으면 0 출력
            System.out.println(0);
            System.exit(0);
        }
    }

    // 빙하 녹이기 구현, 이번 턴에서 녹아 "0"된 빙하는 제외 한다(vi 배열 이용).
    public static void iecberg(int x, int y) {
        int[] dx = {0, 0, -1, 1};
        int[] dy = {-1, 1, 0, 0};

        for (int k = 0; k < 4; k++) {
            int nx = x + dx[k];
            int ny = y + dy[k];
            // arr1[ny][nx] == 0 지만 vi[ny][nx] = true 인 경우를 제외한다.
            // 이번턴에서 녹아 없어진 자리는 다른 빙하에 영향을 끼치지 않는다.
            // 이미 방문한 곳은 이번턴 빙하가 있던 자리 이므로 제외한다.
            if (range(nx, ny) && !vi[ny][nx]) {
                if (arr1[ny][nx] == 0 && arr1[y][x] > 0) {
                    arr1[y][x] -= 1;
                }
            }
        }
    }

    // 범위 체크
    public static boolean range(int x, int y) {
        return x >= 0 && y >= 0 && x < M && y < N;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());


        arr1 = new int[N][M];
        vi = new boolean[N][M];


        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                arr1[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        while (true) {
            // 답이 나오기 전까지 무한 호출 및 한 턴 끝나면 vi배열 초기화.
            vi = new boolean[N][M];
            bfs();
        }

    }
}
