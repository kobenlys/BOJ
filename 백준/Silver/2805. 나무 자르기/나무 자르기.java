import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int max = 0;
        int[] arr1 = new int[N];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            arr1[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, arr1[i]);
        }


        // 이분탐색 범위 설정
        int left = 0, right = max;

        while (left <= right) {
            // 이분탐색 중간값 구하기
            // ex. 50~100, 중간값은 = 50+(100-50) = 75 이다.
            int mid = (left + right)/ 2;
            long sum = 0;

            for (int i = 0; i < N; i++) {
                int num = arr1[i] - mid;
                if (num > 0) {
                    sum += num;
                }
            }

            // 이분탐색 알고리즘, 시간복잡도때문에 완탐은 불가능 하다.
            if (sum < M) {
                right = mid-1;
            } else {
                left = mid + 1;
            }
        }
        System.out.println(left-1);
    }
}