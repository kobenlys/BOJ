import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Queue<Integer> qu = new LinkedList<>(); // FIFO 자료구조 사용해야 한다.
        int N = Integer.parseInt(br.readLine());

        for (int i = 1; i <= N; i++) {
            qu.offer(i);
        }

        while (qu.size() != 1) {
            // 처음 카드 버리기
            qu.poll();

            // 맨 앞 카드 밑으로 옮기기
            if (!qu.isEmpty()) {
                int tmp = qu.poll();
                qu.offer(tmp);
            }
        }
        // 출력
        System.out.println(qu.peek());
    }
}