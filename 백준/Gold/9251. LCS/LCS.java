import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException { //조건 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] arr1 = br.readLine().toCharArray();
        char[] arr2 = br.readLine().toCharArray();
        int[][] dp = new int[arr2.length + 1][arr1.length + 1];

        // LCS 알고리즘 최장길이 공통부분 찾기
        for (int i = 1; i <= arr1.length; i++) {
            for (int j = 1; j <= arr2.length ; j++) {
                if (arr1[i - 1] == arr2[j - 1]) {
                    dp[j][i] = dp[j - 1][i - 1]+1;
                } else {
                    dp[j][i] = Math.max(dp[j - 1][i], dp[j][i - 1]);
                }
            }
        }
        System.out.print(dp[arr2.length][arr1.length]);
    }
}
