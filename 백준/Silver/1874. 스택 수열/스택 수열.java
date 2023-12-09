import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        Stack<Integer> stk = new Stack<>();

        int N = Integer.parseInt(br.readLine());

        // 초기 설정
        stk.push(1); // 1부터 시작 미리 넣는다.
        int flag = 2;   // 다음 스택 입력 수
        sb.append("+").append("\n");

        st:
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());

            while (!stk.isEmpty()) {
                if (stk.peek() == num) { // 같다면 pop
                    stk.pop();
                    sb.append("-").append("\n");
                    if (stk.isEmpty()) {
                        stk.push(flag++);
                        sb.append("+").append("\n");
                    }
                    break;
                } else if (stk.peek() < num) { // num 보다 peek가 작다면 push
                    stk.push(flag++);
                    sb.append("+").append("\n");
                } else if (stk.peek() > num) {
                    // 스택 수열 은 stk.peek 가 num 보다 클 수 없음
                    // 스택 수열의 규칙임.
                    System.out.println("NO");
                    System.exit(0);
                }
            }

        }

        System.out.print(sb.deleteCharAt(sb.length() - 2));
    }
}
