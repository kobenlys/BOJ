import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int N, M, min = Integer.MAX_VALUE;
    public static int[][] arr1;
    public static boolean[][] vi;
    public static boolean[][] viBreak;
    public static Queue<node> qu = new LinkedList<>();
    // 상 하 좌 우
    public static int[] dx = {0, 0, -1, 1};
    public static int[] dy = {-1, 1, 0, 0};

    public static boolean range(int x, int y) { // 좌표 범위 체크
        return x >= 0 && y >= 0 && x < M && y < N;
    }

    public static class node { // 좌표, 부실 수 있는 벽 수, 걸음 수 담는 객체
        int x, y, step, chance;

        public node(int x, int y, int step, int chance) {
            this.x = x;
            this.y = y;
            this.step = step;
            this.chance = chance;
        }
    }

    public static void algorithm() { // BFS 알고리즘
        qu.offer(new node(0, 0, 1, 1));

        while (!qu.isEmpty()) {
            node nd = qu.poll();

            if (nd.x == M - 1 && nd.y == N - 1) { // N, M 에 도착시 최단경로
                min = Math.min(min, nd.step);
                continue;
            }
            for (int i = 0; i < 4; i++) {
                int nx = nd.x + dx[i];
                int ny = nd.y + dy[i];

                if (range(nx, ny)) {

                    // 벽을 부신경우, 벽을 안 부신경우 가 겹쳐지는 것을 방지
                    if (arr1[ny][nx] == 0 && !vi[ny][nx] && nd.chance == 1) { // 벽 부심 X
                        // 갈 수 있는 경로로 이동
                        vi[ny][nx] = true;
                        qu.offer(new node(nx, ny, nd.step + 1, nd.chance));

                    } else if (arr1[ny][nx] == 0 && !viBreak[ny][nx] && nd.chance == 0) {// 벽 부심 O

                        viBreak[ny][nx] = true;
                        qu.offer(new node(nx, ny, nd.step + 1, nd.chance));

                    } else if (arr1[ny][nx] == 1 && !vi[ny][nx]) { // 기회가 있다면 벽부시기
                        // 벽을 부실 수 있는 기회가 있다면 부시고 전진 후 chance = 0
                        if (nd.chance == 1) {
                            vi[ny][nx] = true;
                            qu.offer(new node(nx, ny, nd.step + 1, 0));
                        }
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
        
        arr1 = new int[N][M];
        vi = new boolean[N][M];
        viBreak = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                arr1[i][j] = Character.getNumericValue(input.charAt(j));
            }
        }

        algorithm(); // BFS 실행
        // min 이 초기값일 경우 최단거리 출력 불가능
        System.out.print(min == Integer.MAX_VALUE ? -1 : min);
    }
}