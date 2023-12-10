import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int V, E, start;
    public static ArrayList<node>[] arr1;
    public static int[] dist;

    // 도착노드와, 가중치(길이) 저장을 위한 객체
    public static class node implements Comparable<node> {
        int goal, weight;

        public node(int goal, int weight) {
            this.goal = goal;
            this.weight = weight;
        }

        // 시간절약 위해 가중치 작은 것 부터 출력.
        @Override
        public int compareTo(node o) {
            return weight - o.weight;
        }
    }

    public static void dijkstra(int start) { // 우선순위 큐를 사용한 다익스트라 알고리즘
        PriorityQueue<node> pq = new PriorityQueue<node>();
        dist[start] = 0;
        pq.offer(new node(start, 0));

        while (!pq.isEmpty()) {
            node nd = pq.poll();

            // 최단거리 배열의 값이 현재 가중치보다 작으면 continue
            if (dist[nd.goal] < nd.weight) {
                continue;
            }

            for (int i = 0; i < arr1[nd.goal].size(); i++) {
                node tmp = arr1[nd.goal].get(i);
                int goal = tmp.goal;
                int weight = tmp.weight;
                // 목표자리 가중치 보다 지금 경로의 가중치가 더 작다면 교체한다.
                if (dist[goal] > dist[nd.goal] + weight) {
                    dist[goal] = dist[nd.goal] + weight;
                    pq.offer(new node(goal, dist[goal]));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder();

        V = Integer.parseInt(st.nextToken()); // 정점 수
        E = Integer.parseInt(st.nextToken()); // 간선 수
        start = Integer.parseInt(br.readLine()); // 시작 정점 번호

        arr1 = new ArrayList[V + 1];
        dist = new int[V + 1];

        // 초기 배열 설정 후 시작 지점 0으로 초기화
        Arrays.fill(dist, Integer.MAX_VALUE);

        for (int i = 0; i <= V; i++) {
            arr1[i] = new ArrayList<>();
        }
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            // start에서 end로 가는 w 가중치(길이)
            arr1[start].add(new node(end, w));
        }

        // 다익스트라 함수 호출, start = 시작노드임.
        dijkstra(start);

        for (int i = 1; i <= V; i++) {
            // 답 출력, 삼항 연산자 사용
            sb.append(dist[i] == Integer.MAX_VALUE ? "INF" : dist[i]).append("\n");
        }
        System.out.print(sb);
    }
}