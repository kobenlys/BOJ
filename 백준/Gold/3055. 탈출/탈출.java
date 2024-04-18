import java.io.*;
import java.util.*;

public class Main {
    public static int N, M;
    public static char[][] arr1;
    public static boolean[][] vi;
    public static Queue<node> qu = new LinkedList<>();

    public static class node {
        int x, y, cnt;

        public node(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }
    
    // 시간별 물 채우기.
    public static void waterFlow() {
        int[] dx = {0, 0, -1, 1};
        int[] dy = {-1, 1, 0, 0};
        boolean[][] checkVi = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {

                if (!vi[i][j] && arr1[i][j] == '*' && !checkVi[i][j]) {
                    vi[i][j] = true;
                    for (int k = 0; k < 4; k++) {
                        int nx = j + dx[k];
                        int ny = i + dy[k];
                        if (nx < 0 || ny < 0 || nx >= M || ny >= N) continue;
                        if (arr1[ny][nx] == '.') {
                            // 이번턴에 생긴 물은 제외
                            checkVi[ny][nx] = true;
                            arr1[ny][nx] = '*';
                        }
                    }
                }
            }
        }
    }

    public static int bfs() {
        int[] dx = {0, 0, -1, 1};
        int[] dy = {-1, 1, 0, 0};
        int answer = -1;
        int cnt = -1;

        while (!qu.isEmpty()) {

            node nd = qu.poll();
            // cnt가 달라지는 구간마다 물을 채운다.
            // -> BFS마다 waterFlow함수 실행한다면, 물채우는 속도가 매우 빠르다.
            // 즉 시간을 맞춰야하기 위함이다.
            if (nd.cnt != cnt) {
                waterFlow();
                cnt = nd.cnt;
            }

            for (int i = 0; i < 4; i++) {
                int nx = nd.x + dx[i];
                int ny = nd.y + dy[i];

                if (nx < 0 || ny < 0 || nx >= M || ny >= N) continue;

                if (!vi[ny][nx] && (arr1[ny][nx] == '.' || arr1[ny][nx] == 'D')) {
                    if (arr1[ny][nx] == 'D') return nd.cnt + 1;
                    vi[ny][nx] = true;
                    qu.offer(new node(nx, ny, nd.cnt + 1));
                }
            }
        }
        return answer;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr1 = new char[N][M];
        vi = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                arr1[i][j] = input.charAt(j);
                if (arr1[i][j] == 'S') {
                    qu.offer(new node(j, i, 0));
                }
            }
        }
        int answer = bfs();
        System.out.println(answer == -1 ? "KAKTUS" : answer);
    }
}