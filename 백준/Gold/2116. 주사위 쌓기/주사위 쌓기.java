import java.io.*;
import java.util.*;

public class Main {

    public static int N, answer;
    public static int[][] diceInfo;
    public static int[] sideInfo;

    public static void dfs(int depth, int bottomNum, int res) {

        if (depth == N) {
            answer = Math.max(answer, res);
            return;
        }

        for (int i = 0; i < 6; i++) {
            if (bottomNum == diceInfo[depth][i]) {
                int topNumber = diceInfo[depth][sideInfo[i]];
                int tmp = 0;
                if (bottomNum != 6 && topNumber != 6) {
                    tmp += 6;
                } else if (bottomNum != 5 && topNumber != 5) {
                    tmp += 5;
                } else {
                    tmp += 4;
                }
                dfs(depth + 1, topNumber, res + tmp);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        diceInfo = new int[N][6];
        sideInfo = new int[6];
        sideInfo[0] = 5;
        sideInfo[1] = 3;
        sideInfo[2] = 4;
        sideInfo[3] = 1;
        sideInfo[4] = 2;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 6; j++) {
                diceInfo[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 1; i <= 6; i++) {
            dfs(0, i, 0);
        }
        System.out.println(answer);
    }
}