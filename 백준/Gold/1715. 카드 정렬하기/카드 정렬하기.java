import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException { //조건 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        int N = Integer.parseInt(br.readLine());
        int ans = 0;

        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(br.readLine());
            pq.offer(n);
        }

        while (pq.size() > 1) {

            int a = pq.poll();
            int b = pq.poll();

            ans += a+b;
            pq.offer(a+b);
        }
        
        // 50 60 70 80
        // 110 + 150 + 260 = 520
        System.out.println(ans);
    }
}