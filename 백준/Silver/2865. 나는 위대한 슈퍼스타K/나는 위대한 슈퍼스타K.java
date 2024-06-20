import java.io.*;
import java.util.*;

public class Main {

    public static class node implements Comparable<node> {
        int idx;
        double score;

        public node(int idx, double score) {
            this.idx = idx;
            this.score = score;
        }

        @Override
        public int compareTo(node o) {
            return Double.compare(o.score, score);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        boolean[] vi = new boolean[N + 1];
        double answer = 0;

        PriorityQueue<node> pq = new PriorityQueue<node>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int idx = Integer.parseInt(st.nextToken());
                double score = Double.parseDouble(st.nextToken());
                pq.offer(new node(idx, score));
            }
        }

        while (!pq.isEmpty() && K > 0) {

            node now = pq.poll();
            if (!vi[now.idx]) {
                vi[now.idx] = true;
                K--;
                answer += now.score;
            }
        }

        System.out.printf("%.1f\n", answer);
    }
}