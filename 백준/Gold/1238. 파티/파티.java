import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static ArrayList<node>[] arr1; // 전역 변수 설정
    public static int[] dist;
    public static int INF = 100000001;

    public static class node implements Comparable<node>{
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

    public static int dijkstra(int start, int end) {
        PriorityQueue<node> pq = new PriorityQueue<>();
        Arrays.fill(dist, INF);
        dist[start] = 0;
        pq.offer(new node(start, 0));

        while (!pq.isEmpty()) {
            node now = pq.poll();

            if (dist[now.goal] < now.len) {
                continue;
            }

            for (int i = 0; i < arr1[now.goal].size(); i++) {
                node tmp = arr1[now.goal].get(i);
                if (dist[tmp.goal] > dist[now.goal] + tmp.len) {
                    dist[tmp.goal] = dist[now.goal] + tmp.len;
                    pq.offer(new node(tmp.goal, dist[tmp.goal]));
                }
            }

        }
        return dist[end];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken())-1;

        arr1 = new ArrayList[N];
        dist = new int[N];
        int max = 0;

        for (int i = 0; i < N; i++) {
            arr1[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int s = Integer.parseInt(st.nextToken())-1;
            int e = Integer.parseInt(st.nextToken())-1;
            int l = Integer.parseInt(st.nextToken());
            arr1[s].add(new node(e, l));
        }

        for (int i = 0; i < N; i++) {
            int ans = dijkstra(i, X) + dijkstra(X, i);
            if (ans < INF) {
                max = Math.max(max, ans);
            }
        }
        System.out.println(max);
    }
}