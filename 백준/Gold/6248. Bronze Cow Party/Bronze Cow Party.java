import java.io.*;
import java.util.*;

public class Main {
    public static ArrayList<ArrayList<node>> arr1;
    public static int[] dist;

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

    public static void dijkstra(int start) {
        PriorityQueue<node> pq = new PriorityQueue<>();
        pq.offer(new node(start, 0));

        while (!pq.isEmpty()) {

            node now = pq.poll();
            if (dist[now.goal] < now.val) continue;

            for (int i = 0; i < arr1.get(now.goal).size(); i++) {
                node tmp = arr1.get(now.goal).get(i);
                // 최단 거리 업데이트.
                if (dist[tmp.goal] > dist[now.goal] + tmp.val) {
                    dist[tmp.goal] = dist[now.goal] + tmp.val;
                    pq.offer(new node(tmp.goal, dist[tmp.goal]));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken()) - 1;

        arr1 = new ArrayList<>();
        dist = new int[N];

        for (int i = 0; i < N; i++) {
            arr1.add(new ArrayList<>());
            dist[i] = Integer.MAX_VALUE;
        }

        dist[X] = 0;

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken()) - 1;
            int e = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken());
            arr1.get(s).add(new node(e, v)); // 양방향 간선처리.
            arr1.get(e).add(new node(s, v));
        }

        // 최단거리구하기.
        dijkstra(X);

        // 스트림. filter 사용해서 MAX_VALUE 값 제외한 최대값 구하기.
        int max = Arrays.stream(dist)
                .filter(o -> o != Integer.MAX_VALUE)
                .max().orElse(-1);

        System.out.println(max * 2);
    }
}