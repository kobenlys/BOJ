import java.io.*;
import java.util.*;

public class Main {
    public static int[] parent;
    
    // 유니온 - 파인드 알고리즘
    public static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    public static void union(int x, int y) {
        int from = find(x);
        int to = find(y);
        if (from != to) {
            parent[to] = from;
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        parent = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < N - 2; i++) {
            st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            union(S, E);
        }

        for (int i = 2; i <= N; i++) {
            if (find(1) != find(i)) { // 같은 집합 아니라면 출력
                System.out.println(i + " " + 1);
                System.exit(0);
            }
        }
    }
}