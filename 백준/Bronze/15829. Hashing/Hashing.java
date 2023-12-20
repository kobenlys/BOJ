import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int L = Integer.parseInt(br.readLine());
        BigInteger ans = new BigInteger("0");
        BigInteger r = new BigInteger("31");
        String input = br.readLine();

        for (int i = 0; i < L; i++) {
            BigInteger str = BigInteger.valueOf(input.charAt(i) - 'a' + 1);
            BigInteger numP = r.pow(i);
            ans = ans.add(str.multiply(numP).mod(BigInteger.valueOf(1234567891)));
        }
        System.out.print(ans.mod(BigInteger.valueOf(1234567891)));
    }
}