import java.io.*;
import java.util.*;

public class Main {

    // 번호와, 원래 위치 인덱스 저장
    public static class node implements Comparable<node> {
        int n, idx;

        public node(int n, int idx) {
            this.n = n;
            this.idx = idx;
        }

        @Override
        public int compareTo(node o) {
            // 우선순위 큐는 같은 값에 대해 인덱스 순서가 보장이 안된다.
            if (n == o.n) {
                return idx - o.idx;
            }
            return n - o.n;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PriorityQueue<node> pq = new PriorityQueue<>();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int answer = 0;
        int idx = 0;

        // 오름차순 정렬 + 입력
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());
            pq.offer(new node(num, i));
        }

        while (!pq.isEmpty()) {
            node nd = pq.poll();
            // 원래 있던 자리 - 정렬 후 자리 인덱스의 차가 가장 큰 것을 저장
            // 오른쪽으로 이동한 요소들 기준으로 카운트
            answer = Math.max(answer, nd.idx - idx++);
        }
        System.out.println(answer + 1);
    }
}