import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static long mod = 1000;
    public static long[][] arr1;
    public static int N;

    public static long[][] dfs(long size) {

        if (size == 1) {
            return arr1;
        }

        long[][] tmp = dfs(size / 2);

        if (size%2 == 0) {
            // 짝수인 경우 (A^2)^2 = A^4
            // 2^4 , (2^2)^2 = 16 = 2^4
            return matrixMulti(tmp, tmp);
        } else {
            // 홀수인 경우 (A^2)^2 * A = A^5
            // 2^5, (2^2)^2 * 2 = 2^5 = 32
            return matrixMulti(matrixMulti(tmp, tmp), dfs(1));
        }
    }

    public static long[][] matrixMulti(long[][] a, long[][] b) {
        long[][] ans = new long[N][N];
        // 행렬 곱셈
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    // mod 연산 해준다.
                    ans[i][j] += (a[i][k] * b[k][j]) % mod;
                }
            }
        }
        // 모두 합한 후 mod연산을 한번 더 해준다.
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                ans[i][j] = ans[i][j] % mod;
            }
        }
        return ans;
    }


    public static void main(String[] args) throws IOException { //조건 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        long B = Long.parseLong(st.nextToken());
        arr1 = new long[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                arr1[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        long[][] ans = dfs(B);

        // 출력
        // 출력에 mod연산을 한번 더 하는 이유 :
        // 2 1
        // 1000 1000
        // 1000 1000
        // dfs(1)인 경우 mod연산이 되지 않은 arr1을 넘겨 받는다
        // 모든 값에 mod연산을 해야하기 때문에 위 예제의 정답은
        // 0 0
        // 0 0 이다.
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(ans[i][j]%mod).append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
}