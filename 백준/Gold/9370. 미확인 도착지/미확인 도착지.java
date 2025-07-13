import java.io.*;
import java.util.*;

public class Main {

    public static int N, M, T, S, G, H;
    private static List<List<Node>> list;

    public static class Node implements Comparable<Node> {

        int goal, value;

        public Node(int goal, int value) {
            this.goal = goal;
            this.value = value;
        }

        @Override
        public int compareTo(Node o) {
            return value - o.value;
        }
    }

    public static int[] dijkstra() {
        int[] dist = new int[N + 1];
        PriorityQueue<Node> pq = new PriorityQueue<>();
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[S] = 0;
        pq.offer(new Node(S, 0));

        while (!pq.isEmpty()) {
            Node now = pq.poll();

            if (dist[now.goal] < now.value) {
                continue;
            }

            for (Node tmp : list.get(now.goal)) {
                if (dist[tmp.goal] > dist[now.goal] + tmp.value) {
                    dist[tmp.goal] = dist[now.goal] + tmp.value;
                    pq.offer(new Node(tmp.goal, dist[tmp.goal]));
                }
            }
        }
        return dist;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int tc = Integer.parseInt(br.readLine());

        while (tc-- > 0) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            T = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            S = Integer.parseInt(st.nextToken());
            G = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());

            if (G > H) {
                int temp = G;
                G = H;
                H = temp;
            }

            list = new ArrayList<>();
            for (int i = 0; i <= N; i++) {
                list.add(new ArrayList<>());
            }

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());

                if (G == s && H == e) {
                    list.get(s).add(new Node(e, v * 2 - 1));
                    list.get(e).add(new Node(s, v * 2 - 1));
                    continue;
                }

                list.get(s).add(new Node(e, v * 2));
                list.get(e).add(new Node(s, v * 2));
            }

            int[] dist = dijkstra();
            List<Integer> answer = new ArrayList<>();

            for (int i = 0; i < T; i++) {
                int node = Integer.parseInt(br.readLine());
                int nodeDist = dist[node];
                
                if(nodeDist == Integer.MAX_VALUE) continue;
                if (nodeDist % 2 == 1) {
                    answer.add(node);
                }
            }

            Collections.sort(answer);

            for (int e : answer) {
                sb.append(e).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}