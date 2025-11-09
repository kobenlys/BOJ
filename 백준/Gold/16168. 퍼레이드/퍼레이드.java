import java.io.*;
import java.util.*;

public class Main {

    public static int N, M;
    public static boolean[] vi;
    public static List<List<Integer>> list = new ArrayList<>();

    public static int dfs(int idx) {
        int cnt = 0;
        for (int e : list.get(idx)) {
            if (!vi[e]) {
                vi[e] = true;
                cnt += dfs(e) + 1;
            }
        }

        return cnt;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        vi = new boolean[N + 1];

        for (int i = 0; i <= N; i++) {
            list.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            list.get(s).add(e);
            list.get(e).add(s);
        }

        if (N != dfs(1)) {
            System.out.println("NO");
            return;
        }

        int odd = 0;
        int even = 0;
        for (int i = 1; i <= N; i++) {
            if (list.get(i).size() % 2 == 0) {
                even++;
            } else {
                odd++;
            }
        }

        if (odd == 2 || even == N) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}