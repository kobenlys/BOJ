import java.io.*;
import java.util.*;

public class Main {
    public static int[] dp;

    public static int LIS(int num, int left, int right, int size) {
        int answer = 0;
        while (left <= right) {

            int mid = (left + right) / 2;

            if (dp[mid] >= num) {
                answer = mid;
                right = mid - 1;
            } else{
                left = mid + 1;
            }
        }

        if (left >= size) {
            return -1;
        } else {
            return answer;
        }
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
            int idx = LIS(num, 0, len, len + 1);

            if (idx == -1) {
                dp[len++] = num;
            } else {
                dp[idx] = num;
            }
        }


        System.out.println(len);
    }
}