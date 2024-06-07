import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] arr1 = new int[N];

        st = new StringTokenizer(br.readLine());
        arr1[0] = Integer.parseInt(st.nextToken());

        // 누적합 구하기.
        for (int i = 1; i < N; i++) {
            arr1[i] = arr1[i - 1] + Integer.parseInt(st.nextToken());
        }

        while (M-- > 0) {
            int target = Integer.parseInt(br.readLine());
            int left = 0, right = N - 1;
            int idx = -1;

            // target기준으로 이분탐색
            // 시간을 누적합으로 전처리하고, target보다 작은 idx중 가장 큰 idx탐색
            while (left <= right) {

                int mid = (left + right) / 2;

                if (target < arr1[mid]) {
                    right = mid - 1;
                } else {
                    idx = mid;
                    left = mid + 1;
                }
            }

            sb.append(idx + 1).append("\n");
        }

        System.out.print(sb);
    }
}