import java.io.*;
import java.util.*;

public class Main {
    public static int A, B;
    public static boolean[] prime;

    public static void E_Sieve() {

        prime[0] = prime[1] = true;

        for (int i = 2; i <= Math.sqrt(B); i++) {

            if (prime[i]) continue;
            for (int j = i + i; j <= B; j += i) {
                prime[j] = true;
            }
        }
    }

    public static boolean isPalindrome(int n) {

        String str = String.valueOf(n);

        for (int i = 0; i < str.length()/2; i++) {

            if (str.charAt(i) != str.charAt(str.length() - i - 1)) {
                return false;
            }
        }
        return true;
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