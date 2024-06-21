import java.io.*;
import java.util.*;

public class Main {
    public static HashSet<String> set = new HashSet<>();

    public static void dfs(String now, String target) {

        if (set.contains(now)) return;
        if (now.length() < target.length()) return;

        set.add(now);

        if (now.equals(target)) {
            System.out.println(1);
            System.exit(0);
        }

        if (now.charAt(now.length() - 1) == 'A') {
            StringBuilder sb = new StringBuilder(now);
            sb.delete(now.length() - 1, now.length());
            String tmp = sb.toString();
            dfs(tmp, target);

        }

        if (now.charAt(0) == 'B') {
            StringBuilder sb = new StringBuilder(now);
            sb.delete(0, 1);
            String tmp = sb.reverse().toString();
            dfs(tmp, target);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        String S = br.readLine();
        String T = br.readLine();

        dfs(T, S);
        System.out.println(0);
    }
}