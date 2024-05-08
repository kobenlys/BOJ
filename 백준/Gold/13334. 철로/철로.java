import java.io.*;
import java.util.*;

public class Main {

    public static class node implements Comparable<node> {
        int left, right;

        public node(int left, int right) {
            this.left = left;
            this.right = right;
        }


        @Override
        public int compareTo(node o) {
            return right - o.right;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        ArrayList<node> arr1 = new ArrayList<node>();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int max = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            if (s > e) {
                int tmp = s;
                s = e;
                e = tmp;
            }
            arr1.add(new node(s, e));
        }

        Collections.sort(arr1);
        int len = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {

            node now = arr1.get(i);
            pq.offer(now.left);

            while (!pq.isEmpty() && pq.peek() < now.right - len) {
                pq.poll();
            }
            max = Math.max(max, pq.size());
        }
        System.out.println(max);
    }
}