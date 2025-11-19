import java.io.*;
import java.util.*;

public class Main {

    public static int[][] validIdx;
    public static boolean[] vi = new boolean[26];
    public static List<String> list = new ArrayList<>();

    public static boolean isAllowed(String baseMap) {

        for (int i = 0; i < 6; i++) {
            int res = 4;

            if (baseMap.charAt(validIdx[i][0]) == 'x' || baseMap.charAt(validIdx[i][1]) == 'x' ||
                baseMap.charAt(validIdx[i][2]) == 'x' || baseMap.charAt(validIdx[i][3]) == 'x') {
                continue;
            }
            res += baseMap.charAt(validIdx[i][0]) - 'A';
            res += baseMap.charAt(validIdx[i][1]) - 'A';
            res += baseMap.charAt(validIdx[i][2]) - 'A';
            res += baseMap.charAt(validIdx[i][3]) - 'A';
            if (res != 26) {
                return false;
            }
        }
        return true;
    }

    public static void dfs(int idx, String baseMap) {

        if (!isAllowed(baseMap)) {
            return;
        }

        if (idx == baseMap.length()) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 9; j++) {
                    sb.append(baseMap.charAt(i * 9 + j));
                }
                sb.append("\n");
            }

            System.out.println(sb);
            System.exit(0);
        }

        if (baseMap.charAt(idx) == 'x') {

            for (int i = 0; i < 12; i++) {
                if (!vi[i]) {
                    vi[i] = true;
                    StringBuilder sb = new StringBuilder(baseMap);
                    sb.setCharAt(idx, (char) ('A' + i));
                    dfs(idx + 1, sb.toString());
                    vi[i] = false;
                }
            }
        } else {
            dfs(idx + 1, baseMap);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        for (int i = 0; i < 5; i++) {
            String tmp = br.readLine();
            for (int j = 0; j < 9; j++) {
                if (tmp.charAt(j) == '.') {
                    sb.append(".");
                } else if (tmp.charAt(j) != 'x') {
                    int num = tmp.charAt(j) - 'A';
                    vi[num] = true;
                    sb.append(tmp.charAt(j));
                } else {
                    sb.append("x");
                }
            }
        }

        String baseMap = sb.toString();
        validIdx = new int[][]
            {
                {4, 12, 20, 28},
                {4, 14, 24, 34},
                {28, 30, 32, 34},
                {10, 12, 14, 16},
                {10, 20, 30, 40},
                {16, 24, 32, 40}
            };

        dfs(0, baseMap);
    }
}