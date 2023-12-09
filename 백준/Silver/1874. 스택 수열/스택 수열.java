import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        Stack<Integer> stk = new Stack<>();

        int N = Integer.parseInt(br.readLine());
        String str = "";
        String res = "";

        // 초기 설정
        stk.push(1); // 1부터 시작 미리 넣는다.
        int flag = 2;   // 다음 스택 입력 수
        int temp = 0;
        sb.append("+").append("\n");


        st:
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());

            while (!stk.isEmpty()) {
                if (stk.peek() == num) {
                    stk.pop();
                    sb.append("-").append("\n");
                    if (stk.isEmpty()) {
                        stk.push(flag++);
                        sb.append("+").append("\n");
                    }
                    break;
                } else if (stk.peek() < num) {
                    stk.push(flag++);
                    sb.append("+").append("\n");
                } else if (stk.peek() > num) {
                    System.out.println("NO");
                    System.exit(0);
                }
            }

        }

        System.out.print(sb.deleteCharAt(sb.length()-2));
    }
}


