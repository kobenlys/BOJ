import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int[] dist;
    public static ArrayList<node>[] arr1;
    public static int N, INF = 640000001;

    // 목표와, 현재노드에서 목표노드로 가는 길이 담는 객체
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

    public static int dijkstra(int start, int flag) { // 구현
        PriorityQueue<node> pq = new PriorityQueue<>();
        Arrays.fill(dist, INF);
        dist[start] = 0;
        pq.offer(new node(start, 0));

        while (!pq.isEmpty()) { // 우선순위 큐로 구현한 다익스트라 알고리즘
            node now = pq.poll();

            if (dist[now.goal] < now.len) {
                continue;
            }
            // 현재자리 기준 목표노드로 갈 수 있는 간선 탐색
            for (int i = 0; i < arr1[now.goal].size(); i++) {
                node temp = arr1[now.goal].get(i);
                if (dist[temp.goal] > dist[now.goal] + temp.len) {
                    dist[temp.goal] = dist[now.goal] + temp.len;
                    pq.offer(new node(temp.goal, dist[temp.goal]));
                }
            }
        }
        return dist[flag];
    }

    public static void main(String[] args) throws IOException { // 값 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        // 2차원 동적배열로 인접리스트 생성
        arr1 = new ArrayList[N];
        dist = new int[N];

        for (int i = 0; i < N; i++) {
            // arr1에 arraylist 동적배열 넣기
            arr1[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int s = Integer.parseInt(st.nextToken()) - 1;
            int e = Integer.parseInt(st.nextToken()) - 1;
            int l = Integer.parseInt(st.nextToken());
            // 본문 중 "방향성이 없는 그래프" 즉 양방향성 그래프이다.
            arr1[s].add(new node(e, l));
            arr1[e].add(new node(s, l));
        }

        st = new StringTokenizer(br.readLine(), " ");
        int s1 = Integer.parseInt(st.nextToken()) - 1; // step1
        int s2 = Integer.parseInt(st.nextToken()) - 1; // step2


        // "양방향" 그래프이기 때문에
        // 1 -> s1 ->s2 -> N , 1 -> s2 -> s1 -> N 두 경우를 구해야함
        // 문제에서 지정한 처음과 끝 (1,N)은 고정한다.
        int res1 = dijkstra(0, s1) + dijkstra(s1, s2) + dijkstra(s2, N - 1);
        int res2 = dijkstra(0, s2) + dijkstra(s2, s1) + dijkstra(s1, N - 1);
        int ans = Math.min(res1, res2);

        // 두 경우 모두 이동간 갈수없는곳(INF)가 있다면
        // 위에서 더해줬기 때문에 ans는 INF보다 같거나 크다
        System.out.println(ans >= INF ? -1 : ans);
    }
}