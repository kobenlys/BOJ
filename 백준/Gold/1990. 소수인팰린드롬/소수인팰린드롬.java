import java.io.*;
import java.util.*;

public class Main {
    public static int A, B;
    public static boolean[] prime;
    
    // 소수 판정
    public static void E_Sieve() {
        prime[0] = prime[1] = true;
        // 제곱근까지 에라토스테네스 체 실행하기
        for (int i = 2; i <= Math.sqrt(B); i++) {

            if (prime[i]) continue;
            for (int j = i + i; j <= B; j += i) {
                prime[j] = true;
            }
        }
    }
    
    // 회문 검사, 문자열 길이가 짧으니 reverse로 처리
    public static boolean isPalindrome(int n) {
        StringBuilder tmp = new StringBuilder();

        String str = String.valueOf(n);
        tmp.append(str);
        String reverse = tmp.reverse().toString();
        if (str.equals(reverse)) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        prime = new boolean[B + 1];
        E_Sieve();

        for (int i = A; i <= B; i++) {
            if (!prime[i]) {
                if (isPalindrome(i)) {
                    sb.append(i).append("\n");
                }
            }
        }
        sb.append(-1);
        System.out.println(sb);
    }
}