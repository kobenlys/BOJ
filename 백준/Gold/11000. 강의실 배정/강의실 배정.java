import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException { //조건 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PriorityQueue<Integer> startQ = new PriorityQueue<>();
        PriorityQueue<Integer> endQ = new PriorityQueue<>();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            startQ.offer(s);
            endQ.offer(e);
        }
        int cnt = 0;
        while (!startQ.isEmpty() && !endQ.isEmpty()) {
            if (startQ.peek() < endQ.peek()) {
                cnt++;
                startQ.poll();
            } else {
                startQ.poll();
                endQ.poll();
            }
        }
        System.out.print(cnt);
    }
}