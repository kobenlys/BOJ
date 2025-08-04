import java.io.*;
import java.util.*;

public class Main {

    public static class Node implements Comparable<Node> {

        int a, b;

        public Node(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public int compareTo(Node o) {
            if (a == o.a) {
                return b - o.b;
            }
            return a - o.a;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        List<Node> list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list.add(new Node(a, b));
        }

        Collections.sort(list);

        int answer = 1;
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (Node nd : list) {

            if (!pq.isEmpty() && pq.peek() <= nd.a) {
                pq.poll();
            }
            pq.offer(nd.b);

            answer = Math.max(answer, pq.size());
        }

        System.out.println(answer);
    }
}
