import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {

            String str = br.readLine();
            int lose = 0;

            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) != 'o') {
                    lose++;
                }
            }

            sb.append("#").append(t).append(" ");
            sb.append(lose >= 8 ? "NO" : "YES");
            sb.append("\n");
        }
        System.out.print(sb);
    }
}