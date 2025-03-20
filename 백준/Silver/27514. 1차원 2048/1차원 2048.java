import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        PriorityQueue<Long> pq = new PriorityQueue<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            long num = Long.parseLong(st.nextToken());
            if (num == 0) continue;
            pq.offer(num);
        }

        long answer = 0;

        while (!pq.isEmpty()) {
            long num1 = pq.poll();
            if (pq.isEmpty()) {
                answer = Math.max(answer, num1);
                break;
            }
            long num2 = pq.poll();
            if (num1 == num2) {
                pq.offer(num1 * 2);
            } else {
                pq.offer(num2);
            }
        }

        System.out.println(answer);

    }
}