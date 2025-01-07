import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            int N = Integer.parseInt(br.readLine());

            PriorityQueue<Long> pq = new PriorityQueue<>();

            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                pq.offer(Long.parseLong(st.nextToken()));
            }

            BigInteger answer = new BigInteger("1");

            while (pq.size() != 1) {

                if (pq.size() > 1) {
                    BigInteger res = new BigInteger(String.valueOf(pq.poll()));
                    res = res.multiply(new BigInteger(String.valueOf(pq.poll())));
                    answer = answer.multiply(new BigInteger(String.valueOf(res)));
                    pq.offer(res.longValue());
                }
            }
            answer = answer.mod(new BigInteger("1000000007"));
            sb.append(answer).append("\n");
        }
        System.out.println(sb);
    }
}