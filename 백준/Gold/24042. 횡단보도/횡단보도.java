import java.io.*;
import java.util.*;

public class Main {
    public static List<List<Node>> list;

    public static class Node implements Comparable<Node> {
        int goal;
        long val;

        public Node(int goal, long val) {
            this.goal = goal;
            this.val = val;
        }

        @Override
        public int compareTo(Node o) {
            return Long.compare(val, o.val);
        }
    }

    public static long djikstra(long period, int size) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        long[] dist = new long[size + 1];
        Arrays.fill(dist, Long.MAX_VALUE);
        pq.offer(new Node(1, 0));
        dist[1] = 0;
        
        while (!pq.isEmpty()) {
            Node now = pq.poll();
            if (dist[now.goal] < now.val) {
                continue;
            }
            
            if(now.goal == size) break;

            for (Node tmp : list.get(now.goal)) {
                long nextDist;

                if (now.val < tmp.val) {
                    nextDist = tmp.val;
                }else{
                    nextDist = (long) Math.ceil((double) ( now.val - tmp.val) / period) * period + tmp.val;
                }

                if (dist[tmp.goal] > nextDist) {
                    dist[tmp.goal] = nextDist;
                    pq.offer(new Node(tmp.goal, dist[tmp.goal]));
                }
            }
        }

        return dist[size];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken()); // 횡단 보도 주기

        list = new ArrayList<>();

        for (int i = 0; i <= N; i++) {
            list.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            list.get(s).add(new Node(e, i + 1));
            list.get(e).add(new Node(s, i + 1));
        }

        long answer = djikstra(M, N);
        System.out.println(answer);
    }
}