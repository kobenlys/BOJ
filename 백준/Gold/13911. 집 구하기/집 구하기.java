import java.io.*;
import java.util.*;

public class Main {

    public static int V, E;
    public static List<List<Node>> list;

    public static class Node implements Comparable<Node> {

        int goal, value;

        public Node(int goal, int value) {
            this.goal = goal;
            this.value = value;
        }

        @Override
        public int compareTo(Node o) {
            return value - o.value;
        }
    }

    public static int[] dijkstra(List<Integer> nodes, int condition) {

        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[] dist = new int[V + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        for (int e : nodes) {
            dist[e] = 0;
            pq.offer(new Node(e, 0));
        }

        while (!pq.isEmpty()) {

            Node nd = pq.poll();
            if (dist[nd.goal] < nd.value) {
                continue;
            }

            for (Node tmp : list.get(nd.goal)) {
                int nextDist = dist[nd.goal] + tmp.value;
                if (dist[tmp.goal] > nextDist && nextDist <= condition) {
                    dist[tmp.goal] = nextDist;
                    pq.offer(new Node(tmp.goal, nextDist));
                }
            }
        }

        return dist;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        int answerNode = Integer.MAX_VALUE;
        list = new ArrayList<>();
        List<Integer> nodes = new ArrayList<>();

        for (int i = 0; i <= V; i++) {
            list.add(new ArrayList<>());
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            list.get(s).add(new Node(e, v));
            list.get(e).add(new Node(s, v));
        }

        st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            nodes.add(Integer.parseInt(st.nextToken()));
        }
        int[] isMacDist = dijkstra(nodes, X);

        st = new StringTokenizer(br.readLine());
        int S = Integer.parseInt(st.nextToken());
        int Y = Integer.parseInt(st.nextToken());
        nodes.clear();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < S; i++) {
            nodes.add(Integer.parseInt(st.nextToken()));
        }
        int[] isStarDist = dijkstra(nodes, Y);

        for (int i = 1; i <= V; i++) {

            if (isMacDist[i] == Integer.MAX_VALUE || isStarDist[i] == Integer.MAX_VALUE
                || isMacDist[i] == 0 || isStarDist[i] == 0) {
                continue;
            }
            answerNode = Math.min(answerNode, isMacDist[i] + isStarDist[i]);
        }

        System.out.println(answerNode == Integer.MAX_VALUE ? -1 : answerNode);
    }
}
