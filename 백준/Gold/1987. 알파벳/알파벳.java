import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int R, C, cnt;
    public static char[][] arr1;
    public static int[] alphabet = new int[26];

    public static void dfs(int start, int x, int y) {
        int[] dx = {0, 0, -1, 1};
        int[] dy = {-1, 1, 0, 0};


        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || ny < 0 || nx >= C || ny >= R) continue;

            if (alphabet[arr1[ny][nx] - 'A'] != 0) {

                alphabet[arr1[ny][nx] - 'A']--;
                dfs(start + 1, nx, ny);
                alphabet[arr1[ny][nx] - 'A']++;
            } else {
                cnt = Math.max(cnt, start);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        arr1 = new char[R][C];

        for (int i = 0; i < R; i++) {
            String input = br.readLine();
            for (int j = 0; j < C; j++) {
                arr1[i][j] = input.charAt(j);
            }
        }
        cnt++;
        Arrays.fill(alphabet, 1);
        alphabet[arr1[0][0] - 'A']--;
        dfs(1,0,0);

        System.out.print(cnt);
    }
}