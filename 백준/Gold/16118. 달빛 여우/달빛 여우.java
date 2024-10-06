import java.io.*;
import java.util.*;

public class Main {
    public static int N, M;
    public static List<List<Node>> arr1;

    public static class Node implements Comparable<Node> {
        int goal, isRecovered;
        double val;

        public Node(int goal, double val, int isRecovered) {
            this.goal = goal;
            this.val = val;
            this.isRecovered = isRecovered;
        }

        @Override
        public int compareTo(Node o) {
            return Double.compare(val, o.val);
        }
    }

    public static void dijkstra(double[][] dist, boolean isFox) {

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(0, 0, 1));

        while (!pq.isEmpty()) {
            Node now = pq.poll();
            int update = now.isRecovered;

            if (!isFox) {
                update = update == 1 ? 0 : 1;
            }

            if (dist[now.isRecovered][now.goal] < now.val) {
                continue;
            }

            for (Node tmp : arr1.get(now.goal)) {
                if (isFox) {
                    if (dist[update][tmp.goal] > now.val + tmp.val) {
                        dist[update][tmp.goal] = now.val + tmp.val;
                        pq.offer(new Node(tmp.goal, dist[update][tmp.goal], update));
                    }
                    continue;
                }

                if (now.isRecovered == 1) {
                    if (dist[update][tmp.goal] > now.val + tmp.val / 2) {
                        dist[update][tmp.goal] = now.val + tmp.val / 2;
                        pq.offer(new Node(tmp.goal, dist[update][tmp.goal], update));
                    }
                } else {
                    if (dist[update][tmp.goal] > now.val + tmp.val * 2) {
                        dist[update][tmp.goal] = now.val + tmp.val * 2;
                        pq.offer(new Node(tmp.goal, dist[update][tmp.goal], update));
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int answer = 0;

        arr1 = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            arr1.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken()) - 1;
            int e = Integer.parseInt(st.nextToken()) - 1;
            double v = Double.parseDouble(st.nextToken());

            arr1.get(s).add(new Node(e, v, 1));
            arr1.get(e).add(new Node(s, v, 1));
        }

        double[][] foxDist = new double[2][N];
        Arrays.fill(foxDist[0], Double.MAX_VALUE);
        Arrays.fill(foxDist[1], Double.MAX_VALUE);
        foxDist[0][0] = 0;
        foxDist[1][0] = 0;
        dijkstra(foxDist, true); // 여우,

        double[][] wolfDist = new double[2][N];
        Arrays.fill(wolfDist[0], Double.MAX_VALUE);
        Arrays.fill(wolfDist[1], Double.MAX_VALUE);
        
        dijkstra(wolfDist, false); // 늑대

        for (int i = 1; i < N; i++) {
            if (foxDist[1][i] < Math.min(wolfDist[0][i], wolfDist[1][i])) {
                answer++;
            }
        }

        
        System.out.println(answer);
    }
}