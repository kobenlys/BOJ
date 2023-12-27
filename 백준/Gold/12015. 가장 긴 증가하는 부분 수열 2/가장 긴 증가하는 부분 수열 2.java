import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int[] arr1;
    public static int[] dp;

    public static int LIS(int num, int left, int right, int size) {

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

        if (left == size) {
            return -1;
        }else{
            return res;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        arr1 = new int[N];
        dp = new int[N];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            arr1[i] = Integer.parseInt(st.nextToken());
        }

        int len = 0;
        for (int i = 0; i < N; i++) {

            int site = LIS(arr1[i], 0, len, len + 1);

            if (site == -1) {
                dp[len++] = arr1[i];
            }else{
                dp[site] = arr1[i];
            }
        }
        System.out.println(len);
    }
}