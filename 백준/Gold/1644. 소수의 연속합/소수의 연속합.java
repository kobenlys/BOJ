import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int N;
    public static boolean[] arr1;

    public static void e_Sieve() {

        for (int i = 2; i <= N; i++) {
            for (int j = i + i; j <= N; j += i) {
                arr1[j] = true;
            }
        }
    }

    public static void main(String[] args) throws IOException { //조건 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr1 = new boolean[N + 1];
        e_Sieve();

        int left = 1, right = 1;
        int sum = 0, cnt = 0;
        while (true) {

            if (right > N) break;

            if (sum <= N) {
                if (sum == N) cnt++;
                right++;
                while (right <= N && arr1[right]) {
                    right++;
                }
                sum += right;
            } else {
                left++;
                while (left <= N && arr1[left]) {
                    left++;
                }
                sum -= left;
            }
        }
        System.out.println(cnt);
    }
}