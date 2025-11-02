import java.io.*;
import java.util.*;

public class Main {

    public static int N;

    public static boolean isAllowedString(String tget) {
        int size = tget.length() / 2;
        int startIdx = tget.length();

        for (int i = 1; i <= size; i++) {
            boolean isSame = true;
            for (int j = 0; j < i; j++) {

                int left = startIdx - i * 2;
                int right = startIdx - i;
                if (tget.charAt(left + j) != tget.charAt(right + j)) {
                    isSame = false;
                }
            }

            if (isSame) {
                return false;
            }
        }
        return true;
    }

    public static void dfs(String str) {

        if (str.length() == N) {
            System.out.println(str);
            System.exit(0);
        }

        for (int i = 1; i <= 3; i++) {

            String nxtStr = str + i;
            if (isAllowedString(nxtStr)) {
                dfs(nxtStr);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        dfs("1");

    }
}