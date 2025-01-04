import java.io.*;
import java.util.*;

public class Main {

    public static int GCD(int a, int b) {
        if (a == 0) return b;
        return GCD(b % a, a);
    }

    public static int[] makePattern(String str) {
        int[] LPS = new int[str.length()];

        int pos = 0;
        for (int i = 1; i < str.length(); i++) {

            while (pos > 0 && str.charAt(pos) != str.charAt(i)) {
                pos = LPS[pos - 1];
            }

            if (str.charAt(pos) == str.charAt(i)) {
                LPS[i] = ++pos;
            }
        }
        return LPS;
    }

    public static int KMP(String str1, String str2, int[] patterns) {
        int matchCnt = 0;
        int pos = 0;

        str1 = str1.concat(str1);

        for (int i = 0; i < str1.length() - 1; i++) {

            while (pos > 0 && str1.charAt(i) != str2.charAt(pos)) {
                pos = patterns[pos - 1];
            }

            if (str1.charAt(i) == str2.charAt(pos)) {
                if (pos == str2.length() - 1) {
                    matchCnt++;
                    pos = patterns[pos];
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

        int N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        String str1;
        String str2;
        while (st.hasMoreTokens()) {
            sb.append(st.nextToken());
        }
        str1 = sb.toString();
        sb.setLength(0);

        st = new StringTokenizer(br.readLine());
        while (st.hasMoreTokens()) {
            sb.append(st.nextToken());
        }
        str2 = sb.toString();

        int[] patterns = makePattern(str2);
        int cnt = KMP(str1, str2, patterns);

        // 최대 공약수 구하기.
        cnt = Math.min(cnt, N);
        int GCD = GCD(N, cnt);
        N /= GCD;
        cnt /= GCD;

        System.out.println(cnt + "/" + N);
    }
}