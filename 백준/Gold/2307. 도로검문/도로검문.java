import java.io.*;
import java.util.*;

public class Main {
    public static int N, M;
    public static int[] trace;
    public static ArrayList<ArrayList<node>> arr1;
    public static ArrayList<Integer> traceList;
    public static boolean[][] block;

    public static class node implements Comparable<node> {
        int goal, value;

        public node(int goal, int value) {
            this.goal = goal;
            this.value = value;
        }

        @Override
        public int compareTo(node o) {
            return value - o.value;
        }
    }

    public static void findShortestRoad(int end) {
        traceList.add(end);
        int tmp = end;

        while (trace[tmp] != 0) {
            traceList.add(trace[tmp]);
            tmp = trace[tmp];
        }
    }

    public static int dijkstra(int s, int e) {
        PriorityQueue<node> pq = new PriorityQueue<>();
        int[] dist = new int[N + 1];
        pq.offer(new node(s, 0));
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[s] = 0;

        while (!pq.isEmpty()) {
            node now = pq.poll();

            if (dist[now.goal] < now.value) continue;

            for (node tmp : arr1.get(now.goal)) {
                if (block[now.goal][tmp.goal]) continue;

                if (dist[tmp.goal] > dist[now.goal] + tmp.value) {
                    dist[tmp.goal] = dist[now.goal] + tmp.value;
                    trace[tmp.goal] = now.goal;
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
        traceList = new ArrayList<>();
        trace = new int[N + 1];
        block = new boolean[N + 1][N + 1];

        for (int i = 0; i <= N; i++) {
            arr1.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            arr1.get(s).add(new node(e, v));
            arr1.get(e).add(new node(s, v));
        }

        int minDist = dijkstra(1, N);
        int maxDist = 0;
        findShortestRoad(N);
        //System.out.println(traceList);

        for (int i = 1; i < traceList.size(); i++) {
            int s = traceList.get(i - 1);
            int e = traceList.get(i);
            block[s][e] = block[e][s] = true;
            maxDist = Math.max(maxDist, dijkstra(1, N));
            block[s][e] = block[e][s] = false;
        }

        System.out.println(maxDist == Integer.MAX_VALUE ? -1 : maxDist - minDist);
    }
}