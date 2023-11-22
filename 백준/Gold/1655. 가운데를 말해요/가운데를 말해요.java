import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static PriorityQueue<Integer> maxq = new PriorityQueue<>(Comparator.reverseOrder());
    public static PriorityQueue<Integer> minq = new PriorityQueue<>();

    public static void algorithm() {
        // 둘중 하나라도 비었다면 return
        if (maxq.isEmpty() || minq.isEmpty()) {
            return;
        }
        // minq 에는 큰값을  maxq에는 작은값을 저장한다.
        if (minq.peek() < maxq.peek()) {
            int temp = minq.poll();
            minq.offer(maxq.poll());
            maxq.offer(temp);
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        // 정렬된 배열을 중간으로 나눈다고 생각해보자.
        // -99 1 2 5 5 7 10 중간으로 나눈다고 생각 했을때
        // -99, 1, 2, 5 는 오름차순이고  5, 7, 10 은 내림차순이다.
        // 그렇다면 오름차순된 pq에서 가장 앞 값이 정답이다.
        // 우선순위 큐를 오름차순, 내림차순을 각각 만든후 오름차순큐와 내림차순큐 앞자리 값을 비교하여
        // 큰값은 내림차순큐 쪽으로, 작은 값은 오름차순큐 쪽으로 변경한다.

        for (int i = 0; i < N; i++) {

            int num = Integer.parseInt(br.readLine());
            //짝수 일때
            if (maxq.size() == minq.size()) {
                maxq.add(num);
            } else { // 홀수 일때
                minq.add(num);
            }

            algorithm();
            sb.append(maxq.peek()).append("\n");
        }


        System.out.println(sb);
    }
}
