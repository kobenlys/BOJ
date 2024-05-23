import java.io.*;
import java.util.*;

public class Main {
    public static int[] dp;

    // LIS 알고리즘
    public static int LIS(int num, int left, int right, int size) {
        int ans = 0;
        // 이분탐색 활용
        while (left <= right) {

            int mid = (left + right) / 2;

            if (dp[mid] >= num) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        if (left >= size) {
            return -1;
        } else {
            return ans;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        dp = new int[N];
        int len = 0;

        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());
            int idx = LIS(num, 0, len, len + 1);

            // dp 메모이제이션 하기.
            if (idx == -1) {
                dp[len++] = num;
            } else {
                dp[idx] = num;
            }

        }
        // N - 이미정렬된 숫자 갯수(최장길이 수열 길이) 
        System.out.println(N - len);
        // 3 7 5 2 6 1 4
        // 7 2 1 4
        // 3 5 6
    }
}