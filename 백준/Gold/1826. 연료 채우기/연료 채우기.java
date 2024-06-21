import java.io.*;
import java.util.*;

public class Main {
    public static class node implements Comparable<node> {
        int idx, fuel;

        public node(int idx, int fuel) {
            this.idx = idx;
            this.fuel = fuel;
        }

        @Override
        public int compareTo(node o) {
            return idx - o.idx;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PriorityQueue<node> pq = new PriorityQueue<>();
        PriorityQueue<Integer> tmp = new PriorityQueue<>(Comparator.reverseOrder());
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int idx = Integer.parseInt(st.nextToken());
            int fuel = Integer.parseInt(st.nextToken());
            pq.offer(new node(idx, fuel));
        }

        st = new StringTokenizer(br.readLine());
        int L = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());
        int answer = 0;

        while (!pq.isEmpty() && P < L) {


            while (!pq.isEmpty() && P >= pq.peek().idx) {
                tmp.add(pq.poll().fuel);
            }

            if (tmp.isEmpty()) break;

            answer++;
            P += tmp.poll();
        }
        System.out.println(P >= L ? answer : -1);
    }
}