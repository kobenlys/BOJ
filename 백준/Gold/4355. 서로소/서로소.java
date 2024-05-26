import java.io.*;
import java.util.*;

public class Main {
    // 오일러의 피 함수
    public static int eulerPhi(int N) {

        int res = N;
        // N 과 N이하 자연수 서로소 개수 구하기.
        for (int i = 2; i <= Math.sqrt(N); i++) {
            if (N % i == 0) {
                // N에서 i배수 제거.
                while (N % i == 0) {
                    N /= i;
                }
                // 결과값 보정.
                res -= res / i;
            }
        }
        if (N > 1) res -= res / N;
        return res;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        while (true) {
            int N = Integer.parseInt(br.readLine());
            if (N == 0) break;
            // N보다 작은 양의 정수라는 조건 때문에, eulerPhi(1) = 1 이 아닌  0이 답이다.
            sb.append(N == 1 ? 0 : eulerPhi(N)).append("\n");
        }
        System.out.print(sb);
    }
}