import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int K = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        long[] arr1 = new long[K];
        long sum = 0;

        for (int i = 0; i < K; i++) {
            long num = Integer.parseInt(br.readLine());
            sum += num;
            arr1[i] = num;
        }

        long left = 1; // 최솟값
        long right = sum / N; // 최댓값

        while (left <= right) {
            // 이분탐색으로 기준 선의 길이를 정한다.
            long mid = (right + left) / 2;
            long line = 0;

            for (int i = 0; i < K; i++) {
                // mid를 기준으로 자른 선의 개수
                line += arr1[i] / mid;
            }
            // N개의 조건에 부합 한 mid 중에 가장 큰값을 구한다
            if (line >= N) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        System.out.print(right);
    }
}