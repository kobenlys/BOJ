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
        int left = 0, right = 0;
        int[] arr1 = new int[N];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            arr1[i] = Integer.parseInt(st.nextToken());
            right = Math.max(right, arr1[i]);
        }

        while (left <= right) {

            int mid = (right + left) / 2;
            long sum=0;
            for (int i = 0; i < N; i++) {
                if (arr1[i] - mid > 0) {
                    sum += arr1[i] - mid;
                }
            }

            if (sum < M) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        System.out.println(left-1);
    }
}