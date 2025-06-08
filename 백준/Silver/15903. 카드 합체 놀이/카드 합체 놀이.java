import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        long answer = 0;

        PriorityQueue<Long> pq = new PriorityQueue<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            pq.offer(Long.parseLong(st.nextToken()));
        }

        for (int i = 0; i < M; i++) {
            long num = pq.poll() + pq.poll();
            pq.offer(num);
            pq.offer(num);
        }

        answer = pq.stream().mapToLong(Long::longValue).sum();
        System.out.println(answer);
    }
}