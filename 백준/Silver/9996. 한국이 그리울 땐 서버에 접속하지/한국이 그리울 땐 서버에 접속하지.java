import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        String[] matcher = br.readLine().split("\\*");

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            String answer = "DA";
            int strLen = str.length();

            if (str.length() < matcher[0].length() + matcher[1].length()) {
                sb.append("NE").append("\n");
                continue;
            }

            for (int j = 0; j < matcher[0].length(); j++) {
                if (matcher[0].charAt(j) != str.charAt(j)) {
                    answer = "NE";
                    break;
                }
            }

            for (int j = matcher[1].length() - 1; j >= 0; j--) {
                if (matcher[1].charAt(j) != str.charAt(--strLen)) {
                    answer = "NE";
                    break;
                }
            }

            sb.append(answer).append("\n");
        }
        System.out.println(sb);
    }
}