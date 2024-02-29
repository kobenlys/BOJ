import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

    public static void dijkstra(int start) { // 다익스트라 알고리즘.
        PriorityQueue<node> pq = new PriorityQueue<>();
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;
        pq.offer(new node(start, 0));

        while (!pq.isEmpty()) {
            node now = pq.poll();

            if (dist[now.goal] < now.val) {
                continue;
            }

            for (int i = 0; i < arr1.get(now.goal).size(); i++) {
                node tmp = arr1.get(now.goal).get(i);
                if (dist[tmp.goal] > dist[now.goal] + tmp.val) {
                    dist[tmp.goal] = dist[now.goal] + tmp.val;
                    pq.offer(new node(tmp.goal, dist[tmp.goal]));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException { //조건 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());

        arr1 = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            arr1.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int s = Integer.parseInt(st.nextToken()) - 1;
            int e = Integer.parseInt(st.nextToken()) - 1;
            arr1.get(s).add(new node(e, 1));
        }
        
        dist = new int[N];
        dijkstra(X - 1); // 다익스트라 함수 호출

        // 출력
        for (int i = 0; i < N; i++) {
            if (dist[i] == K) {
                sb.append(i + 1).append("\n");
            }
        }
        // sb의 길이가 0 이라면 = 답이 없다면 -1 출력한다
        System.out.print(sb.length() == 0 ? -1 : sb);
    }
}