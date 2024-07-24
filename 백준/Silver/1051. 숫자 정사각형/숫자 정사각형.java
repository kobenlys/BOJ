import java.io.*;
import java.util.*;

public class Main {
    public static int N, M;
    public static int[][] arr1;

    public static int findRectangle(int x, int y) {
        int max = 1;
        int target = arr1[y][x];

        for (int i = 1; i < N; i++) {
            if (x + i >= M || y + i >= N) break;
            if (target == arr1[y][x + i]) {
                if (arr1[y][x + i] == arr1[y + i][x + i] && arr1[y + i][x + i] == arr1[y + i][x]) {
                    max = i + 1;
                }
            }
        }
        return max * max;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int answer = 0;
        arr1 = new int[N][M];

        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                arr1[i][j] = Character.getNumericValue(input.charAt(j));
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                answer = Math.max(answer, findRectangle(j, i));
            }
        }
        System.out.println(answer);
    }
}
