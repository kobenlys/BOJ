import java.io.*;
import java.util.*;

public class Main {
    public static int[] parent;
    private static int[] ans;
    public static HashMap<String, Integer> map = new HashMap<>();

    public static int find(int x) {
        if(parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    private static void union(int x, int y) {
        int from = find(x);
        int to = find(y);

        if (from != to) {
            ans[from] += ans[to];
            parent[to] = from;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();


        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            map = new HashMap<>();

            int F = Integer.parseInt(br.readLine());
            int cnt = 0;
            parent = new int[200_001];
            ans = new int[200_001];

            for (int i = 0; i < 200_001; i++) {
                parent[i] = i;
                ans[i] = 1;
            }

            for (int i = 0; i < F; i++) {
                st = new StringTokenizer(br.readLine());
                String name1 = st.nextToken();
                String name2 = st.nextToken();

                if (!map.containsKey(name1)) {
                    map.put(name1, cnt++);
                }

                if (!map.containsKey(name2)) {
                    map.put(name2, cnt++);
                }

                union(map.get(name1), map.get(name2));
                sb.append(ans[find(map.get(name1))]).append("\n");
            }
        }
        System.out.print(sb);
    }
}