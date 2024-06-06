import java.io.*;
import java.util.*;

public class Main {

    public static int euler_Phi(int N) {
        int res = N;

        for (int i = 2; i <= Math.sqrt(N); i++) {
            if (N % i == 0) {
                while (N % i == 0) {
                    N /= i;
                }
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

        int T = Integer.parseInt(br.readLine());
        int[] arr1 = new int[10001];
        for (int i = 1; i <= 10000; i++) {
            arr1[i] = euler_Phi(i) + arr1[i - 1];
        }

        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());

            sb.append(arr1[N] + 1).append("\n");
        }
        System.out.print(sb);
    }
}