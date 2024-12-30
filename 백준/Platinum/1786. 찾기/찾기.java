import java.io.*;
import java.util.*;

public class Main {

    public static int[] makePattern(String str) {
        int[] pattern = new int[str.length()];
        int pos = 0;
        for (int i = 1; i < pattern.length; i++) {
            while (pos > 0 && str.charAt(i) != str.charAt(pos)) {
                pos = pattern[pos - 1];
            }
            if (str.charAt(i) == str.charAt(pos)) {
                pattern[i] = ++pos;
            }
        }
        return pattern;
    }

    public static int kmp(String str1, String str2, int[] pattern, StringBuilder sb) {
        int matchCnt = 0;
        int pos = 0;

        for (int i = 0; i < str1.length(); i++) {
            while (pos > 0 && str1.charAt(i) != str2.charAt(pos)) {
                pos = pattern[pos - 1];
            }

            if (str1.charAt(i) == str2.charAt(pos)) {
                if (str2.length() - 1 == pos) {
                    sb.append(i - pos + 1).append(" ");
                    matchCnt++;
                    pos = pattern[pos];
                } else {
                    pos++;
                }
            }
        }
        return matchCnt;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        String str1 = br.readLine();
        String str2 = br.readLine();

        int[] pattern = makePattern(str2);
        int cnt = kmp(str1, str2, pattern, sb);
        System.out.println(cnt);
        System.out.println(sb);
    }
}