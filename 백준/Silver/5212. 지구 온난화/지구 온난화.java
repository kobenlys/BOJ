import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int R, C, maxX, maxY, minX = 11, minY = 11;
    public static char[][] arr1;
    public static boolean[][] vi;

    public static boolean range(int x, int y) { // 범위 체크
        return x >= 0 && y >= 0 && x < C && y < R;
    }

    public static void algorithm(int x, int y) {
        int[] dx = {0, 0, -1, 1}; // 좌 우
        int[] dy = {-1, 1, 0, 0}; // 상 하
        int cnt = 0;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (range(nx, ny)) {
                // 이전 계산에서 잠긴 섬이 아니면서, 바다라면 cnt++
                if (!vi[ny][nx] && arr1[ny][nx] == '.') {
                    cnt++;
                }
            } else {
                // 지도 밖은 바다이다.
                cnt++;
            }
        }

        if (cnt >= 3) { // 물에 잠기는 조건 달성시
            arr1[y][x] = '.';
            // 다음 계산에서 물에 잠긴섬은 바다로 cnt하지 않는다.
            // 방문 처리 해야함.
            vi[y][x] = true;
        } else {
            // 남아있는 섬의 출력 범위 설정,
            maxX = Math.max(maxX, x);
            maxY = Math.max(maxY, y);
            minX = Math.min(minX, x);
            minY = Math.min(minY, y);
        }
    }

    public static void main(String[] args) throws IOException { //조건 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        arr1 = new char[R][C];
        vi = new boolean[R][C];

        for (int i = 0; i < R; i++) {
            String str = br.readLine();
            for (int j = 0; j < C; j++) {
                arr1[i][j] = str.charAt(j);
            }
        }
        // 섬 발견시 algorithm 함수 호출
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (arr1[i][j] == 'X') {
                    algorithm(j, i); // 섬 주위 바다 카운팅
                }
            }
        }
        // 최적의 지도 출력.
        for (int i = minY; i <= maxY; i++) {
            for (int j = minX; j <= maxX; j++) {
                sb.append(arr1[i][j]);
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
}
