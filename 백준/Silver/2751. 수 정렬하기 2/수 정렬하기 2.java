import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException { // 값 입력

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 우선순위 큐, 힙정렬 사용  최상, 최악, 평균 O(NlogN) 보장한다.
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            pq.offer(Integer.parseInt(br.readLine()));
        }

        for (int i = 0; i < N; i++) {
            sb.append(pq.poll()).append("\n");
        }
        System.out.print(sb);
    }
}