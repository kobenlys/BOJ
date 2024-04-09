import java.io.*;
import java.util.*;

public class Main {
    public static int N, max;
    public static int[][] arr1;

    public static void dfs(int idx, int cnt) {
        // 보드 끝까지 탐색 했다면 max 값 도출
        if (idx >= N * N) {
            max = Math.max(max, cnt);
            return;
        }
        // 1차원 배열 인덱스 -> 2차원 배열 인덱스로 변환
        int x = idx % N;
        int y = idx / N;
        int nIdx = calcIdx(idx); // 다음 전진 횟수 결정.

        if (arr1[y][x] == 1 && isPlaced(x, y)) {
            arr1[y][x] = 2;
            dfs(idx + nIdx, cnt + 1);
            arr1[y][x] = 1;
        }
        dfs(idx + nIdx, cnt);
    }

    // 보드 크기 짝수, 홀수 일때 이동 길이 조절.
    public static int calcIdx(int idx) {
        // 홀수라면 그냥 2칸++
        if (N % 2 == 1) return 2;

        // 짝수라면 색깔에 맞는 다음칸 +n 리턴
        if (idx % N == N - 1) return 1;
        else if (idx % N == N - 2) return 3;
        else return 2;
    }


    public static boolean isPlaced(int x, int y) {
        // 좌상, 우상, 좌하, 우하
        int[] dx = {-1, 1, -1, 1};
        int[] dy = {-1, -1, 1, 1};
        // 현재 좌표기준으로 비숍 이동경로 겹치는 다른 비숍있는지 판단.
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

    // 좌표의 범위 체크
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
        // 검정칸, 흰색칸 나눠서 탐색 -> 검정 비숍과 흰색 비숍은 만날일 없기때문에 가능함.
        // 검정칸 탐색
        int res = 0;
        dfs(0, 0);
        res += max;
        max = 0;

        // 흰색칸 탐색
        dfs(1, 0);
        res += max;
        System.out.println(res);
    }
}