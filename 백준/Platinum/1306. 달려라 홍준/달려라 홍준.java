import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    // 숫자와 해당 숫자 인덱스 담는 객체임.
    public static class node {
        int n, idx;
        public node(int n, int idx) {
            this.n = n;
            this.idx = idx;
        }
    }

    public static void main(String[] args) throws IOException { //조건 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder();
        Deque<node> dq = new ArrayDeque<>();

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken()); // 2

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());

            if (i  < M*2-2) { // dq에 초기 범위 숫자 담기
                while (!dq.isEmpty() && dq.peekLast().n <= num) {
                    dq.pollLast();
                }
                dq.offerLast(new node(num, i));
                continue;
            }
            // dq의 가장 처음 값이 큰값 보장함.
            while (!dq.isEmpty() && dq.peekLast().n <= num) {
                dq.pollLast();
            }
            // dq의 가장 처음 값의 idx가 시야범위를 벗어났다면 제거
            if (!dq.isEmpty() && dq.peekFirst().idx == i - (M*2 -1)) {
                dq.pollFirst();
            }
            // 현재 숫자와 해당 인덱스 같이 저장
            dq.offerLast(new node(num, i));
            // 가장 큰값 출력
            sb.append(dq.peekFirst().n).append(" ");
        }

        if (sb.length() == 0) { // 시야 범위가 모든 숫자 범위보다 넓다면
            // 가장 큰값 M자리부터 끝까지 출력.
            for (int i = M - 1; i < N; i++) {
                sb.append(dq.peekFirst().n).append(" ");
            }
            System.out.print(sb);
        } else {
            System.out.print(sb);
        }
    }
}