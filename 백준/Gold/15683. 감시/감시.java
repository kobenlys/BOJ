import java.io.*;
import java.util.*;

public class Main {
    public static int N, M, blindSpot = Integer.MAX_VALUE;

    // 2차원 배열 깊은복사.
    public static int[][] deepCopy(int[][] arr1) {
        int[][] copy = new int[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                copy[i][j] = arr1[i][j];
            }
        }
        return copy;
    }

    // 해당 배열의 사각지대 카운트
    public static int cntBlindSpot(int[][] arr1) {
        int answer = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (arr1[i][j] == 0) {
                    answer++;
                }
            }
        }
        return answer;
    }

    // CCTV 위 방향
    public static void upWatch(int[][] arr1, int x, int y) {
        for (int i = y; i >= 0; i--) {
            if (arr1[i][x] == 6) break;
            if (arr1[i][x] == 0) arr1[i][x] = -1;
        }
    }

    // CCTV 아래 방향
    public static void downWatch(int[][] arr1, int x, int y) {
        for (int i = y; i < N; i++) {
            if (arr1[i][x] == 6) break;
            if (arr1[i][x] == 0) arr1[i][x] = -1;
        }
    }

    // CCTV 왼쪽 방향
    public static void leftWatch(int[][] arr1, int x, int y) {
        for (int i = x; i >= 0; i--) {
            if (arr1[y][i] == 6) break;
            if (arr1[y][i] == 0) arr1[y][i] = -1;
        }
    }

    // CCTV 오른쪽 방향
    public static void rightWatch(int[][] arr1, int x, int y) {
        for (int i = x; i < M; i++) {
            if (arr1[y][i] == 6) break;
            if (arr1[y][i] == 0) arr1[y][i] = -1;
        }
    }
    
    // 깊이 우선 탐색으로 모든 조합을 탐색한다.
    public static void dfs(int[][] arr1, int x, int y) {

        // 모든 칸들의 탐색 완료시, 사각지대 카운트
        if (x == M && y == N - 1) {
            int res = cntBlindSpot(arr1);
            blindSpot = Math.min(blindSpot, res);
            return;
        }

        if (x == M) { // Y축 한칸 전진
            dfs(arr1, 0, y + 1);
            return;
        }

        // CCTV 발견 시
        if (arr1[y][x] >= 1 && arr1[y][x] <= 5) {

            int dir = arr1[y][x]; // CCTV 번호

            if (dir == 1) { // 1번 CCTV 모든 경우의 방향 탐색
                // 현재 배열을 건들지 않으면서, 깊은복사로 새로운 배열 생성
                int[][] newTmp = deepCopy(arr1);
                upWatch(newTmp, x, y); // 상
                dfs(newTmp, x + 1, y); // 인덱스 한칸 전진

                newTmp = deepCopy(arr1);
                downWatch(newTmp, x, y);// 하
                dfs(newTmp, x + 1, y);

                newTmp = deepCopy(arr1);
                leftWatch(newTmp, x, y);// 좌
                dfs(newTmp, x + 1, y);

                newTmp = deepCopy(arr1);
                rightWatch(newTmp, x, y);// 우
                dfs(newTmp, x + 1, y);

            } else if (dir == 2) { // 2번 CCTV 모든 경우의 방향 탐색

                int[][] newTmp = deepCopy(arr1); // 깊은 복사
                leftWatch(newTmp, x, y); // 좌
                rightWatch(newTmp, x, y); // 우
                dfs(newTmp, x + 1, y); // 인덱스 한칸 전진
                // ~~Watch 함수는 얕은복사를 이용하기때문에 배열을 리턴할 필요없음.

                newTmp = deepCopy(arr1);
                upWatch(newTmp, x, y); // 상
                downWatch(newTmp, x, y); // 하
                dfs(newTmp, x + 1, y);


            } else if (dir == 3) { // 3번 CCTV 모든 경우의 방향 탐색
                int[][] newTmp = deepCopy(arr1); // 깊은 복사
                upWatch(newTmp, x, y); // 상
                rightWatch(newTmp, x, y); // 우
                dfs(newTmp, x + 1, y); // 인덱스 한칸 전진

                newTmp = deepCopy(arr1);
                rightWatch(newTmp, x, y);
                downWatch(newTmp, x, y);
                dfs(newTmp, x + 1, y);

                newTmp = deepCopy(arr1);
                downWatch(newTmp, x, y);
                leftWatch(newTmp, x, y);
                dfs(newTmp, x + 1, y);

                newTmp = deepCopy(arr1);
                leftWatch(newTmp, x, y);
                upWatch(newTmp, x, y);
                dfs(newTmp, x + 1, y);


            } else if (dir == 4) { // 4번 CCTV 모든 경우의 방향 탐색
                int[][] newTmp = deepCopy(arr1); // 깊은복사
                leftWatch(newTmp, x, y); // 좌
                upWatch(newTmp, x, y); // 상
                rightWatch(newTmp, x, y); // 우
                dfs(newTmp, x + 1, y); // 한칸 전진

                newTmp = deepCopy(arr1);
                upWatch(newTmp, x, y);
                rightWatch(newTmp, x, y);
                downWatch(newTmp, x, y);
                dfs(newTmp, x + 1, y);

                newTmp = deepCopy(arr1);
                rightWatch(newTmp, x, y);
                downWatch(newTmp, x, y);
                leftWatch(newTmp, x, y);
                dfs(newTmp, x + 1, y);

                newTmp = deepCopy(arr1);
                downWatch(newTmp, x, y);
                leftWatch(newTmp, x, y);
                upWatch(newTmp, x, y);
                dfs(newTmp, x + 1, y);

            } else if (dir == 5) { // 5번 CCTV 모든 경우의 방향 탐색
                int[][] newTmp = deepCopy(arr1); // 깊은복사
                upWatch(newTmp, x, y); // 상
                downWatch(newTmp, x, y); // 하
                leftWatch(newTmp, x, y); // 좌
                rightWatch(newTmp, x, y); // 우
                dfs(newTmp, x + 1, y); // 한칸 전진

            }
        } else {
            // CCTV 발견 할때까지 한칸 전진
            dfs(arr1, x + 1, y);
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int[][] arr1 = new int[N][M];

        // 입력
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr1[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // DFS 탐색, 출력
        dfs(arr1, 0, 0);
        System.out.println(blindSpot);
    }
}