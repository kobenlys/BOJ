import java.io.*;
import java.util.*;

public class Main {

    public static int N, M;
    public static long[] arr1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr1 = new long[N + 2];
        long[] dp = new long[N + 2];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < N + 1; i++) {
            arr1[i] = Integer.parseInt(st.nextToken());
        }

        while (M-- > 0) {

            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            dp[s] += v;
            dp[e + 1] -= v;
        }

        for (int i = 1; i <= N; i++) {
            dp[i] += dp[i - 1];
        }

        for (int i = 1; i <= N; i++) {
            arr1[i] += dp[i];
            sb.append(arr1[i]).append(" ");
        }
        System.out.println(sb);
    }
}