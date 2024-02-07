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

    public static void findDust() {
        for (int i = 0; i < Y; i++) {
            for (int j = 0; j < X; j++) {
                if (arr1[i][j] > 0) {
                    spreadDust(j,i,arr1[i][j]);
                }
            }
        }
        startCleaner();
    }

    public static void startCleaner() {
        node now = airCleaner.get(0);
        int upY = now.y;

        int num =-1;
        int key = 0;
        for (int i = 1; i < X; i++) {
            key = tmp[upY][i];
            tmp[upY][i] = num == -1 ? 0 : num;
            num = key;
        }
        for (int i = upY-1; i >= 0; i--) {
            key = tmp[i][X-1];
            tmp[i][X-1] = num == -1 ? 0 : num;
            num = key;
        }
        for (int i = X-2; i >= 0; i--) {
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

        num =-1;
        key = 0;
        for (int i = 1; i < X; i++) {
            key = tmp[downY][i];
            tmp[downY][i] = num == -1 ? 0 : num;
            num = key;
        }
        for (int i = downY+1; i < Y; i++) {
            key = tmp[i][X-1];
            tmp[i][X-1] = num == -1 ? 0 : num;
            num = key;
        }
        for (int i = X-2; i >= 0; i--) {
            key = tmp[Y-1][i];
            tmp[Y-1][i] = num == -1 ? 0 : num;
            num = key;
        }
        for (int i = Y-2; i >= downY; i--) {
            key = tmp[i][0];
            tmp[i][0] = num == -1 ? 0 : num;
            num = key;
        }
    }

    public static void spreadDust(int x,int y, int num) {
        int[] dx = {0, 0, -1, 1};
        int[] dy = {-1, 1, 0, 0};
        int res = num / 5;
        int cnt = 0;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx<0 || ny<0 || nx>=X || ny >= Y ) continue;
            if(arr1[ny][nx] == -1) continue;
            cnt++;
            tmp[ny][nx] += res;
        }
        tmp[y][x] += num - res * cnt;
    }

    public static void main(String[] args) throws IOException { //조건 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        Y = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        arr1 = new int[Y][X];
        tmp = new int[Y][X];

        for (int i = 0; i < Y; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < X; j++) {
                arr1[i][j]  = Integer.parseInt(st.nextToken());
                if (arr1[i][j] == -1) {
                    airCleaner.add(new node(j, i));
                    tmp[i][j] = arr1[i][j];
                }
            }
        }
        while (T-- > 0) {
            ans = 0;
            findDust();
            for (int i = 0; i < airCleaner.size(); i++) {
                node site = airCleaner.get(i);
                tmp[site.y][site.x] = -1;
            }
            arr1 = tmp.clone();
            tmp = new int[Y][X];
        }
        for (int i = 0; i < Y; i++) {
            for (int j = 0; j < X; j++) {
                if (arr1[i][j] > 0) {
                    ans += arr1[i][j];
                }
            }
        }
        System.out.println(ans);
    }
}