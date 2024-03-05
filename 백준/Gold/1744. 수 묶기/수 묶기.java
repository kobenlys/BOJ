import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException { //조건 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PriorityQueue<Integer> pq1 = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> pq2 = new PriorityQueue<>();

        int cnt = 0;
        int zeroCnt = 0;
        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(br.readLine());
            if (n > 1) {
                // 1 초과 양수는 pq1저장
                pq1.offer(n);
            } else if (n < 0) {
                // 음수는 pq2 저장
                pq2.offer(n);
            } else {
                // 1은 계산 중 변수가 없으므로 더해주는게 가장 좋음
                // 0의 유무 판별.
                if(n == 0) zeroCnt++;
                cnt += n;
            }
        }
        // pq1은 내림 차순 정렬
        while (!pq1.isEmpty()) {

            int a = pq1.poll();
            if (pq1.isEmpty()) {
                cnt += a;
                break;
            }
            int b = pq1.poll();
            // a, b 둘다 존재시 a*b
            // a 혼자만 있다면 더해주기
            cnt += a*b;
        }
        // pq2는 오름 차순 정렬 (-5,-4,-2 ...)
        while (!pq2.isEmpty()) {

            int a = pq2.poll();

            if (pq2.isEmpty()) {
                // 음수가 마지막 하나만 남았다면.
                // 음수를 제거할 수 있는 숫자 '0'의 존재에 따라 계산한다.
                if (zeroCnt == 0) {
                    cnt += a;
                }
                break;
            }
            int b = pq2.poll();
            // 음수*음수 = 양수 를 이용한다
            cnt += a*b;
        }
        System.out.print(cnt);
    }
}