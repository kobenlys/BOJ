import java.io.*;
import java.util.*;

public class Main {
    public static int N, M, answer = Integer.MAX_VALUE;
    public static ArrayList<node> chicken = new ArrayList<>();
    public static ArrayList<node> home = new ArrayList<>();
    public static boolean[] vi;

    public static class node {
        int x, y;

        public node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void dfs(int start, int idx) {

        if (start == M) {
            int res = 0;

            for (int i = 0; i < home.size(); i++) {
                int cnt = Integer.MAX_VALUE;
                node p1 = home.get(i);

                for (int j = 0; j < chicken.size(); j++) {
                    if (vi[j]) {
                        node p2 = chicken.get(j);
                        cnt = Math.min(cnt, Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y));
                    }
                }
                res += cnt;
            }

            answer = Math.min(answer, res);
            return;
        }


        for (int i = idx; i < chicken.size(); i++) {
            vi[i] = true;
            dfs(start + 1, i + 1);
            vi[i] = false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int num = Integer.parseInt(st.nextToken());
                if (num == 1) {
                    home.add(new node(j, i));
                }

                if (num == 2) {
                    chicken.add(new node(j, i));
                }
            }
        }

        vi = new boolean[chicken.size()];

        dfs(0, 0);
        System.out.println(answer);
    }
}