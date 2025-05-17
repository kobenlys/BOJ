import java.io.*;
import java.util.*;

public class Main {

    public static int N, M;
    public static List<List<Node>> list = new ArrayList<>();
    public static List<Integer> friend = new ArrayList<>();

    public static class Node implements Comparable<Node> {

        int goal, dist;

        public Node(int goal, int dist) {
            this.goal = goal;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node o) {
            return this.dist - o.dist;
        }
    }

    public static int findNowDistance(int[] dist) {
        int result = 0;

        for (int index : friend) {
            if(dist[index] == Integer.MAX_VALUE) continue;
            result += dist[index];
        }

        return result;
    }

    public static int dijkstra(int targetRoom){

        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[targetRoom] = 0;
        pq.offer(new Node(targetRoom, 0));

        while (!pq.isEmpty()) {
            Node now = pq.poll();

            if(dist[now.goal] < now.dist) continue;

            for (Node tmp : list.get(now.goal)) {

                if (dist[tmp.goal] > dist[now.goal] + tmp.dist) {
                    dist[tmp.goal] = dist[now.goal] + tmp.dist;
                    pq.offer(new Node(tmp.goal, dist[tmp.goal]));
                }
            }
        }
//        System.out.println(Arrays.toString(dist));
//        System.out.println("res : " + findNowDistance(dist));
        return findNowDistance(dist);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {

            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            list = new ArrayList<>();
            friend = new ArrayList<>();
            int minTime = Integer.MAX_VALUE;
            int roomNumber = 0;

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

            int K = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < K; i++) {
                friend.add(Integer.parseInt(st.nextToken()));
            }

            for (int i = 1; i <= N; i++) {
                int tmp = dijkstra(i);
                if (minTime > tmp) {
                    minTime = tmp;
                    roomNumber = i;
                }
            }

            sb.append(roomNumber).append("\n");
        }

        System.out.println(sb);
    }
}