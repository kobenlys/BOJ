import java.io.*;
import java.util.*;

public class Main {
    public static int N;
    public static char[][] arr1;
    public static int[] dx = {0, 0, 0, -1, 1};
    public static int[] dy = {0, -1, 1, 0, 0};

    // 좌표 기준 가로축 가장 긴 연속부분 카운트
    public static int checkRow(int x, int y) {
        int cnt = 0;
        char color = arr1[y][x];
        // color는 델타로 조정한 좌표의 색깔이 넘어온다.
        for (int i = x + 1; i < N; i++) {
            if (arr1[y][i] == color) cnt++;
            else break;
        }
        for (int i = x - 1; i >= 0; i--) {
            if (arr1[y][i] == color) cnt++;
            else break;
        }
        return cnt + 1;
    }

    // 좌표 기준 세로축 가장 긴 연속부분 카운트
    public static int checkColumm(int x, int y) {
        int cnt = 0;
        char color = arr1[y][x];
        for (int i = y + 1; i < N; i++) {
            if (arr1[i][x] == color) cnt++;
            else break;
        }
        for (int i = y - 1; i >= 0; i--) {
            if (arr1[i][x] == color) cnt++;
            else break;
        }
        return cnt + 1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr1 = new char[N][N];
        int answer = 0;

        for (int i = 0; i < N; i++) {
            arr1[i] = br.readLine().toCharArray();
        }

        // 상,하 (가로 체크), 좌 우(가로체크)
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {

                // 현재좌표기준  무(교환 x) 상 하 좌 우 탐색
                for (int k = 0; k < 5; k++) {
                    int nx = j + dx[k];
                    int ny = i + dy[k];
                    if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;

                    char before = arr1[i][j];
                    char after = arr1[ny][nx];

                    // 변경 후
                    arr1[i][j] = after;
                    arr1[ny][nx] = before;

                    int res = Math.max(checkRow(j, i), checkColumm(j, i));

                    // 다음계산을 위해 원위치
                    arr1[i][j] = before;
                    arr1[ny][nx] = after;
                    answer = Math.max(answer, res);
                }
            }
        }
        System.out.println(answer);
    }
}