import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int ans = 0;
        
        int[] dp = new int[101];
        Arrays.fill(dp, -1);

        st = new StringTokenizer(br.readLine());
        int tmp = Integer.parseInt(st.nextToken());
        dp[tmp] = 0;

        for (int i = 1; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());
            for (int j = 1; j <= 100; j++) {
                if (dp[j] > -1) {
                    dp[num] = Math.max(dp[num], dp[j] + (num - j) * (num - j));
                }
            }
        }

        ans = Arrays.stream(dp).max().getAsInt();
        System.out.println(ans);
    }
}