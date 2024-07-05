import java.io.*;
import java.util.*;

public class Main {
    public static int flag;
    public static String str, number;
    public static int[] dp;
    public static String[] arr1 = {"ZERO", "ONE", "TWO", "THREE",
            "FOUR", "FIVE", "SIX", "SEVEN", "EIGHT", "NINE"};

    public static void dfs(String num, int cnt) {
        if (flag == 1) return;
        if(cnt > str.length()) return;

        if (cnt == str.length()) {
            for (int i = 0; i < 26; i++) {
                if (dp[i] != 0) return;
            }

            number = num;
            flag = 1;
            return;
        }

        for (int i = 0; i <= 9; i++) {
            String pivot = arr1[i];

            for (int j = 0; j < pivot.length(); j++) {
                dp[pivot.charAt(j) - 'A']--;
            }
            dfs(num + i, cnt + pivot.length());

            for (int j = 0; j < pivot.length(); j++) {
                dp[pivot.charAt(j) - 'A']++;
            }
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            str = br.readLine();
            flag = 0;
            number = "";
            dp = new int[26];

            for (int i = 0; i < str.length(); i++) {
                char a = str.charAt(i);
                dp[a - 'A']++;
            }

            dfs("", 0);
            sb.append("Case #").append(tc).append(": ");
            sb.append(number).append("\n");
        }
        System.out.println(sb);
    }
}