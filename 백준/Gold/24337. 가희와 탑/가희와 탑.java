import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        Deque<Integer> dq = new ArrayDeque<>(); // deque 이용 풀이.
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int max = Math.max(A, B);


        // 1 -> A-1 까지 입력
        for (int i = 1; i < A; i++) {
            dq.offerLast(i);
        }
        // 가장 A 와 B 중 가장 큰값 넣기.
        dq.offerLast(max);

        // 1 -> B-1 까지 입력
        for (int i = B-1; i >= 1 ; i--) {
            dq.offerLast(i);
        }
        // deque 사이즈가 N 보다 크다면 -> 만들어 질 수 없는 조건임
        if (dq.size() > N) {
            System.out.println(-1);
            System.exit(0);
        }

        // 사이즈가 작다면 최소값 1 을 넣어 사이즈를 맞춘다.
        int tmp = dq.pop();
        while (dq.size() + 1 < N) {
            dq.offerFirst(1);
        }
        // A = 1인 경우 예외처리.
        dq.offerFirst(tmp);


        for (int i = 0; i < N; i++) {
            sb.append(dq.pollFirst()).append(" ");
        }
        System.out.print(sb);
    }
}