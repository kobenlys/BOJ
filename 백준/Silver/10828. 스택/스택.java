import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException { // 값 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        Stack<Integer> stk = new Stack<>();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            String type = st.nextToken();
            int num = 0;
            // push 일때 값을 받아야 함.
            if (type.equals("push")) {
                num = Integer.parseInt(st.nextToken());
                stk.push(num);
            }

            // 나머지는 출력이다.
            if (type.equals("top")) {
                if (!stk.isEmpty()) {
                    sb.append(stk.peek()).append("\n");
                } else {
                    sb.append(-1).append("\n");
                }
            } else if (type.equals("size")) {
                sb.append(stk.size()).append("\n");
            } else if (type.equals("pop")) {
                if (!stk.isEmpty()) {
                    sb.append(stk.pop()).append("\n");
                } else {
                    sb.append(-1).append("\n");
                }
            } else if (type.equals("empty")) {
                sb.append(stk.isEmpty() ? 1 : 0).append("\n");
            }
        }
        System.out.print(sb);
    }
}