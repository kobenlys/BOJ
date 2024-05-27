import java.io.*;
import java.util.*;

public class Main {

    public static int eulerPhi(int N) { // 오일러의 피 함수
        int res = N;

        for (int i = 2; i <= Math.sqrt(N); i++) {
            // i가 N의 소인수 라면?
            if (N % i == 0) {
                // N에서 i의 배수 제거.
                while (N % i == 0) {
                    N /= i;
                }
                res -= res / i; // 값 조정.
            }
        }
        if (N > 1) res -= res / N;
        return res;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int answer = 0;
        
        // 현재 자리에서 새로운 서로소를 찾았다면 answer에 더해준다.
        for (int i = 2; i <= N; i++) {
            answer += eulerPhi(i);
        }
        System.out.println(answer);
    }
}