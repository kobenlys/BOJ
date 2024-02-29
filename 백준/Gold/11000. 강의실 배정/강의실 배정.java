import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException { //조건 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PriorityQueue<Integer> startQ = new PriorityQueue<>();
        PriorityQueue<Integer> endQ = new PriorityQueue<>();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            startQ.offer(s); // 내림차순 시작시간 담는 우선순위 큐
            endQ.offer(e); // 내림차순 끝나는 시간 담는 우선순위 큐
        }

        int cnt = 0;
        while (!startQ.isEmpty() && !endQ.isEmpty()) {
            if (startQ.peek() < endQ.peek()) {
                // 시작시간 보다 끝나는 시간이 큰 경우
                // 즉 강의실 하나 필요함.
                cnt++;
                startQ.poll();
            } else {
                // 시간 시작시간과 끝나는 시간이 같은 경우 = 강의실 재사용
                // ex) 1시 ~ "3시", "3시"(시작시간) ~ 5시 = 강의실 1개면 충분.
                // 시작시간이 끝나는 시간보다 큰 경우 = 강의실 재사용
                // ex) 1시 ~ "3시", "7시"(시작시간) ~ 10시
                startQ.poll();
                endQ.poll();
            }
        }
        System.out.print(cnt);
    }
}