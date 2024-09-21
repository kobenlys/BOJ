import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        long answer = 0;

        HashMap<Integer, Integer> map = new HashMap<>();
        int[] arr1 = new int[N + 1];
        int[] dp = new int[N + 1];


        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr1[i] = Integer.parseInt(st.nextToken());
            dp[i] = arr1[i] + dp[i - 1];
        }

        map.put(0, 1);

        for (int i = 1; i <= N; i++) {
            answer += map.getOrDefault(dp[i] - K, 0);
            //System.out.println(dp[i] +" "+ (dp[i] - K) +" "+ answer);
            map.put(dp[i], map.getOrDefault(dp[i], 0) + 1);
        }

        System.out.println(answer);
    }
}