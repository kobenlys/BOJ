import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int N;


    public static void main(String[] args) throws IOException { //조건 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st ;

        int[] dp = new int[1001];
        int[] arr1 = new int[1001];
        int n = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= n; i++) {
            arr1[i] = Integer.parseInt(st.nextToken());
        }
        dp[0] = arr1[0];

        for (int i = 1; i <= n; i++) {

            for (int j = 0; j < i; j++) {
                if (arr1[j] < arr1[i] && dp[i] < dp[j] + 1) {
                    dp[i] = dp[j] + 1;
                }
            }
        }

        Arrays.sort(dp);
        System.out.println(dp[1000]);

    }
}
