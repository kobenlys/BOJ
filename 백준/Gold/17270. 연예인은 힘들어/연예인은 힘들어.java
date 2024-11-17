import java.io.*;
import java.util.*;

public class Main {
    public static int N, M;
    public static List<List<Node>> list = new ArrayList<>();

    public static class Node implements Comparable<Node> {
        int goal, len;

        public Node(int goal, int len) {
            this.goal = goal;
            this.len = len;
        }

        @Override
        public int compareTo(Node o) {
            return len - o.len;
        }
    }

    public static int[] dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {

            Node now = pq.poll();

            if (dist[now.goal] < now.len) {
                continue;
            }

            for (Node tmp : list.get(now.goal)) {
                if (dist[tmp.goal] > dist[now.goal] + tmp.len) {
                    dist[tmp.goal] = dist[now.goal] + tmp.len;
                    pq.offer(new Node(tmp.goal, dist[tmp.goal]));
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
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

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

        st = new StringTokenizer(br.readLine());
        int ji = Integer.parseInt(st.nextToken());
        int sung = Integer.parseInt(st.nextToken());
        int[] dist1 = dijkstra(ji);
        int[] dist2 = dijkstra(sung);

        int minLen = Integer.MAX_VALUE;

        for (int i = 1; i <= N; i++) {
            if(i == ji || i == sung) continue;
            minLen = Math.min(minLen, dist1[i] + dist2[i]);
        }

        int jiLen = Integer.MAX_VALUE;
        int answer = Integer.MAX_VALUE;

        for (int i = 1; i <= N; i++) {
            if(dist1[i] == Integer.MAX_VALUE || dist2[i] == Integer.MAX_VALUE)continue;
            if(i == ji || i == sung) continue; // 조건1
            if(minLen != dist1[i] + dist2[i]) continue; //조건2
            if(dist1[i] > dist2[i]) continue; // 조건 3

            if (jiLen == dist1[i]) {
                answer = Math.min(answer, i);
            }else if(jiLen > dist1[i]){
                answer = i;
                jiLen = dist1[i];
            }

            answer = Math.min(answer, i);
        }
        if (answer == Integer.MAX_VALUE) {
            answer = -1;
        }
        System.out.println(answer);
    }
}