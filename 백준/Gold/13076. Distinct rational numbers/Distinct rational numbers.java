import java.io.*;
import java.util.*;

public class Main {

    // 오일러의 피 함수.
    public static int euler_Phi(int N) {
        int res = N;

        for (int i = 2; i <= Math.sqrt(N); i++) {
            if(N % i == 0){
                while(N % i == 0){
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
        // 시간단축 위해 누적합구하기
        for (int i = 1; i <= 10000; i++) {
            arr1[i] = euler_Phi(i) + arr1[i - 1];
        }

        // 분수로 표현 가능해야함. 1/2, 1/3 이렇게 하지만 2/4 는 1/2로 변환 가능 중복제거해줘야함
        // 즉, 최대공약수가 1인 짝만 카운트 필요함

        while (T-- > 0) {
            // 출력
            int N = Integer.parseInt(br.readLine());
            sb.append(arr1[N] + 1).append("\n");
        }
        System.out.print(sb);
    }
}