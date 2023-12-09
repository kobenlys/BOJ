import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        // N = 500 일때 매우 큰 수(2^62 초과) 이므로 BigInteger을 사용한다
        BigInteger factorial = BigInteger.valueOf(1);

        for (int i = 1; i <= N; i++) {
            // .multiply -> 곱셈
            factorial = factorial.multiply(BigInteger.valueOf(i));
        }

        String res = String.valueOf(factorial);
        
        for (int i = res.length() - 1; i >= 0; i--) {
            if (res.charAt(i) != '0') {
                System.out.println(res.length() - i - 1);
                break;
            }
        }
    }
}
