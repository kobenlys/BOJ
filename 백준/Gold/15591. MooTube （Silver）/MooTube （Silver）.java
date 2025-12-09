import java.io.*;
import java.util.*;

public class Main {

    public static int N, M;
    public static boolean[] vi;
    public static List<List<Node>> list = new ArrayList<>();

    public static class Node {

        int goal, val;

        public Node(int goal, int val) {
            this.goal = goal;
            this.val = val;
        }
    }

    public static int dfs(int node, int limit) {
        int res = 0;
        for (Node nxt : list.get(node)) {

            if (!vi[nxt.goal]) {
                if (nxt.val >= limit) {
                    vi[nxt.goal] = true;
                    res += dfs(nxt.goal, limit) + 1;
                    vi[nxt.goal] = false;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        vi = new boolean[N + 1];
        list = new ArrayList<>();

        for (int i = 0; i <= N; i++) {
            list.add(new ArrayList<>());
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            list.get(s).add(new Node(e, v));
            list.get(e).add(new Node(s, v));
        }

        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int K = Integer.parseInt(st.nextToken());
            int target = Integer.parseInt(st.nextToken());
            vi[target] = true;
            int num = dfs(target, K);
            vi[target] = false;
            sb.append(num).append("\n");
        }
        System.out.println(sb);
    }
}