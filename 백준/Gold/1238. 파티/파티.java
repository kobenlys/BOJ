import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static ArrayList<node>[] arr1; // 전역 변수 설정
    public static int[] dist;
    public static int INF = 100000001;

    // 현재 노드기준 목표 노드와 걸리는 시간 담는 객체
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

        PriorityQueue<node> pq = new PriorityQueue<>(); // 우선순위 큐 사용
        Arrays.fill(dist, INF);
        dist[start] = 0;
        pq.offer(new node(start, 0));

        while (!pq.isEmpty()) {
            node now = pq.poll();

            if (dist[now.goal] < now.len) {
                continue;
            }
            // now.goal 자리에서 갈 수 있는 모든 곳 탐색
            for (int i = 0; i < arr1[now.goal].size(); i++) {
                node tmp = arr1[now.goal].get(i);
                // 기존 시간보다 새로운 경로의 시간이 더 적다면 입력
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
        int X = Integer.parseInt(st.nextToken()) - 1;

        // 인접리스트로 간선 구현
        arr1 = new ArrayList[N];
        dist = new int[N];
        int max = 0;

        for (int i = 0; i < N; i++) {
            arr1[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int s = Integer.parseInt(st.nextToken()) - 1;
            int e = Integer.parseInt(st.nextToken()) - 1;
            int l = Integer.parseInt(st.nextToken());
            // 단방향 간선이다.
            arr1[s].add(new node(e, l));
        }

        for (int i = 0; i < N; i++) {
            // 파티에 가는 경우 + 집에 돌아가는 경우
            int ans = dijkstra(i, X) + dijkstra(X, i);
            if (ans < INF) {
                // 최댓값 구하기
                max = Math.max(max, ans);
            }
        }
        System.out.println(max);
    }
}