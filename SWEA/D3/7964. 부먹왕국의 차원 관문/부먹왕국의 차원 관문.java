import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {

            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int D = Integer.parseInt(st.nextToken());
            int answer = 0;
            int[] arr1 = new int[N+2];

            arr1[0] = arr1[N + 1] = 1;

            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                arr1[i] = Integer.parseInt(st.nextToken());
            }

            int left = 0, right = 0, step = 0;

            while (right != N + 1) {

                if (left == right) {
                    right++;
                    step++;
                    continue;
                }

                if (arr1[right] == 1) {
                    left = right;
                    step = 0;
                    continue;
                }

                if (D == step && arr1[right] == 0) {
                    left = right;
                    step = 0;
                    answer++;
                    continue;
                }

                if (D > step) {
                    step++;
                    right++;
                }
            }

            sb.append("#").append(tc).append(" ");
            sb.append(answer);
            sb.append("\n");
        }
        System.out.print(sb);
    }
}