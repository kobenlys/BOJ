import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Stack<Character> stk = new Stack<>();
        StringBuilder sb = new StringBuilder();

        while (true) {
            String input = br.readLine();
            if (input.equals(".")) { // 조건 1 : '.' 경우 종료 후 출력
                System.out.print(sb);
                System.exit(0);
            }

            for (int i = 0; i < input.length() - 1; i++) { // 조건 2 : 소괄호, 대괄호 짝 맞는지 체크
                if (input.charAt(i) == '(') {
                    stk.add('(');
                } else if (input.charAt(i) == '[') {
                    stk.add('[');
                }

                if (input.charAt(i) == ')') {
                    if (stk.isEmpty()) {
                        sb.append("no").append("\n");
                        // 조건 3 에서 제외하기 위해 stk가 비어있다면 하나 입력
                        stk.add('e');
                        break;
                    } else if (stk.peek() == '(') {
                        stk.pop();
                    } else {
                        sb.append("no").append("\n");
                        break;
                    }
                } else if (input.charAt(i) == ']') {
                    if (stk.isEmpty()) {
                        sb.append("no").append("\n");
                        stk.add('e');
                        break;
                    } else if (stk.peek() == '[') {
                        stk.pop();
                    } else {
                        sb.append("no").append("\n");
                        break;
                    }
                }
                if (!stk.isEmpty() && i == input.length() - 2) {
                    sb.append("no").append("\n");
                }
            }
            // 조건 3 : stk 가 비어있고(괄호짝이 다 맞음), 문자열 끝이 '.'으로 끝난 다면 yes
            if (stk.isEmpty()) {
                if (input.charAt(input.length() - 1) == '.') {
                    sb.append("yes").append("\n");
                }else{
                    sb.append("no").append("\n");
                }
            }
            stk.clear();
        }
    }
}