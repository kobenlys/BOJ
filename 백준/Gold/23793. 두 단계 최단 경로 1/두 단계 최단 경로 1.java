import java.io.*;
import java.util.*;

public class Main {
    public static int N, M;
    public static ArrayList<ArrayList<node>> arr1;

    public static class node implements Comparable<node> {
        int goal, val;

        public node(int goal, int val) {
            this.goal = goal;
            this.val = val;
        }

        @Override
        public int compareTo(node o) {
            return val - o.val;
        }
    }

    public static int dijkstra(int s, int target, int e) {
        PriorityQueue<node> pq = new PriorityQueue<>();
        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[s] = 0;
        pq.offer(new node(s, 0));
        
        while (!pq.isEmpty()) {

            node now = pq.poll();

            if (dist[now.goal] < now.val) continue;
            if (now.goal == target) continue;

            for (node tmp : arr1.get(now.goal)) {
                if (dist[tmp.goal] > dist[now.goal] + tmp.val) {
                    dist[tmp.goal] = dist[now.goal] + tmp.val;
                    pq.offer(new node(tmp.goal, dist[tmp.goal]));
                }
            }
        }

        return dist[e];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr1 = new ArrayList<>();

        for (int i = 0; i <= N; i++) {
            arr1.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            arr1.get(s).add(new node(e, v));
        }

        st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int z = Integer.parseInt(st.nextToken());

        // y를 거쳐가는 경우

        long res1 = dijkstra(x, 0, y);
        res1 += dijkstra(y, 0, z);
        System.out.print(res1 >= Integer.MAX_VALUE ? -1 + " " : res1 + " ");

        // y를 거치지 않는 경우
        long res2 = dijkstra(x, y, z);
        System.out.print(res2 >= Integer.MAX_VALUE ? -1 : res2);
    }
}
