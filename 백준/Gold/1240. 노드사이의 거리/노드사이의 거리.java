import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int N, M, INF = Integer.MAX_VALUE;
    public static int[] dist;
    public static ArrayList<node>[] arr1;

    // 목표노드와 목표노드를 가기 위한 거리 를 담는 객체
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

    public static int dijkstra(int start, int end) { // 다익스트라 알고리즘
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
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        dist = new int[N];
        // 인접리스트를 사용한다.
        arr1 = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            arr1[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int s = Integer.parseInt(st.nextToken()) - 1;
            int e = Integer.parseInt(st.nextToken()) - 1;
            int len = Integer.parseInt(st.nextToken());
            // 양방성 그래프
            arr1[s].add(new node(e, len));
            arr1[e].add(new node(s, len));
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken()) - 1;
            int end = Integer.parseInt(st.nextToken()) - 1;
            // 입력받은 두 노드사이 거리 출력
            sb.append(dijkstra(start, end)).append("\n");
        }
        System.out.println(sb);
    }
}