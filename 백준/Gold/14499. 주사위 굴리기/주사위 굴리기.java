import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int N, M;
    public static int[][] arr1;
    // 차례대로 동 서 북 남
    public static int[] dx = {0, 0, -1, 1};
    public static int[] dy = {1, -1, 0, 0};
    // 위, 바닥, 앞, 뒤, 왼, 오
    public static int[] dice = {0, 0, 0, 0, 0, 0};

    public static boolean range(int x, int y) { // 좌표 범위 체크
        return x >= 0 && y >= 0 && x < N && y < M;
    }

    public static void E_roll() {
        // 위 -> 오 -> 바닥 -> 왼 - 위
        int tmp = dice[0];
        dice[0] = dice[4];
        dice[4] = dice[1];
        dice[1] = dice[5];
        dice[5] = tmp;
    }

    public static void W_roll() {
        // 위 -> 왼 -> 바닥 -> 오 -> 위
        int tmp = dice[0];
        dice[0] = dice[5];
        dice[5] = dice[1];
        dice[1] = dice[4];
        dice[4] = tmp;
    }

    public static void N_roll() {
        // 위 -> 뒤 -> 바닥 -> 앞 -> 위
        int tmp = dice[0];
        dice[0] = dice[2];
        dice[2] = dice[1];
        dice[1] = dice[3];
        dice[3] = tmp;
    }

    public static void S_roll() {
        // 위 -> 앞 -> 바닥 -> 뒤 -> 위
        int tmp = dice[0];
        dice[0] = dice[3];
        dice[3] = dice[1];
        dice[1] = dice[2];
        dice[2] = tmp;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        // 이 문제는 x = 세로 y = 가로 이다.
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int task = Integer.parseInt(st.nextToken());

        arr1 = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                arr1[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < task; i++) {
            int input = Integer.parseInt(st.nextToken()) - 1;
            x += dx[input];
            y += dy[input];
            if (!range(x, y)) {
                // 가야하는 방향이 지도 밖이라면 continue
                x -= dx[input];
                y -= dy[input];
                continue;
            }

            if (input == 0) { //  동쪽 이동
                E_roll();
            } else if (input == 1) { // 서쪽 이동
                W_roll();
            } else if (input == 2) { // 북쪽 이동
                N_roll();
            } else if (input == 3) { // 남쪽 이동
                S_roll();
            }
            // 주사위 바닥면, 지도 스왑하기
            if (arr1[x][y] == 0) {
                arr1[x][y] = dice[1];
            } else {
                dice[1] = arr1[x][y];
                arr1[x][y] = 0;
            }
            sb.append(dice[0]).append("\n");
        }
        System.out.print(sb);
    }
}