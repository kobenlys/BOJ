import java.io.*;
import java.util.*;

public class Main {

    public static class node implements Comparable<node> {
        int d, w;

        public node(int d, int w) {
            this.d = d;
            this.w = w;
        }
        // 1순위 d, 2순위 w 둘 다 내림차순
        @Override
        public int compareTo(node o) {
            if (d == o.d) {
                return o.w - w;
            }
            return o.d - d;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        PriorityQueue<node> pq = new PriorityQueue<node>();
        PriorityQueue<Integer> tmp = new PriorityQueue<>(Collections.reverseOrder());
        int N = Integer.parseInt(br.readLine());
        int answer = 0;
        int day = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            pq.offer(new node(d, w));
        }
        // 현재 day에 끝낼 수 있는 과제를 체크 할 거임.
        day = pq.peek().d;

        while (day > 0) {
            // 현재 day가 마감일인 과제들의 점수를 tmp 우선순위 큐에 넣기.
            while (!pq.isEmpty() && pq.peek().d == day) {
                tmp.offer(pq.poll().w);
            }

            // 그중 가장 큰 점수를 주는 과제 고르기.
            if (!tmp.isEmpty()) {
                answer += tmp.poll();
            }
            // 날짜를 줄여가며 탐색
            day--;
        }

        System.out.println(answer);
    }
}