import java.io.*;
import java.util.*;

public class Main {
    public static int N, max;
    public static int[][] arr1;


    public static void dfs(int idx, int cnt) {
        // 보드에 가능한 비숍 다 올렸다면 값 구하기.
        if (idx >= N * N) {
            max = Math.max(max, cnt);
            return;
        }
        // 일차원 배열 좌표 idx를 2차원 좌표로 변환
        int x = idx % N;
        int y = idx / N;
        int nIdx = calcIdx(idx); //칸 전진.

        if (arr1[y][x] == 0) {
            dfs(idx + nIdx, cnt);
            return;
        }


        if (arr1[y][x] == 1 && isPlaced(x, y)) {

            arr1[y][x] = 2;
            dfs(idx + nIdx, cnt + 1);
            arr1[y][x] = 1;
        }
        dfs(idx + nIdx, cnt);
    }
    
    // 인덱스 칸 전진, N이 짝수, 홀수에 비례하게.
    public static int calcIdx(int idx) {

        if (N % 2 == 1) return 2;

        if (idx % N == N - 1) return 1;
        else if (idx % N == N - 2) return 3;
        else return 2;
    }


    public static boolean isPlaced(int x, int y) {
        // 좌상, 우상, 좌하, 우하
        int[] dx = {-1, 1, -1, 1};
        int[] dy = {-1, -1, 1, 1};
        
        // 각 대각선에 비숍이 놓아져 있는지 판별.
        
        for (int i = 0; i < 4; i++) {
            int flag = 1;
            while (true) {
                int nx = x + flag * dx[i];
                int ny = y + flag * dy[i];
                if (!isRange(nx, ny)) break;
                if (arr1[ny][nx] == 2) return false;
                flag++;
            }
        }
        return true;
    }

    public static boolean isRange(int x, int y) {
        return x >= 0 && y >= 0 && x < N && y < N;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr1 = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                arr1[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int res = 0;
        // 검정칸과 흰색칸에 있는 비숍은 절대 만날 수 없음.
        // 백트래킹을 칸색깔 별로 나눠서 탐색한다.
        // 검정칸 탐색
        dfs(0, 0);
        res += max;
        max = 0;
        // 흰색 칸 탐색
        dfs(1, 0);
        res += max;

        System.out.println(res);
    }
}