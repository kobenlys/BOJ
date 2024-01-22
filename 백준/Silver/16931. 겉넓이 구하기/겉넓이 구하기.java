import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int[][] arr1;
    public static int N, M;

    public static int algorithm() {
        int[] dx = {0, 0, -1, 1};
        int[] dy = {-1, 1, 0, 0};
        int ans = 0;

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                for (int k = 0; k < 4; k++) {
                    int nx = j + dx[k];
                    int ny = i + dy[k];

                    int res = arr1[i][j] - arr1[ny][nx];

                    if (res > 0) {
                        ans += res;
                    }
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) throws IOException { //조건 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int bottomCnt = 0;
        arr1 = new int[102][102];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 1; j <= M; j++) {
                arr1[i][j] = Integer.parseInt(st.nextToken());
                if (arr1[i][j] != 0) bottomCnt++;
            }
        }
        System.out.print(algorithm() + bottomCnt * 2);
    }
}
