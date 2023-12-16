import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        Deque<Integer> dq = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();

        int tcase = Integer.parseInt(br.readLine());
        int num = 0;

        for (int i = 0; i < tcase; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            String input = st.nextToken();
            if (input.equals("push_front")) {
                num = Integer.parseInt(st.nextToken());
                dq.offerFirst(num);
            } else if (input.equals("push_back")) {
                num = Integer.parseInt(st.nextToken());
                dq.offerLast(num);
            } else if (input.equals("pop_front")) {
                if (dq.isEmpty()) {
                    sb.append(-1).append("\n");
                } else {
                    sb.append(dq.pollFirst()).append("\n");
                }
            } else if (input.equals("pop_back")) {
                if (dq.isEmpty()) {
                    sb.append(-1).append("\n");
                } else {
                    sb.append(dq.pollLast()).append("\n");
                }
            } else if (input.equals("size")) {
                sb.append(dq.size()).append("\n");
            } else if (input.equals("empty")) {
                sb.append(dq.isEmpty() ? 1 : 0).append("\n");
            } else if (input.equals("front")) {
                if (dq.isEmpty()) {
                    sb.append(-1).append("\n");
                } else {
                    sb.append(dq.peekFirst()).append("\n");
                }
            } else if (input.equals("back")) {
                if (dq.isEmpty()) {
                    sb.append(-1).append("\n");
                } else {
                    sb.append(dq.peekLast()).append("\n");
                }
            }
        }
        System.out.print(sb);
    }
}