import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException { // 값 입력
        BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));
        StringTokenizer st;

        String N = br.readLine();

      
         if (N.equals("1")) {
            System.out.println(1);
        } else {
            BigInteger n1 = new BigInteger(N);
            BigInteger n2 = (n1.subtract(BigInteger.valueOf(2)));
            System.out.println(n1.add(n2));
        }
       
    }
}