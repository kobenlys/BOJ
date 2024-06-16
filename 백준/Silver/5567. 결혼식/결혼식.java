import java.io.*;
import java.util.*;

public class Main {
    public static boolean[] vi;
    public static HashSet<Integer> set = new HashSet<>();
    public static ArrayList<ArrayList<Integer>> arr1 = new ArrayList<>();

    public static void dfs(int start, int cnt) {

        if (cnt == 2) {
            return;
        }

        for (int e : arr1.get(start)) {
            if (!vi[e]) {
                set.add(e);
                vi[e] = true;
                dfs(e, cnt + 1);
                vi[e] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        vi = new boolean[N + 1];
        for (int i = 0; i <= N; i++) {
            arr1.add(new ArrayList<>());
        }

        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            arr1.get(s).add(e);
            arr1.get(e).add(s);
        }

        vi[1] = true;
        dfs(1, 0);
        System.out.println(set.size());
    }
}