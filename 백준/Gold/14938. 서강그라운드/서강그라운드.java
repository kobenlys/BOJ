import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int N, M;
    public static int[] dist;
    public static int[] res;
    public static ArrayList<node>[] arr1;

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

    public static int dijkstra(int start) { // 다익스트라 알고리즘 구현
        PriorityQueue<node> pq = new PriorityQueue<>();
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;
        pq.offer(new node(start, 0));

        while (!pq.isEmpty()) {
            node now = pq.poll();

            if (dist[now.goal] < now.len) {
                continue;
            }

            for (int i = 0; i < arr1[now.goal].size(); i++) {
                node tmp = arr1[now.goal].get(i);
                // 저장된 길이보다 작은 길이의 경로가 있다면 초기화
                if (dist[tmp.goal] > dist[now.goal] + tmp.len) {
                    dist[tmp.goal] = dist[now.goal] + tmp.len;
                    pq.offer(new node(tmp.goal, dist[tmp.goal]));
                }
            }
        }
        //  탐색범위 이하인 최단 거리가 있다면 노드에 저장된 아이템 수 sum에 더해준다.
        int sum = 0;
        for (int i = 0; i < N; i++) {
            if (dist[i] <= M) {
                sum += res[i];
            }
        }

        return sum;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken()); // search range
        int R = Integer.parseInt(st.nextToken());
        int max = 0;

        res = new int[N];
        dist = new int[N];
        arr1 = new ArrayList[N];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            // 주어진 노드가 가지는 아이템 수 저장
            res[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N; i++) {
            arr1[i] = new ArrayList<>();
        }

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int s = Integer.parseInt(st.nextToken()) - 1;
            int e = Integer.parseInt(st.nextToken()) - 1;
            int l = Integer.parseInt(st.nextToken());

            arr1[s].add(new node(e, l));
            arr1[e].add(new node(s, l));
        }
        // i 번 노드가 시작 노드일 경우로 함수 호출
        for (int i = 0; i < N; i++) {
            // 모든 경우의 아이템 수 중 가장 큰 값 도출
            max = Math.max(max, dijkstra(i));
        }

        System.out.println(max);
    }
}