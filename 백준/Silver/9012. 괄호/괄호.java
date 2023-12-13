import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException { // 값 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Stack<Character> stk = new Stack<>();
        StringBuilder sb = new StringBuilder();

        int tcase = Integer.parseInt(br.readLine());

        st:
        for (int T = 0; T < tcase; T++) {
            String input = br.readLine();

            for (int i = 0; i < input.length(); i++) {
                if ('(' == input.charAt(i)) {
                    stk.push('(');
                }else{
                    if (stk.isEmpty()) {
                        sb.append("NO").append("\n");
                        // 스택이 비었는데 ')' 입력시 옳지 않은 괄호 문자열임
                        // st 지점으로 돌아간다.
                        continue st;
                    }
                    // 스택에 저장된 '(' 을 하나 삭제한다.
                    stk.pop();
                }
            }
            // 스택이 비었다면 -> YES, 스택이 비어있지않다면 -> NO
            // 올바른 괄호 문자열은 결국 스택이 비어야 한다
            sb.append(stk.isEmpty() ? "YES" : "NO").append("\n");
            stk.clear();
        }
        System.out.print(sb);
    }
}