import java.io.*;
import java.util.*;

public class Main {
    public static int N, M, K;
    public static List<List<Node>> list = new ArrayList<>();


    public static class Node implements Comparable<Node>{
        int goal, val;

        public Node(int goal, int val) {
            this.goal = goal;
            this.val = val;
        }

        @Override
        public int compareTo(Node o) {
            return val - o.val;
        }
    }

    public static boolean dijkstra(int target) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[1] = 0;
        pq.offer(new Node(1, 0));

        while (!pq.isEmpty()) {
            Node now = pq.poll();
            if (dist[now.goal] < now.val) {
                continue;
            }

            for (Node tmp : list.get(now.goal)) {

                int nextCost = now.val;

                if (target < tmp.val) {
                    nextCost++;
                }

                if (dist[tmp.goal] > nextCost) {
                    dist[tmp.goal] = nextCost;
                    pq.offer(new Node(tmp.goal, dist[tmp.goal]));
                }
            }
        }
        return dist[N] <= K;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= N; i++) {
            list.add(new ArrayList<>());
        }


        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            list.get(s).add(new Node(e, v));
            list.get(e).add(new Node(s, v));
        }

        int left =0;
        int right = 1_000_000;
        int answer = -1;

        while (left <= right) {

            int mid = (left + right) / 2;

            if (dijkstra(mid)) {
                answer = mid;
                right = mid - 1;
            }else{
                left = mid + 1;
            }
        }

        System.out.println(answer);
    }
}