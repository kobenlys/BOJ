import java.io.*;
import java.util.*;

public class Main {

    public static class node implements Comparable<node> {
        int id, w, orderN;

        public node(int id, int w, int orderN) {
            this.id = id;
            this.w = w;
            this.orderN = orderN;
        }

        @Override
        public int compareTo(node o) {
            if (w == o.w) {
                return o.orderN - orderN;
            } else {
                return w - o.w;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PriorityQueue<node> pq = new PriorityQueue<node>();
        PriorityQueue<Integer> cashTable = new PriorityQueue<>();
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        node[] arr1 = new node[N];
        int time = 0;
        int cnt = 0;
        long ans = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int id = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            arr1[i] = new node(id, w, 0);
            if (i < K) {
                cashTable.add(i+1);
            }
        }


        int idx = 0;
        while (idx < N) {

            int id = arr1[idx].id;
            int w = arr1[idx].w;

            if (pq.size() < K) {
                w += time;
                idx++;
                pq.offer(new node(id, w, cashTable.poll()));
                continue;
            } else {
                time++;
            }


            while (!pq.isEmpty() && pq.peek().w == time) {
                cnt++;
                cashTable.add(pq.peek().orderN);
                ans += (long) cnt * pq.poll().id;
            }
        }

        // 123 21 34
        // 21 56 21(10) -> 123
        // 723(5) 45(7) 34(9) -> 123, 21, 56
        // 45(2) 34(4) 55(7) -> 123, 21, 56, 723
        // 34(2) 13(5), 55(5) -> 123, 21, 56, 723, 45
        // 13(3), 55(3), 910(10) -> 123, 21, 56, 723, 45, 34
        // 73(3), 910(10) -> 123, 21, 56, 723, 45, 34, 13, 55

        while (!pq.isEmpty()) {
            cnt++;
            //System.out.println(pq.peek().id +" "+ pq.peek().orderN);
            ans += (long) cnt * pq.poll().id;
        }
        System.out.print(ans);
    }
}