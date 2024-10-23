import java.io.*;
import java.util.*;

public class Main {
    public static int N, M, K;
    public static ArrayList<ArrayList<node>> arr1 = new ArrayList<>();
    public static long[][] dist;

    public static class node implements Comparable<node> {
        int goal, cnt;
        long val;

        public node(int goal, int cnt, long val) {
            this.goal = goal;
            this.cnt = cnt;
            this.val = val;
        }

        @Override
        public int compareTo(node o) {
            return (int) (val - o.val);
        }
    }

    public static void dijkstra() { // 다익스트라 알고리즘
        // 초기세팅
        PriorityQueue<node> pq = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            Arrays.fill(dist[i], Long.MAX_VALUE);
        }
        dist[0][0] = 0;
        pq.offer(new node(0, 0, 0));


        while (!pq.isEmpty()) {

            node now = pq.poll();
            // 시간초과 방지 (이전시간이, 현재 경로에서 걸리는 시간보다 적은경우 탐색필요 X)
            if (now.val > dist[now.goal][now.cnt]) {
                continue;
            }

            for (int i = 0; i < arr1.get(now.goal).size(); i++) {
                node tmp = arr1.get(now.goal).get(i);
                // 포장 안하는 경우 -> now.cnt는 유지하고 최솟값 입력
                if (dist[tmp.goal][now.cnt] > dist[now.goal][now.cnt] + tmp.val) {
                    dist[tmp.goal][now.cnt] = dist[now.goal][now.cnt] + tmp.val;
                    pq.offer(new node(tmp.goal, now.cnt, dist[tmp.goal][now.cnt]));
                }
                // 포장 하는 경우 -> 포장가능하다면 -> now.cnt+1 하고 tmp.val를 더하지 않는다 (포장했으니깐) 
                if (now.cnt < K && dist[tmp.goal][now.cnt + 1] > dist[now.goal][now.cnt]) {
                    dist[tmp.goal][now.cnt + 1] = dist[now.goal][now.cnt];
                    pq.offer(new node(tmp.goal, now.cnt + 1, dist[tmp.goal][now.cnt + 1]));
                }
            }
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        dist = new long[N][K + 1]; // 포장 할 수 있는 횟수 K도 포함해서 2차원 배열 만든다.

        for (int i = 0; i < N; i++) {
            arr1.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int s = Integer.parseInt(st.nextToken()) - 1;
            int e = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken());
            arr1.get(s).add(new node(e, 0, v));
            arr1.get(e).add(new node(s, 0, v));
        }

        // 다익스트라 알고리즘 호출.
        dijkstra();

        // 답 출력.
        long min = Long.MAX_VALUE;
        for (int i = 0; i <= K; i++) {
            min = Math.min(min, dist[N - 1][i]);
        }
        System.out.println(min);
    }
}