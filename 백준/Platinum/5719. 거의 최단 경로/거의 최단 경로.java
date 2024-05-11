import java.io.*;
import java.util.*;

public class Main {
    public static int N, M;
    public static ArrayList<ArrayList<node>> arr1;
    public static ArrayList<ArrayList<Integer>> trace;
    public static boolean[][] vi;
    public static int[] dist;


    public static class node implements Comparable<node> {
        int goal, len;

        public node(int goal, int len) {
            this.goal = goal;
            this.len = len;
        }

        @Override
        public int compareTo(node o) {
            return len - o.len;
        }
    }

    public static void removePath(int start, int end) {
        if (start == end) return;

        for (int e : trace.get(end)) {
            if (!vi[e][end]) {
                vi[e][end] = true;
                removePath(start, e);
            }
        }
    }


    public static int dijkstra(int start, int end) {
        PriorityQueue<node> pq = new PriorityQueue<>();
        dist = new int[N];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;
        pq.offer(new node(start, 0));

        while (!pq.isEmpty()) {

            node now = pq.poll();

            if (dist[now.goal] < now.len) {
                continue;
            }

            for (int i = 0; i < arr1.get(now.goal).size(); i++) {
                node tmp = arr1.get(now.goal).get(i);

                if (vi[now.goal][tmp.goal]) continue;

                if (dist[tmp.goal] > dist[now.goal] + tmp.len) {
                    
                    trace.get(tmp.goal).clear();
                    trace.get(tmp.goal).add(now.goal);
                    dist[tmp.goal] = dist[now.goal] + tmp.len;
                    pq.offer(new node(tmp.goal, dist[tmp.goal]));
                    
                } else if (dist[tmp.goal] == dist[now.goal] + tmp.len) {
                    trace.get(tmp.goal).add(now.goal);
                }
            }
        }
        return dist[end];
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        while (true) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            if (N == M && N == 0) break;

            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int answer = 0;

            trace = new ArrayList<>();
            arr1 = new ArrayList<>();
            vi = new boolean[N][N];

            for (int i = 0; i < N; i++) {
                arr1.add(new ArrayList<>());
                trace.add(new ArrayList<>());
            }


            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                arr1.get(s).add(new node(e, v));
            }

            answer = dijkstra(start, end);
            removePath(start, end);
            answer = dijkstra(start, end);

            sb.append(answer == Integer.MAX_VALUE ? -1 : answer).append("\n");
        }
        System.out.print(sb);
    }
}