import java.io.*;
import java.util.*;

public class Main {

    public static class node implements Comparable<node> {
        int x, y;

        public node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(node o) {
            return x - o.x;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PriorityQueue<node> pq = new PriorityQueue<node>();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            pq.offer(new node(x, y));
        }

        node nd = pq.poll();
        int start = nd.x, end = nd.y;
        long answer = 0;

        while (!pq.isEmpty()) {

            node now = pq.poll();
            if (start <= now.x && now.x <= end) {
                end = Math.max(end, now.y);
            } else {
                answer += end - start;
                start = now.x;
                end = now.y;

            }
            if (pq.isEmpty()) {
                answer += end - start;
            }
        }

        if(N == 1) answer = end - start;
        System.out.print(answer);
    }
}