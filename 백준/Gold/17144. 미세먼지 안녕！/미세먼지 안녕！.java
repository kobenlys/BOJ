import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int Y, X, T, ans;
    public static int[][] arr1;
    public static int[][] tmp;
    public static ArrayList<node> airCleaner = new ArrayList<>();

    public static class node {
        int x, y;
        public node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void findDust() { // 현재 미세먼지 위치 찾기.
        for (int i = 0; i < Y; i++) {
            for (int j = 0; j < X; j++) {
                if (arr1[i][j] > 0) {
                    // 미세먼지 확산하기.
                    spreadDust(j, i, arr1[i][j]);
                }
            }
        }
        startCleaner();
    }

    public static void spreadDust(int x, int y, int num) { // 미세먼지 확산
        int[] dx = {0, 0, -1, 1};
        int[] dy = {-1, 1, 0, 0};
        int res = num / 5; // 미세먼지 퍼지는 양
        int cnt = 0;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            // 배열의 범위를 넘어가거나 + 공기청정기에 닿으면 미세먼지 확산 불가.
            if (nx < 0 || ny < 0 || nx >= X || ny >= Y) continue;
            if (arr1[ny][nx] == -1) continue;
            cnt++; // 미세먼지 퍼진 칸 수
            tmp[ny][nx] += res;
        }
        tmp[y][x] += num - res * cnt; // 미세먼지 확산후 남은 미세먼지.
    }

    public static void startCleaner() {
        node now = airCleaner.get(0);
        int upY = now.y;
        int num = -1;
        int key = 0;
        // 공기청정기 윗부분 배열 돌리기.
        for (int i = 1; i < X; i++) {
            key = tmp[upY][i];
            tmp[upY][i] = num == -1 ? 0 : num;
            num = key;
        }
        for (int i = upY - 1; i >= 0; i--) {
            key = tmp[i][X - 1];
            tmp[i][X - 1] = num == -1 ? 0 : num;
            num = key;
        }
        for (int i = X - 2; i >= 0; i--) {
            key = tmp[0][i];
            tmp[0][i] = num == -1 ? 0 : num;
            num = key;
        }
        for (int i = 1; i < upY; i++) {
            key = tmp[i][0];
            tmp[i][0] = num == -1 ? 0 : num;
            num = key;
        }
        
        now = airCleaner.get(1);
        int downY = now.y;
        num = -1;
        key = 0;
        // 공기청정기 아랫부분 배열 돌리기
        for (int i = 1; i < X; i++) {
            key = tmp[downY][i];
            tmp[downY][i] = num == -1 ? 0 : num;
            num = key;
        }
        for (int i = downY + 1; i < Y; i++) {
            key = tmp[i][X - 1];
            tmp[i][X - 1] = num == -1 ? 0 : num;
            num = key;
        }
        for (int i = X - 2; i >= 0; i--) {
            key = tmp[Y - 1][i];
            tmp[Y - 1][i] = num == -1 ? 0 : num;
            num = key;
        }
        for (int i = Y - 2; i >= downY; i--) {
            key = tmp[i][0];
            tmp[i][0] = num == -1 ? 0 : num;
            num = key;
        }
    }

    public static void main(String[] args) throws IOException { //조건 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        Y = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        arr1 = new int[Y][X]; // 현재 미세먼지 위치.
        tmp = new int[Y][X]; // 미세먼지 확산 후 담는 배열

        for (int i = 0; i < Y; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < X; j++) {
                arr1[i][j] = Integer.parseInt(st.nextToken());
                if (arr1[i][j] == -1) { // 공기청정기 위치 담기.
                    airCleaner.add(new node(j, i));
                    tmp[i][j] = arr1[i][j];
                }
            }
        }
        while (T-- > 0) {
            findDust();
            // 공기청정기 위치 tmp배열에 다시 입력, 동시에 공기청정기 위치 미세먼지 제거!
            for (int i = 0; i < airCleaner.size(); i++) {
                node site = airCleaner.get(i);
                tmp[site.y][site.x] = -1;
            }
            arr1 = tmp.clone(); // 확산 + 공기청정기 사용 후 arr1 배열에 복사.
            tmp = new int[Y][X]; // tmp 배열 초기화.
        }
        // 미세먼지 카운트.
        for (int i = 0; i < Y; i++) {
            for (int j = 0; j < X; j++) {
                if (arr1[i][j] > 0) {
                    ans += arr1[i][j];
                }
            }
        }
        System.out.print(ans);
    }
}