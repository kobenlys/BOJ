import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
    public static PriorityQueue<node> box = new PriorityQueue<node>();

    public static class node implements Comparable<node> {
        int dl, ramen;

        public node(int dl, int ramen) {
            this.dl = dl;
            this.ramen = ramen;
        }

        @Override
        public int compareTo(node o) {
            return o.dl - dl;
        }
    }

    public static void main(String[] args) throws IOException { //조건 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        long cnt = 0;
        
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            box.offer(new node(a, b));
        }

        int max = box.peek().dl;

        while (max !=0) {

            while (!box.isEmpty() &&max == box.peek().dl) {
                pq.offer(box.poll().ramen);
            }
            if (!pq.isEmpty()) {
                cnt += pq.poll();
            }
            max--;
        }
        System.out.print(cnt);
    }
}