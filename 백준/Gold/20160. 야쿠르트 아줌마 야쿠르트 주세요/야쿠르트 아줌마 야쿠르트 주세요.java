import java.io.*;
import java.util.*;

public class Main {
    public static int N, M;
    public static boolean check;
    public static long[] myDist;
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
            return Long.compare(val, o.val);
        }
    }

    public static long dijkstra(int s, int e, long startValue) {
        PriorityQueue<node> pq = new PriorityQueue<>();
        long[] dist = new long[N + 1];

        Arrays.fill(dist, Long.MAX_VALUE);
        dist[s] = startValue;
        pq.offer(new node(s, 0));

        while (!pq.isEmpty()) {

            node now = pq.poll();
            if (dist[now.goal] < now.val) continue;


            for (node tmp : arr1.get(now.goal)) {

                if (dist[tmp.goal] > dist[now.goal] + tmp.val) {
                    dist[tmp.goal] = dist[now.goal] + tmp.val;
                    pq.offer(new node(tmp.goal, dist[tmp.goal]));
                }
            }
        }

        if (check) {
            myDist = new long[N + 1];
            for (int i = 0; i <= N; i++) {
                myDist[i] = dist[i];
            }
        }
        return dist[e];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int answer = Integer.MAX_VALUE;
        check = true;

        int[] townList = new int[10];
        arr1 = new ArrayList<>();

        for (int i = 0; i <= N; i++) {
            arr1.add(new ArrayList<>());
        }

        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            arr1.get(s).add(new node(e, v));
            arr1.get(e).add(new node(s, v));

        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 10; i++) {
            townList[i] = Integer.parseInt(st.nextToken());
        }

        int start = Integer.parseInt(br.readLine());
        if (start == townList[0]) {
            answer = start;
        }

        dijkstra(start, 0, 0);
        check = false;

        long tmp = 0;
        int s = townList[0];
        for (int i = 1; i < 10; i++) {
            int e = townList[i];
            long res = dijkstra(s, e, tmp);

            if (res != Long.MAX_VALUE) {
                tmp = res;
                s = e;
            }else{
                continue;
            }

            if (tmp >= myDist[e]) {
                answer = Math.min(answer, e);
            }
        }

        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }
}