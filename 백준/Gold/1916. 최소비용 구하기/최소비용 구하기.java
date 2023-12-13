import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static long[] dist;
    public static ArrayList<node>[] arr1;

    // 목표와 비용을 담는 객체
    public static class node implements Comparable<node> {
        int goal;
        long cost;
        public node(int goal, long cost) {
            this.goal = goal;
            this.cost = cost;
        }
        @Override
        public int compareTo(node o) {
            return Long.compare(cost, o.cost);
        }
    }

    public static void dijkstra(int start) { // 다익스트라 알고리즘
        PriorityQueue<node> pq = new PriorityQueue<>(); // 우선순위 큐 사용한다.
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[start] = 0; // 시작 자리 초기화
        pq.offer(new node(start, 0));

        while (!pq.isEmpty()) {
            node nd = pq.poll();

            if (dist[nd.goal] < nd.cost) {
                continue;
            }
            // 해당 노드에서 갈 수 있는 노드 탐색
            for (int i = 0; i < arr1[nd.goal].size(); i++) {
                node tmp = arr1[nd.goal].get(i);
                // 목표노드 비용 과 현재자리노드비용 + 현재자리에서  목표로 가는 비용
                if (dist[tmp.goal] > dist[nd.goal] + tmp.cost) {
                    dist[tmp.goal] = dist[nd.goal] + tmp.cost;
                    pq.offer(new node(tmp.goal, dist[tmp.goal]));
                }
            }
        }
    }


    public static void main(String[] args) throws IOException { // 값 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        dist = new long[N];
        arr1 = new ArrayList[N]; // 2차원 동적배열 생성

        for (int i = 0; i < N; i++) {
            // 한 자리 마다 동적배열 설정
            arr1[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int s = Integer.parseInt(st.nextToken()) - 1;
            int e = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken());
            arr1[s].add(new node(e, c));
        }
        st = new StringTokenizer(br.readLine(), " ");
        int start = Integer.parseInt(st.nextToken()) - 1; // 시작
        int end = Integer.parseInt(st.nextToken()) - 1; // 목표

        // 다익스트라 알고리즘 실행
        dijkstra(start);
        System.out.println(dist[end]);
    }
}