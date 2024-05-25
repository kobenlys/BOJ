import java.io.*;
import java.util.*;

public class Main {

    public static long eulerPhi(long N) {

        long res = N;
        // 오일러의 피 알고리즘.
        for (long i = 2; i <= Math.sqrt(N); i++) {
            if (N % i == 0) {
                // i가 N의 소인수(나누어 떨어지게 하는 소수) 라면
                while (N % i == 0) {
                    // i의 배수 를 제거
                    N /= i;
                }
                // 결과를 조정한다.
                res -= res / i;
            }
        }
        // 남은 소수 처리
        if (N > 1) {
            res -= res / N;
        }
        return res;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        long N = Long.parseLong(br.readLine());
        long num = eulerPhi(N);

        System.out.println(num);

    }
}