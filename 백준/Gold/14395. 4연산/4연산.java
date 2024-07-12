import java.io.*;
import java.util.*;

public class Main {
    public static int N, M;

    public static class node {
        long num;
        String str;

        public node(long num, String str) {
            this.num = num;
            this.str = str;
        }
    }

    public static String bfs() {
        Queue<node> qu = new LinkedList<>();
        HashSet<Long> set = new HashSet<>();
        qu.offer(new node(N, ""));

        if (N == M) return "0";

        while (!qu.isEmpty()) {

            node nd = qu.poll();
            long num = nd.num;

            if (num == M) return nd.str;
            if (set.contains(num)) continue;
            set.add(num);

            qu.offer(new node(num * num, nd.str + "*"));
            qu.offer(new node(num + num, nd.str + "+"));
            if (num != 0) {
                qu.offer(new node(1L, nd.str + "/"));
            }
        }

        return "-1";
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        if (M == 0) {
            System.out.println("-");
        } else {
            System.out.println(bfs());
        }
    }
}