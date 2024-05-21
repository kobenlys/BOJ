import java.io.*;
import java.util.*;

public class Main {
    public static int[] parent;

    public static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    public static boolean union(int x, int y) {
        int from = find(x);
        int to = find(y);
        if (from != to) {
            parent[to] = from;
            return false;
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = 0;
        while (true) {

            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            if (N == M && N == 0) break;
            T++;

            parent = new int[N];
            boolean[] vi = new boolean[N];

            for (int i = 0; i < N; i++) {
                parent[i] = i;
            }

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken()) - 1;
                int e = Integer.parseInt(st.nextToken()) - 1;

                if(vi[find(s)] || vi[find(e)]) {
                    vi[find(s)] = true;
                    vi[find(e)] = true;
                }

                if (union(s, e)) {
                    vi[find(s)] = true;
                }
            }

            HashSet<Integer> set = new HashSet<>();
            for (int i = 0; i < N; i++) {
                if (!vi[find(i)]) {
                    set.add(find(i));
                }
            }

            if (set.size() == 1) {
                sb.append("Case ").append(T).append(": ").append("There is one tree.")
                        .append("\n");

            } else if (set.isEmpty()) {
                sb.append("Case ").append(T).append(": ").append("No trees.").append("\n");

            } else {
                sb.append("Case ").append(T).append(": ").append("A forest of ")
                        .append(set.size()).append(" trees.").append("\n");
            }

        }
        System.out.print(sb);
    }
}