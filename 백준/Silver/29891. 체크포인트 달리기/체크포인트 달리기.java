import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        long answer = 0;
        PriorityQueue<Integer> pq1 = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> pq2 = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            int cordinate = Integer.parseInt(br.readLine());

            if (cordinate < 0) {
                pq2.offer(cordinate);
            } else if (cordinate > 0) {
                pq1.offer(cordinate);
            }
        }

        while (!pq1.isEmpty()) {

            int num = pq1.poll();

            answer += num * 2;

            for (int i = 0; i < K - 1; i++) {
                if (!pq1.isEmpty()) {
                    pq1.poll();
                }
            }
        }

        while (!pq2.isEmpty()) {

            int num = pq2.poll();

            answer += Math.abs(num * 2);

            for (int i = 0; i < K - 1; i++) {
                if (!pq2.isEmpty()) {
                    pq2.poll();
                }
            }
        }

        System.out.println(answer);

    }
}