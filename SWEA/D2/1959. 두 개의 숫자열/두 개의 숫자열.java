import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int i = 1; i <= T; i++) {

            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int max = 0;

            int[] arr1 = new int[N];
            int[] arr2 = new int[M];

            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr1[j] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr2[j] = Integer.parseInt(st.nextToken());
            }
            

            if (N < M) {

                for (int j = 0; j <= M - N; j++) {
                    int cnt = 0;
                    for (int k = 0; k < N; k++) {
                        cnt += arr2[j + k] * arr1[k];
                    }
                    max = Math.max(max, cnt);
                }

            } else {

                for (int j = 0; j <= N - M; j++) {
                    int cnt = 0;
                    for (int k = 0; k < M; k++) {
                        cnt += arr1[j + k] * arr2[k];
                    }
                    max = Math.max(max, cnt);
                }
            }

            sb.append("#").append(i).append(" ");
            sb.append(max).append("\n");
        }
        System.out.println(sb);
    }
}