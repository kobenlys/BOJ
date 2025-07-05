import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {

    public static void isEmpty(StringBuilder sb, List<BigInteger> list) {
        if (sb.length() > 0) {
            list.add((new BigInteger(sb.toString())));
            sb.setLength(0);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        List<BigInteger> list = new ArrayList<>();
        // 답은 오른차순 정렬

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            sb.setLength(0);

            for (int j = 0; j < str.length(); j++) {
                if (Character.isDigit(str.charAt(j))) {
                    sb.append(str.charAt(j));
                } else {
                    isEmpty(sb, list);
                }
            }
            isEmpty(sb, list);
        }

        isEmpty(sb, list);
        Collections.sort(list);

        for (BigInteger e : list) {
            sb.append(e).append("\n");
        }
        System.out.println(sb);
    }
}