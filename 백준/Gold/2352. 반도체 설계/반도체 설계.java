import java.io.*;
import java.util.*;

public class Main {
    public static int[] dp;

    public static int LIS(int left, int right, int num, int size) {
        int res = 0;

        while (left <= right) {

            int mid = (left + right) / 2;

            if (num <= dp[mid]) {
                res = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        if (left >= size) {
            return -1;
        }
        return res;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        dp = new int[N];
        int len = 0;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());

            int dir = LIS(0, len, num, len + 1);

            if (dir == -1) {
                dp[len++] = num;
            } else {
                dp[dir] = num;
            }
        }

        System.out.println(len);
    }
}