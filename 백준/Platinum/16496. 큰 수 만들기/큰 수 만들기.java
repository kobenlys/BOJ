import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        List<String> arr1 = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr1.add(st.nextToken());
        }

        Collections.sort(arr1);

        for (int i = 0; i < N; i++) {
            String n1 = arr1.get(i);
            if (n1.equals("0")) continue;

            for (int j = i + 1; j < N; j++) {
                String n2 = arr1.get(j);

                if (n1.equals(n2)) continue;

                if (n1.charAt(0) == n2.charAt(0)) {
                    BigInteger num1 = new BigInteger(n1.concat(n2));
                    BigInteger num2 = new BigInteger(n2.concat(n1));

                    if (num1.compareTo(num2) > 0) {
                        arr1.add(j, n1);
                        arr1.remove(j + 1);
                        arr1.add(i, n2);
                        arr1.remove(i + 1);
                        break;
                    }
                }
            }
        }

        for (int i = N - 1; i >= 0; i--) {
            sb.append(arr1.get(i));
        }

        if (sb.toString().charAt(0) == '0') {

            System.out.println(0);
        } else {
            System.out.println(sb);
        }
    }
}