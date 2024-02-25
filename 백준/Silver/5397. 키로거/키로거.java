import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException { //조건 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        Stack<Character> stk1;
        Stack<Character> stk2;
        StringBuilder ans;
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            ans = new StringBuilder();
            String str = br.readLine();
            stk1 = new Stack<>();
            stk2 = new Stack<>();

            // 커서의 움직임의 따른 비밀번호 도출
            for (int i = 0; i < str.length(); i++) {

                if (str.charAt(i) == '<') {
                    // 커서 왼쪽으로 이동
                    if (!stk1.isEmpty()) {
                        stk2.push(stk1.pop());
                    }

                } else if (str.charAt(i) == '>') {
                    // 커서 오른쪽으로 이동
                    if (!stk2.isEmpty()) {
                        stk1.push(stk2.pop());
                    }

                } else if (str.charAt(i) == '-') {
                    // 커서의 왼쪽(stk1)문자삭제.
                    if (!stk1.isEmpty()) {
                        stk1.pop();
                    }
                } else {
                    stk1.push(str.charAt(i));
                }
            }
            // 완성된 문자열 stk1에 넣기.
            while (!stk2.isEmpty()) {
                stk1.push(stk2.pop());
            }

            // 출력
            while (!stk1.isEmpty()) {
                ans.append(stk1.pop());
            }
            sb.append(ans.reverse()).append("\n");
        }
        System.out.print(sb);
    }
}