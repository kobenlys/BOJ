import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int N, M, cnt;
    public static Character[][] arr1;
    public static boolean[][] vi;
    public static Queue<node> qu = new LinkedList<>();

    public static class node {
        int x, y;

        public node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static boolean range(int x, int y) {
        return x >= 0 && y >= 0 && x < M && y < N;
    }

    public static void BFS() { // BFS 알고리즘 구현
        int[] dx = {0, 0, -1, 1};
        int[] dy = {-1, 1, 0, 0};

        while (!qu.isEmpty()) {
            node nd = qu.poll();

            for (int i = 0; i < 4; i++) {
                int nx = nd.x + dx[i];
                int ny = nd.y + dy[i];

                if (range(nx, ny)) {
                    if (arr1[ny][nx] != 'X' && !vi[ny][nx]) {
                        if (arr1[ny][nx] == 'P') { // 사람 발견, cnt++
                            cnt++;
                        }
                        vi[ny][nx] = true;
                        qu.offer(new node(nx, ny));
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

        arr1 = new Character[N][M];
        vi = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                arr1[i][j] = input.charAt(j);
                if (arr1[i][j] == 'I') {
                    // 도연이 위치 입력
                    qu.offer(new node(j, i));
                }
            }
        }
        // 함수호출, 답 출력
        BFS();
        System.out.println(cnt == 0 ? "TT" : cnt);
    }
}