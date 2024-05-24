import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
    public static int[] dp = new int[1_000_001];

    public static void divsor() {

        // 약수 구하기 최적화 알고리즘 O(nlogn)
        for (int i = 1; i <= 1_000_000; i++) {
            for (int j = 1; j <= 1_000_000 / i; j++) {
                // N / i  해야 nlogn으로 단축가능.
                dp[i * j] += i + j;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        long[] prefixSum = new long[1_000_001];
        divsor(); // 약수구하기 최적화 알고리즘

        // 시간초과 방지, 누적합 구하기
        for (int i = 1; i <= 1_000_000; i++) {
            prefixSum[i] = dp[i] + prefixSum[i - 1];
        }
        
        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            // 1 * 2, 2*1 같은 약수 쌍 중복제거.
            sb.append(prefixSum[N]/2).append("\n");
        }
        System.out.print(sb);
    }
}