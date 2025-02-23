import java.io.*;
import java.util.*;

public class Main {
    public static List<List<Node>> list = new ArrayList<>();

    public static class Node implements Comparable<Node>{
        int goal, value;
        public Node(int goal, int value) {
            this.goal = goal;
            this.value = value;
        }

        @Override
        public int compareTo(Node o){
            return value - o.value;
        }
    }

    public static String dijkstra(int start){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[] dist = new int[list.size()+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;
        int count = 0;
        int time = 0;
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {

            Node now = pq.poll();

            if (dist[now.goal] < now.value) {
                continue;
            }

            for (Node tmp : list.get(now.goal)) {

                if (dist[tmp.goal] > dist[now.goal] + tmp.value) {
                    dist[tmp.goal] = dist[now.goal] + tmp.value;
                    pq.offer(new Node(tmp.goal, dist[tmp.goal]));
                }
            }
        }

        for (int i = 1; i <= list.size(); i++) {
            if (dist[i] != Integer.MAX_VALUE) {
                count++;
                time = Math.max(time, dist[i]);
            }
        }

        return count + " " + time;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            list = new ArrayList<>();
            for (int i = 0; i <= N; i++) {
                list.add(new ArrayList<>());
            }

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                list.get(e).add(new Node(s, v));
            }

            sb.append(dijkstra(K)).append("\n");
        }
        System.out.println(sb);
    }
}