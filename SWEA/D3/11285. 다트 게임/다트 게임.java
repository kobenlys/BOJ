import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        int[] arr1 = new int[11];
        arr1[0] = -1;
        for (int i = 1; i <= 10; i++) {
            arr1[i] = i * 20;
        }

        for (int tc = 1; tc <= T; tc++) {

            int N = Integer.parseInt(br.readLine());
            int answer = 0;

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                int len = x * x + y * y;

                for (int j = 1; j < 11; j++) {
                    if (arr1[j] * arr1[j] >= len) {
                        answer += 11 - arr1[j] / 20;
                        break;
                    }
                }
            }
            sb.append("#").append(tc).append(" ");
            sb.append(answer);
            sb.append("\n");
        }

        System.out.print(sb);
    }
}