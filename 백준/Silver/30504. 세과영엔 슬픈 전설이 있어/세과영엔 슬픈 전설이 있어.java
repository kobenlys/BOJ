import java.io.*;
import java.util.*;

public class Main {

    public static class node implements Comparable<node> {
        int n, idx;

        public node(int n, int idx) {
            this.n = n;
            this.idx = idx;
        }

        @Override
        public int compareTo(node o) {
            return n - o.n;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] res = new int[N + 1];
        PriorityQueue<node> pq1 = new PriorityQueue<>();
        PriorityQueue<Integer> pq2 = new PriorityQueue<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());
            pq1.offer(new node(num, i));
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            pq2.offer(Integer.parseInt(st.nextToken()));
        }

        while (!pq1.isEmpty()) {
            node A = pq1.poll();
            int B = pq2.poll();

            if (A.n <= B) {
                res[A.idx] = B;
            } else {
                System.out.println(-1);
                System.exit(0);
            }
        }

        for (int i = 0; i < N; i++) {
            sb.append(res[i]).append(" ");
        }

        System.out.println(sb);
    }
}