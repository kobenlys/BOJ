import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        while (true) {

            String num = br.readLine();
            if(num.equals("0")) break;

            while (num.length() != 1) {

                int tmp = 0;
                for (int i = 0; i < num.length(); i++) {
                    tmp += num.charAt(i) - '0';
                }
                num = String.valueOf(tmp);
            }
            sb.append(num).append("\n");
        }

        System.out.println(sb);
    }
}