import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        String str1 = br.readLine();
        String str2 = br.readLine();
        sb.append(str2);


        while (true) {

            str2 = sb.toString();
            if (str2.isEmpty()) break;


            if (str1.equals(str2)) {
                System.out.println(1);
                System.exit(0);
            }

            if (str2.charAt(str2.length() - 1) == 'A') {
                sb.delete(str2.length() - 1, str2.length());
            } else if (str2.charAt(str2.length() - 1) == 'B') {
                sb.delete(str2.length() - 1, str2.length());
                String tmp = sb.reverse().toString();
                sb.setLength(0);
                sb.append(tmp);
            }
        }

        System.out.println(0);
    }
}