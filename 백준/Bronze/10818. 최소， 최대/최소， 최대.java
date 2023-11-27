import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        // 우선순위 큐 오름차순
        PriorityQueue<Integer> pq1 = new PriorityQueue<>();
        // 우선순위 큐 내림차순
        PriorityQueue<Integer> pq2 = new PriorityQueue<>(Comparator.reverseOrder());
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());


        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(st.nextToken());
            pq1.add(num);
            pq2.add(num);
        }

        System.out.println(pq1.peek()+" "+pq2.peek());
    }
}
