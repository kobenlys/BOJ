import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static Deque<node> dq = new ArrayDeque<>();

    public static class node {
        int val, idx;
        public node(int val, int idx) {
            this.val = val;
            this.idx = idx;
        }
    }

    public static void main(String[] args) throws IOException { //조건 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            int now = Integer.parseInt(st.nextToken());

            while (!dq.isEmpty() && dq.peekLast().val >= now) {
                dq.pollLast();
            }
            dq.offerLast(new node(now, i));

            if (dq.peekFirst().idx == i - L) {
                dq.pollFirst();
            }
            sb.append(dq.peekFirst().val).append(" ");
        }
        System.out.println(sb);
    }
}