import java.io.*;
import java.util.*;

public class Main {
    public static int N, M, start, end;
    public static ArrayList<ArrayList<node>> arr1;

    public static class node implements Comparable<node> {
        int goal;
        long val;

        public node(int goal, long val) {
            this.goal = goal;
            this.val = val;
        }

        @Override
        public int compareTo(node o) {
            return (int) (val - o.val);
        }
    }

    public static boolean dijkstra(int limit) {
        PriorityQueue<node> pq = new PriorityQueue<>();
        long[] dist = new long[N];

        Arrays.fill(dist, Long.MAX_VALUE);
        dist[start] = 0;

        pq.offer(new node(start, 0));

        while (!pq.isEmpty()) {

            node now = pq.poll();

            if (dist[now.goal] < now.val) continue;

            for (int i = 0; i < arr1.get(now.goal).size(); i++) {

                node tmp = arr1.get(now.goal).get(i);

                if (dist[tmp.goal] > dist[now.goal] + tmp.goal) {
                    if (tmp.val < limit) continue;
                    dist[tmp.goal] = dist[now.goal] + tmp.goal;
                    pq.offer(new node(tmp.goal, dist[tmp.goal]));
                }
            }
        }

        return dist[end] == Long.MAX_VALUE;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine(), " ");
        start = Integer.parseInt(st.nextToken()) - 1;
        end = Integer.parseInt(st.nextToken()) - 1;

        int left = 0, right = 0;

        arr1 = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            arr1.add(new ArrayList<>());
        }

        while (M-- > 0) {
            st = new StringTokenizer(br.readLine(), " ");
            int s = Integer.parseInt(st.nextToken()) - 1;
            int e = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken());
            arr1.get(s).add(new node(e, v));
            arr1.get(e).add(new node(s, v));
            right = Math.max(right, v);
        }



        int answer = 0;
        while (left <= right) {

            int mid = (left + right) / 2;

            if (dijkstra(mid)) {
                right = mid - 1;
            } else {
                answer = mid;
                left = mid + 1;
            }
        }
        System.out.println(answer);
    }
}