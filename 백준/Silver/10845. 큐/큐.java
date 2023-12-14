import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException { // 값 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        // Queue에서 가장 뒤 정수를 출력하기위해 peekLast 사용한다
        // 즉 LinkedList 사용하여 .peekLast 메서드를 사용한다.
        LinkedList<Integer> qu = new LinkedList<>();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            String type = st.nextToken();
            int num = 0;
            // push 일때 값을 받아야 함.
            if (type.equals("push")) {
                num = Integer.parseInt(st.nextToken());
                qu.offer(num);
            }

            // 나머지는 출력이다.
            if (type.equals("front")) {
                if (!qu.isEmpty()) {
                    sb.append(qu.peek()).append("\n");
                } else {
                    sb.append(-1).append("\n");
                }
            } else if (type.equals("back")) {
                if (!qu.isEmpty()) {
                    sb.append(qu.peekLast()).append("\n");
                } else {
                    sb.append(-1).append("\n");
                }
            } else if (type.equals("size")) {
                sb.append(qu.size()).append("\n");
            } else if (type.equals("pop")) {
                if (!qu.isEmpty()) {
                    sb.append(qu.poll()).append("\n");
                } else {
                    sb.append(-1).append("\n");
                }
            } else if (type.equals("empty")) {
                sb.append(qu.isEmpty() ? 1 : 0).append("\n");
            }

        }
        System.out.print(sb);
    }
}