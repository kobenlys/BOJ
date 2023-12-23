import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int[] dist, vi;
    public static ArrayList<node>[] arr1;

    public static class node implements Comparable<node> {
        int goal, cost;
        public node(int goal, int cost) {
            this.goal = goal;
            this.cost = cost;
        }
        @Override
        public int compareTo(node o) {
            return cost - o.cost;
        }
    }

    public static long dijkstra(int start, int end) {
        PriorityQueue<node> pq = new PriorityQueue<>();
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;
        pq.offer(new node(start, 0));

        while (!pq.isEmpty()) {
            node now = pq.poll();

            if (dist[now.goal] < now.cost) {
                continue;
            }

            for (int i = 0; i < arr1[now.goal].size(); i++) {
                node tmp = arr1[now.goal].get(i);

                if (dist[tmp.goal] > dist[now.goal] + tmp.cost) {
                    dist[tmp.goal] = dist[now.goal] + tmp.cost;
                    vi[tmp.goal] = now.goal;
                    pq.offer(new node(tmp.goal, dist[tmp.goal]));
                }
            }
        }
        return dist[end];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        arr1 = new ArrayList[N+1];
        dist = new int[N+1];
        vi = new int[N+1];

        for (int i = 1; i <= N; i++) {
            arr1[i] = new ArrayList<>();
        }


        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            arr1[s].add(new node(e, c));
        }
        st = new StringTokenizer(br.readLine(), " ");
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        sb.append(dijkstra(start, end)).append("\n");

        Stack<Integer> stk = new Stack<>();
        int cnt = 0;

        while (end != 0) {
            stk.push(end);
            end = vi[end];
            cnt++;
        }
        sb.append(cnt).append("\n");
        while (!stk.isEmpty()) {
            sb.append(stk.pop()).append(" ");
        }

        System.out.println(sb);
    }
}