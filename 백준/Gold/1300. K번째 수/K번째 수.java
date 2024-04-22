import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());

       
        // 답은 K이상이 될 수 없다.
        int left = 0, right = K;
        int answer = 0;

        while (left <= right) {

            int mid = (left + right) / 2;
            int cnt = 0;
            // 곱의 성질 이용, i 구구단 에서 mid값 이하 숫자갯수 찾기.
            // ex 6 / 2 -> 2,4,6 -> 총 3개임.
            for (int i = 1; i <= N; i++) {
                cnt += Math.min(mid / i, N);
            }
            // cnt가 K 보다 크다면 답이 아님 right 범위 줄이기.
            if (K <= cnt) {
                right = mid - 1;
            } else {
                // cnt가 K보다 작다면 더큰 mid값을 구하자.
                left = mid + 1;
                answer = left;
            }
        }
        System.out.print(answer);
    }
}