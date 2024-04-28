import java.io.*;
import java.util.*;

public class Main {
    public static int N;
    public static int[] arr1;
    public static int[] dp;

    public static boolean isPalindrome(int start, int end, boolean isOdd) {

        int left = 0, right = 0;
        int mid = (start + end) / 2;

        boolean isOk = true;

        if (isOdd) {
            left = mid;
            right = mid + 1;
        } else {
            left = right = mid;
        }

        while (left >= start && right <= end) {

            if (arr1[left] != arr1[right]) {
                isOk = false;
                break;
            }
            left--;
            right++;
        }

        return isOk;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr1 = new int[N];
        dp = new int[N];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            arr1[i] = Integer.parseInt(st.nextToken());
        }

        int M = Integer.parseInt(br.readLine());

        while (M-- > 0) {
            st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken()) - 1;
            int end = Integer.parseInt(st.nextToken()) - 1;

            int mid = (start + end) / 2;

            if (start == end) {
                sb.append(1).append("\n");
                continue;
            }

            if ((start + end) % 2 != 0) {
                // 홀수
                sb.append(isPalindrome(start, end, true) ? 1 : 0).append("\n");
            } else {
                // 짝수
                sb.append(isPalindrome(start, end, false) ? 1 : 0).append("\n");
            }

        }
        System.out.print(sb);
    }
}