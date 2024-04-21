import java.io.*;
import java.util.*;

public class Main {
    public static int[] parent;

    public static int find(int x) {
        if(parent[x] == x) return x;
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
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int ans = 0;
        parent = new int[N+1];

        int P = Integer.parseInt(br.readLine());

        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < P; i++) {
            int g = Integer.parseInt(br.readLine());

            if (find(g) == 0) {
                break;
            }

            union(find(g) - 1, g);
            ans++;
        }
        System.out.print(ans);
    }
}