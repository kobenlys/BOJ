import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static ArrayList<ArrayList<node>> arr1;
    public static int[] dist;

    public static class node implements Comparable<node> {
        int goal, cow;

        public node(int goal, int cow) {
            this.goal = goal;
            this.cow = cow;
        }

        @Override
        public int compareTo(node o) {
            return cow - o.cow;
        }
    }

    public static int dijkstra(int start, int end) {
        PriorityQueue<node> pq = new PriorityQueue<>();
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;
        pq.offer(new node(start, 0));

        while (!pq.isEmpty()) {

            node nd = pq.poll();

            if (dist[nd.goal] < nd.cow) {
                continue;
            }

            for (int i = 0; i < arr1.get(nd.goal).size(); i++) {

                node tmp = arr1.get(nd.goal).get(i);

                if (dist[tmp.goal] > dist[nd.goal] + tmp.cow) {
                    dist[tmp.goal] = dist[nd.goal] + tmp.cow;
                    pq.offer(new node(tmp.goal, dist[tmp.goal]));
                }
            }
        }
        return dist[end];
    }

    public static void main(String[] args) throws IOException { //조건 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        arr1 = new ArrayList<>();
        dist = new int[N];

        for (int i = 0; i < N; i++) {
            arr1.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int s = Integer.parseInt(st.nextToken()) - 1;
            int e = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken());
            arr1.get(s).add(new node(e, c));
            arr1.get(e).add(new node(s, c));
        }

        System.out.print(dijkstra(0, N - 1));
    }
}
