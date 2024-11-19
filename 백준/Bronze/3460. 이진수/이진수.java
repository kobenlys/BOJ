import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {

            int num = Integer.parseInt(br.readLine());

            String str = Integer.toBinaryString(num);

            for (int i = str.length()-1; i >= 0; i--) {
                if (str.charAt(i) == '1') {
                    sb.append(str.length() - i - 1).append(" ");
                }
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}