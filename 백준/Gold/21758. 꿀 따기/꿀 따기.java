import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int answer = 0;
        int[] arr1 = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr1[i] = Integer.parseInt(st.nextToken());
        }

        if (N < 3) {
            System.out.println(0);
            return;
        }

        int[] dp1 = new int[N + 1];
        int[] dp2 = new int[N + 1];
        dp2[N] = arr1[N];

        for (int i = 1; i <= N; i++) {
            dp1[i] += dp1[i - 1] + arr1[i];
            dp2[N - i] += dp2[N - i + 1] + arr1[N - i];
        }

        for (int i = 2; i <= N; i++) {
            int res = (dp1[N] - arr1[1]) + (dp1[N] - dp1[i]) - arr1[i];
            answer = Math.max(answer, res);
        }

        for (int i = N - 1; i >= 1; i--) {
            int res = (dp2[1] - arr1[N]) + (dp2[1] - dp2[i]) - arr1[i];
            answer = Math.max(answer, res);
        }

        for (int i = 2; i < N; i++) {
            int res = dp1[i] - arr1[1] + dp2[i] - arr1[N];
            answer = Math.max(answer, res);
        }

        System.out.println(answer);
    }
}