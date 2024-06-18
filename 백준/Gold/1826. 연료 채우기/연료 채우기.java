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
        PriorityQueue<Integer> tmp = new PriorityQueue<>(Collections.reverseOrder());
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            pq.offer(new node(a, b));
        }

        st = new StringTokenizer(br.readLine());
        int L = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());
        int answer = 0;

        while (!pq.isEmpty()) {
            if (P >= L) {
                break;
            }

            while (!pq.isEmpty() && P >= pq.peek().idx) {
                tmp.add(pq.poll().fuel);
            }

            if (!tmp.isEmpty()) {
                answer++;
                P += tmp.poll();
            } else {
                break;
            }
        }

        System.out.println(P >= L ? answer : -1);
    }
}