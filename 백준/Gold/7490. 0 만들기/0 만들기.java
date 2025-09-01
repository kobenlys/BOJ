import java.io.*;
import java.util.*;

public class Main {

    public static List<String> list;

    public static boolean isZero(String trace) {

        int result = 0;
        char operator = '+';

        for (int i = 0; i < trace.length(); i++) {

            if (!Character.isDigit(trace.charAt(i))) {
                operator = trace.charAt(i);
                continue;
            }

            int number = 0;
            for (int j = i; j < trace.length(); j++) {

                if (Character.isDigit(trace.charAt(j))) {
                    number = number * 10 + trace.charAt(j) - '0';
                } else if (trace.charAt(j) == '+' || trace.charAt(j) == '-') {
                    i = j - 1;
                    break;
                }
            }

            if (operator == '+') {
                result += number;
            } else if (operator == '-') {
                result -= number;
            }
        }

        return result == 0;
    }

    public static void dfs(int idx, int target, String trace) {

        if (idx > target) {
            if (isZero(trace)) {
                list.add(trace);
            }
            return;
        }

        dfs(idx + 1, target, trace + "+" + idx);
        dfs(idx + 1, target, trace + "-" + idx);
        dfs(idx + 1, target, trace + " " + idx);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {

            int N = Integer.parseInt(br.readLine());
            list = new ArrayList<>();
            dfs(2, N, "1");
            Collections.sort(list);

            list.forEach(s -> sb.append(s).append("\n"));
            sb.append("\n");
        }

        System.out.println(sb);
    }
}
