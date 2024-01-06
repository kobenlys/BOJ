import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException { //조건 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        // 중복체크, 더미값 제거
        HashMap<Integer, Integer> map = new HashMap<>();
        // 두 개의 우선순위 큐 이용, 오름차순, 내림차순
        PriorityQueue<Integer> ASC = new PriorityQueue<>();
        PriorityQueue<Integer> DESC = new PriorityQueue<>(Comparator.reverseOrder());
        StringTokenizer st;
        
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int K = Integer.parseInt(br.readLine());
            int size = 0;

            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                String input = st.nextToken();
                int num = Integer.parseInt(st.nextToken());

                if (input.equals("I")) {
                    // 중복된 숫자가 몇개 입력되었는지 확인한다.
                    if (map.containsKey(num)) {
                        map.put(num, map.get(num) + 1);
                    } else {
                        map.put(num, 1);
                    }
                    size++;
                    // 두 곳에 같은 숫자를 저장하다 보니 더미값이 생길 수 있다.
                    ASC.offer(num); // 최솟값 출력용
                    DESC.offer(num); // 최댓값 출력용

                } else {
                    if (num > 0) {
                        // 더미 값 제거
                        while (!DESC.isEmpty() && map.get(DESC.peek()) == 0) {
                            DESC.poll();
                        }

                        if (!DESC.isEmpty()) { // 아직 삭제가 안되었다면 삭제, value--
                            map.put(DESC.peek(), map.get(DESC.poll()) - 1);
                            size--;
                        }

                    } else {
                        // 더미 값 제거
                        while (!ASC.isEmpty() && map.get(ASC.peek()) == 0) {
                            ASC.poll();
                        }

                        if (!ASC.isEmpty()) { // 아직 삭제가 안되었다면 삭제, value--
                            map.put(ASC.peek(), map.get(ASC.poll()) - 1);
                            size--;
                        }
                    }
                }
            }
            // 모든 연산이 끝나고 모든 더미값이 제거되었다는 보장이 없다, 다시 제거해준다.
            while (!DESC.isEmpty() && map.get(DESC.peek()) == 0) {
                DESC.poll();
            }
            while (!ASC.isEmpty() && map.get(ASC.peek()) == 0) {
                ASC.poll();
            }

            if (size > 0) { // 당연히 삭제 연산이 더 많다면 큐는 비어있다.
                sb.append(DESC.peek()).append(" ").append(ASC.peek()).append("\n");
            } else {
                sb.append("EMPTY").append("\n");
            }
            // 다음 테스트케이스를 위해 초기화
            ASC.clear();
            DESC.clear();
            map.clear();
        }
        System.out.print(sb);
    }
}
